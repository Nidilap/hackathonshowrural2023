import { createAsyncThunk } from "@reduxjs/toolkit";
import APISysAgroLinks from "../../assets/constants/api/ApiSysAgroLinks";
import Lote from "../../models/redux/lote/lote";
import { axiosGet } from "../../utils/AxiosUtils";
import { RootState } from "../configs/mainStore";


const fetchLotes = createAsyncThunk<any>('lote/fetchLotes',
    async (props: any, thunkApi: any) => {
        try {
            const state: RootState = thunkApi.getState();
            const { auth } = thunkApi.getState();

            const resData = await axiosGet(APISysAgroLinks.LoteEndPoints.getListaLoteAvesByUsuario(state.root.auth.idUsuario));
          
            // return thunkApi.fulfillWithValue({ notasFiscaisUsuario: notasFiscaisFixas })
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    });

export { fetchLotes };