import { Prop } from "../decorators/PropDecorator";
import BaseModel from "./baseModel";
class Visita extends BaseModel {

    @Prop("number")
    idVisita!: number;

    @Prop("Date")
    dataAgendada!: Date;

    @Prop("string")
    observacao!: string;

    @Prop("number")
    idFuncionario!: number;

    @Prop("number")
    idPessoa!: number;

}

export default Visita;