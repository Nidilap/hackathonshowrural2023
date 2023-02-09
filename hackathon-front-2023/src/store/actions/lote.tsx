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
            debugger
            // const notasFiscaisFixas: Lote[] = [
            //     ClassUtil.JSONConstructor({ idNotaFiscal: 2, numero: 385438, fornecedor: "COCAMAR", qntItens: 100, valorTotal: 1000, dataEmissao: new Date('2022-07-03') }, NotaFiscal),
            //     ClassUtil.JSONConstructor({ idNotaFiscal: 1, numero: 256489, fornecedor: "COCAMAR", qntItens: 250, valorTotal: 2500, dataEmissao: new Date('2022-07-02') }, NotaFiscal),
            //     ClassUtil.JSONConstructor({ idNotaFiscal: 0, numero: 11974, fornecedor: "COCAMAR", qntItens: 300, valorTotal: 3000, dataEmissao: new Date('2022-07-01') }, NotaFiscal),
            // ]

            // return thunkApi.fulfillWithValue({ notasFiscaisUsuario: notasFiscaisFixas })
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    });

export { fetchLotes };