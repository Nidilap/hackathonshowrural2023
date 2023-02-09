import { Prop } from '../decorators/PropDecorator';

class BaseModel {

    @Prop("boolean")
    isSincronizado: boolean = false; // | null

    @Prop("boolean")
    isRemovido: boolean = false; // | null

    @Prop("boolean")
    isAlterado: boolean = false; // | null

    @Prop("Date")
    dataHoraCriacao: Date | string = new Date();
}


export default BaseModel;