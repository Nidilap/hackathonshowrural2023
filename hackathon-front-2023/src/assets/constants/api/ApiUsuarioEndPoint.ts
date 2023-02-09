import APISysAgro from "./ApiSysAgro";

export default class ApiUsuarioEndPoint {

    //#region POST
    getLogin() {
        return `${APISysAgro.getServidor()}/usuarios/autenticar`;
    }
    //#endregion

    //#region GET
    getListaUsuarios() {
        return `${APISysAgro.getServidor()}/usuarios`;
    }
    //#endregion
}