
//Constants
import APISysAgroLinks from "../../../assets/constants/api/ApiSysAgroLinks";

import Usuario from "../../../models/redux/usuario";
import { push } from "redux-first-history";


// Redux
import { createAsyncThunk } from "@reduxjs/toolkit"

// Utils
import ClassUtil from "../../../utils/ClassUtil";
import { axiosPost } from "../../../utils/AxiosUtils";
import { removeCookie, setCookie } from "../../../utils/AuthUtils";

export type LoginProps = {
    usuario: string;
    senha: string;
}

const login = createAsyncThunk('auth/autenticar',
    async (loginProps: LoginProps, thunkApi: any)  => {
        try {
            const resData = await axiosPost(APISysAgroLinks.UsuarioEndPoints.getLogin(), {
                login: loginProps.usuario,
                senha: loginProps.senha,
            });
            var userObject = ClassUtil.JSONConstructor(resData.dados, Usuario);
            thunkApi.dispatch(autenticar({authToken: resData.tokenJWT, idUsuario: userObject.idUsuario}))
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    })


export type AutenticarProps = {
    authToken: string;
    idUsuario: number;
}
const autenticar = createAsyncThunk('auth/autenticar', async (props: AutenticarProps, thunkApi) => {
    thunkApi.dispatch(push("/"));
    setCookie("token", props.authToken);
    setCookie("idUsuario", props.idUsuario.toString());
    return thunkApi.fulfillWithValue({authToken: props.authToken, idUsuario: props.idUsuario.toString()});
}); // Recebe um token JWT


export type ReautenticarProps = {
    authToken: string;
    idUsuario: number;
}
const reautenticar = createAsyncThunk('auth/reautenticar',
    async (props: ReautenticarProps, thunkApi: any) => {
        thunkApi.dispatch(autenticar({ authToken: props.authToken, idUsuario: props.idUsuario}))
    });

const logout = createAsyncThunk<any>('auth/logout', async (props: any, thunkApi: any) => {
    removeCookie('token'); 
    thunkApi.dispatch(push("/login"));
});


export { login, autenticar, reautenticar, logout }