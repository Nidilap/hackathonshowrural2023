import { Prop } from "../decorators/PropDecorator";
import Endereco from "./endereco";
import BaseModel from "./baseModel";
class Pessoa extends BaseModel {

  @Prop("number")
  idPessoa!: number;

  @Prop("string")
  razaoSocial!: string;

  @Prop("string")
  nomeFantasia!: string;

  @Prop("string")
  nomePai!: string;

  @Prop("string")
  nomeMae!: string;

  @Prop("string")
  cpfCnpj!: string;

  @Prop("string")
  rg!: string;

  @Prop("string")
  telefone!: string;

  @Prop("string")
  celular!: string;

  @Prop("string")
  email!: string;

  @Prop("string")
  atividade!: string;
  
  @Prop("string")
  observacao!: string;

  @Prop("arrayObject", Endereco)
  enderecos!: Endereco[];

}

export default Pessoa;