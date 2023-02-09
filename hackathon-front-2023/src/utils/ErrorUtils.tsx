import { AxiosError } from "axios";
import { IError } from "../interfaces/general/IError";
import { toast } from "react-toastify";


const axiosErrorProcessor = (error: AxiosError) => {

    if (error.message == "Network Error" || error.message == "TIMEOUT") {
        switch (error.message) {
            case "Network Error":
                genericErrorProcessor(new Error("Ocorreu um erro de conexão."));
                break;

            case "TIMEOUT":
                genericErrorProcessor(new Error("Ocorreu um erro de conexão."));
                break;
        }
        return;
    }

    if (error.response?.status != 404) {

        // @ts-ignore
        let errosArray: IError[] = (error?.response?.data?.erros ?? []) as IError[];
        errosArray = errosArray.map((erro) => {
            // TRATA DO MODO ANTIGO, MANTER ISSO ATÉ QUE O PEDRO ATUALIZE A API
            if (erro.codigo === "USUARIO_NAO_ENCONTRADO") {
                erro.mensagem = "Usuário não encontrado";
            } else if (erro.codigo === "USUARIO_SENHA_INVALIDA") {
                erro.mensagem = "Senha não válida";
            }
            return erro;
        });

        if (errosArray.length > 0) {
            mostrarModal(errosArray[0].mensagem);
        }
    }

    console.warn(error);

};

const mostrarModal = (mensagem: string) => toast.error(`Ocorreu um erro! ${mensagem}`, {position: 'top-center'});

const genericErrorProcessor = (error: Error) => {

    // No Futuro podemos criar um log para isso
    var newErro = { mensagem: error.message } as IError;
    mostrarModal(newErro.mensagem)
}


export { genericErrorProcessor, axiosErrorProcessor };