import { Prop } from "../decorators/PropDecorator";
import BaseModel from "./baseModel";
class Coordenada extends BaseModel {

    @Prop("number")
    x!: number;

    @Prop("number")
    y!: number;

}

export default Coordenada;