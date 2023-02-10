import APISysAgro from "./ApiSysAgro";


export default class ApiVisitaEndPoint {

    //#region GET/POST
    getListaVisitas() {
        return `${APISysAgro.getServidor()}/visitas`;
    }

    getListaVisitasByIdUsuario(idUsuario: number) {
        return `${APISysAgro.getServidor()}/usuarios/${idUsuario}/visitas`;
    }

}