import ApiUsuarioEndPoint from "./ApiUsuarioEndPoint";
import LoteEndPoints from "./ApiLoteEndPoint";

export default class APISysAgroLinks {
    // Usuario && Auth
    static UsuarioEndPoints = new ApiUsuarioEndPoint();

    static LoteEndPoints = new LoteEndPoints();
}