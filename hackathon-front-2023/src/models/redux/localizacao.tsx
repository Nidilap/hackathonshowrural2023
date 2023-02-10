import { Prop } from "../decorators/PropDecorator";
import BaseModel from "./baseModel";
import Coordenada from "./coordenada";
class Localizacao extends BaseModel {

    @Prop("number")
    idLocalizacao!: number;

    @Prop("object", Coordenada)
    coordenada!: Coordenada;

    @Prop("string")
    observacao!: string;

    @Prop("number")
    idFuncionario!: number;

    @Prop("number")
    idPessoa!: number;

}

export default Localizacao;