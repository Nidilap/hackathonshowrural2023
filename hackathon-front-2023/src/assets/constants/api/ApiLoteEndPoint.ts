import APISysAgro from "./ApiSysAgro";


export default class ApiLoteEndPoint {

    //#region GET
    getListaLoteAves(idLote?: number) {
        let idLoteAUsar;
        if (idLote == undefined) {
            idLoteAUsar = "";
        } else {
            idLoteAUsar = idLote;
        }
        return `${APISysAgro.getServidor()}/lotes/aves/${idLoteAUsar}`;
    }
    getListaLoteAvesByUsuario(idUsuario: number, idLote?: number) {
        let idLoteAUsar;
        if (idLote == undefined) {
            idLoteAUsar = "";
        } else {
            idLoteAUsar = idLote;
        }

        return `${APISysAgro.getServidor()}/usuarios/${idUsuario}/lotes/aves/${idLoteAUsar}`;
    }


    getLoteRegistrosByLote(
        idLote: number
    ) {
        let idLoteAUsar;
        if (idLote == undefined) {
            idLoteAUsar = "";
        } else {
            idLoteAUsar = idLote;
        }

        return `${APISysAgro.getServidor()}/lotes/${idLoteAUsar}/registros`;
    }


    //#endregion

    //#region Post
    postLoteRegistros() {
        return `${APISysAgro.getServidor()}/lotes/registros`;
    }
    //#endregion




}