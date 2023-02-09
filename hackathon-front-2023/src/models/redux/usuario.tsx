import { Prop } from "../decorators/PropDecorator";
import Perfil from "./perfil";
import BaseModel from "./baseModel";
class Usuario extends BaseModel {

  @Prop("number")
  idUsuario!: number;

  @Prop("number")
  idFoto!: number;

  @Prop("boolean")
  isAtivo!: boolean;

  @Prop("string")
  login!: string;

  @Prop("string")
  nome!: string;

  @Prop("string")
  email!: string;

  @Prop("arrayObject", Perfil)
  perfis!: Perfil[];

}

export default Usuario;