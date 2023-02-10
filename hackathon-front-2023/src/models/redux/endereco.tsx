import { Prop } from "../decorators/PropDecorator";
import BaseModel from "./baseModel";
class Endereco extends BaseModel {

    @Prop("number")
    idEndereco!: number;

  @Prop("number")
  numero!: number;

  @Prop("string")
  endereco!: string;

  @Prop("string")
  bairro!: string;

  @Prop("string")
  complemento!: string;

  @Prop("string")
  cep!: string;

  @Prop("string")
  inscricaoEstadual!: string;

  @Prop("string")
  car!: string;

  @Prop("string")
  observacao!: string;

}

export default Endereco;