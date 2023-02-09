function Prop(type: Ttype, classe: any = null): PropertyDecorator {

    return (target: Object, propertyKey: string | symbol): void => {

        var className = "";
        var classType = null;
        if (type === "arrayObject" || type == "object") {
            ErroPropTipoFuncao(classe, propertyKey, target);
            className = classe.name;
            classType = classe;
        }
        let props: IProp[]
        if (target.hasOwnProperty("__props__")) {
            props = (target as any)["__props__"] as IProp[];
        } else {
            props = ((target as any)["__props__"] as IProp[]) = [];
        }
        props.push({
            name: propertyKey as string,
            type,
            className: className,
            classType: classType
        } as IProp);
    };
}
type Ttype = "number" | "string" | "boolean" | "object" | "enum" | "array" | "Date" | "arrayObject";

function ErroPropTipoFuncao(classe: any, propertyKey: string | symbol, target: Object) {
    if (classe === null || classe == undefined || classe?.name === undefined)
        throw new Error(`###Erro na propriedade: ${(propertyKey as string).toUpperCase()} da Classe ${target.constructor.name.toUpperCase()}\n###Defina o tipo do Objeto da prop dessa maneira:\n -- @Prop(Usuario) e não @Prop(new Usuario())`);
}

interface IProp {
    name: string;
    type?: Ttype;
    className?: string;
    classType?: any
}

export { Prop };
export type { IProp };
