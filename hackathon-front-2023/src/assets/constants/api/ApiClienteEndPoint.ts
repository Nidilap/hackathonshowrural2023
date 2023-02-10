import APISysAgro from "./ApiSysAgro";


export default class ApiClienteEndPoint {

    //#region GET
    getListaPessoas() {
        return `${APISysAgro.getServidor()}/pessoas/`;
    }
    getListaVisitasByPessoa(idPessoa: number) {
        return `${APISysAgro.getServidor()}/pessoas/${idPessoa}/visitas`;
    }

    //#region GET/POST
    getListaVisitas() {
        return `${APISysAgro.getServidor()}/visitas`;
    }

}