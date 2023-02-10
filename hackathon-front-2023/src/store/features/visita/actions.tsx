
//Constants
import APISysAgroLinks from "../../../assets/constants/api/ApiSysAgroLinks";

import Usuario from "../../../models/redux/usuario";
import { push } from "redux-first-history";


// Redux
import { createAsyncThunk } from "@reduxjs/toolkit"

// Utils
import ClassUtil from "../../../utils/ClassUtil";
import { axiosGet } from "../../../utils/AxiosUtils";
import { RootState } from "../../configs/mainStore";

// Alert
import Visita from "../../../models/redux/visita";

const fetchVisitasByUsuario = createAsyncThunk<any>('visita/fetchVisitasByUsuario',
    async (props: any, thunkApi: any) => {
        try {
            const state: RootState = thunkApi.getState();

            const resData = await axiosGet(APISysAgroLinks.VisitaEndPoints.getListaVisitasByIdUsuario(state.root.auth.idUsuario));
            console.log("ENTROU AQUI")
            let visitasDoUsuario: Visita[] = [];
            (resData.dados ?? []).map((item: any) => {
                visitasDoUsuario.push(ClassUtil.JSONConstructor(item, Visita))
            })


            return thunkApi.fulfillWithValue({ visitasDoUsuario: visitasDoUsuario })
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    }
);

export { fetchVisitasByUsuario }