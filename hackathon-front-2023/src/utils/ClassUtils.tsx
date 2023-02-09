import { IProp } from "../models/decorators/PropDecorator";
import { isVazio } from "./CodeUtil";
import moment from 'moment-timezone';

class ClassUtil {
    public static JSONConstructor<T>(entity: any, _type: (new () => T)): T {
        var novaClasse = new _type();
        if (!isVazio(entity)) {
            var listPropriedadesObjeto = this.getAllProps(_type);
            var listPropriedadesEntidade = this.getAllProps(entity);

            type objKey = keyof typeof novaClasse;
            listPropriedadesObjeto.map(item => {

                if (!isVazio(item.className)) {
                    if (item.type === "arrayObject" && ((entity[item.name] as []) ?? []).length > 0) {
                        entity[item.name] = (entity[item.name] as []).map(arr => this.JSONConstructor(arr, item.classType));
                        novaClasse[item.name as objKey] = entity[item.name];
                        return;
                    }

                    if ((item.type === "arrayObject" || item.type == "array") && ((entity[item.name] as []) ?? []).length == 0) {
                        novaClasse[item.name as objKey] = [] as any;
                        return;
                    }

                    if (!isVazio(entity[item.name]))
                        novaClasse[item.name as objKey] = this.JSONConstructor(entity[item.name], item.classType);

                }
                else if (item.type === "Date" && !isVazio(entity[item.name])) {
                    novaClasse[item.name as objKey] = moment(entity[item.name]).local().toDate() as any;
                }
                else if (entity[item.name] !== undefined) {
                    novaClasse[item.name as objKey] = entity[item.name]
                }

            });
        }
        return novaClasse;
    }



    public static getAllProps(cls: new (...args: any[]) => any): IProp[] {
        let result: IProp[] = [];
        let prototype = cls.prototype;
        while (prototype != null) {
            let props: IProp[] = prototype["__props__"];
            if (props) {
                result.push(...props);
            }
            prototype = Object.getPrototypeOf(prototype);
        }
        if (prototype == null && typeof cls === "object")
            result = Object.getOwnPropertyNames(cls).map(x => ({ name: x } as IProp));
        return result;
    }
}




export default ClassUtil;