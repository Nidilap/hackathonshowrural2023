import ApiUsuarioEndPoint from "./ApiUsuarioEndPoint";
import LoteEndPoints from "./ApiLoteEndPoint";
import ApiClienteEndPoint from "./ApiClienteEndPoint";

export default class APISysAgroLinks {
    // Usuario && Auth
    static UsuarioEndPoints = new ApiUsuarioEndPoint();

    static LoteEndPoints = new LoteEndPoints();

    static ClienteEndPoints = new ApiClienteEndPoint();
}