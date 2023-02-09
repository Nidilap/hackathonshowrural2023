import PerfilEnum from "../../assets/enums/perfilEnum";
import { Prop } from "../decorators/PropDecorator";
import BaseModel from "./baseModel";
class Perfil extends BaseModel {
  @Prop("number")
  idPerfil!: number;

  @Prop("string")
  nome!: string;

  @Prop("enum")
  perfilEnum!: PerfilEnum;

  @Prop("string")
  sigla!: string;
}

export default Perfil;