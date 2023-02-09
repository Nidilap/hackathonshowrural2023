import { Prop } from "../../decorators/PropDecorator";
import LoteAve from "./loteAve";
import BaseModel from "../baseModel";
class Lote extends BaseModel {

    @Prop("number")
    idLote!: number;

    @Prop("number")
    idLinhagemGenetica!: number;

    @Prop("number")
    idIncubatorio!: number;

    @Prop("number")
    idCidade!: number;

    @Prop("number")
    idCooperado!: number;

    @Prop("number")
    quantidadeAlojamento!: number;

    @Prop("number")
    quantidadeAbate!: number;

    @Prop("number")
    pesoUnitarioAlojamento!: number;

    @Prop("number")
    pesoTotalAlojamento!: number;

    @Prop("number")
    pesoUnitarioAbate!: number;

    @Prop("string")
    hashMobile!: string;

    @Prop("string")
    lote!: string;

    @Prop("string")
    matriz!: string;

    @Prop("string")
    observacao!: string;

    @Prop("Date")
    dataHoraAlojamento!: Date;

    @Prop("object", LoteAve)
    loteAve!: LoteAve;
}

export default Lote;