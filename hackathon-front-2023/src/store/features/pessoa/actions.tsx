
//Constants
import APISysAgroLinks from "../../../assets/constants/api/ApiSysAgroLinks";

import Usuario from "../../../models/redux/usuario";
import { push } from "redux-first-history";


// Redux
import { createAsyncThunk } from "@reduxjs/toolkit"

// Utils
import ClassUtil from "../../../utils/ClassUtil";
import { axiosGet, axiosPost } from "../../../utils/AxiosUtils";
import { removeCookie, setCookie } from "../../../utils/AuthUtils";
import { RootState } from "../../configs/mainStore";
import Pessoa from "../../../models/redux/pessoa";

// Alert
import { toast } from "react-toastify";

const fetchPessoas = createAsyncThunk<any>('pessoa/fetchPessoas',
    async (props: any, thunkApi: any) => {
        try {
            const state: RootState = thunkApi.getState();

            const resData = await axiosGet(APISysAgroLinks.ClienteEndPoints.getListaPessoas());


            let listaPessoas: Pessoa[] = [];
            (resData.dados ?? []).map((item: any) => {
                listaPessoas.push(ClassUtil.JSONConstructor(item, Pessoa))
            })


            return thunkApi.fulfillWithValue({ pessoas: listaPessoas })
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    }
);

export type VisitaProps = {
    idPessoa: number;
    idEndereco: number;
    dataAgendada: Date;
    observacao: string;
}
const cadastrarVisita = createAsyncThunk('pessoa/cadastrarVisita',
    async (visitaProps: VisitaProps, thunkApi: any) => {
        try {
            const state: RootState = thunkApi.getState();

            const resData = await axiosPost(APISysAgroLinks.ClienteEndPoints.getListaVisitas(), {
                idFuncionario: state.root.auth.idUsuario,
                idPessoa: visitaProps.idPessoa,
                idEndereco: visitaProps.idEndereco,
                dataAgendada: visitaProps.dataAgendada,
                observacao: visitaProps.observacao,
            });

            toast.success("Agendamento feito com sucesso!", {
                position: "top-center",
              });
            thunkApi.dispatch(push("/"));
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    }
)

export { fetchPessoas, cadastrarVisita}