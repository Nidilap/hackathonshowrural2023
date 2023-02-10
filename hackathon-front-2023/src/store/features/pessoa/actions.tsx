
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

const fetchPessoas = createAsyncThunk<any>('pessoa/fetchPessoas',
    async (props: any, thunkApi: any) => {
        try {
            const state: RootState = thunkApi.getState();

            const resData = await axiosGet(APISysAgroLinks.ClienteEndPoints.getListaPessoas());
            
            debugger
            let listaPessoas: Pessoa[] = [];
            (resData.dados??[]).map((item: any) => {
                listaPessoas.push(ClassUtil.JSONConstructor(item, Pessoa))
            })
            debugger
            // const notasFiscaisFixas: Lote[] = [
            //     ClassUtil.JSONConstructor({ idNotaFiscal: 2, numero: 385438, fornecedor: "COCAMAR", qntItens: 100, valorTotal: 1000, dataEmissao: new Date('2022-07-03') }, NotaFiscal),
            //     ClassUtil.JSONConstructor({ idNotaFiscal: 1, numero: 256489, fornecedor: "COCAMAR", qntItens: 250, valorTotal: 2500, dataEmissao: new Date('2022-07-02') }, NotaFiscal),
            //     ClassUtil.JSONConstructor({ idNotaFiscal: 0, numero: 11974, fornecedor: "COCAMAR", qntItens: 300, valorTotal: 3000, dataEmissao: new Date('2022-07-01') }, NotaFiscal),
            // ]

            return thunkApi.fulfillWithValue({ pessoas: listaPessoas })
        } catch (error: any) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    });

export { fetchPessoas }