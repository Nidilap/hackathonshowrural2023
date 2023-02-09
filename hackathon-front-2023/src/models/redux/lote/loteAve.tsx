import { Prop } from "../../decorators/PropDecorator";
import BaseModel from "../baseModel";
class LoteAve extends BaseModel {

	@Prop("number")
	idLoteAve!: number;

	@Prop("number")
	idAviario!: number;

	@Prop("number")
	idLote!: number;

	@Prop("number")
	idManejoPreAbateAve!: number;

	@Prop("string")
	sexoAveEnum!: string;
}

export default LoteAve;