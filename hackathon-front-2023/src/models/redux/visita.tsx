import { Prop } from "../decorators/PropDecorator";
import BaseModel from "./baseModel";
class Visita extends BaseModel {

    @Prop("number")
    idVisita!: number;

    @Prop("Date")
    dataAgendada!: Date | null;

    @Prop("string")
    observacao!: string;

    @Prop("string")
    nomePessoa!: string;

    @Prop("number")
    idFuncionario!: number;

    @Prop("number")
    idPessoa!: number;

}

export default Visita;