const isExistente = (variavel: any) => {
    if (variavel !== undefined && variavel !== null) {
        return true
    } else {
        return false;
    }
}

const isVazio = (variavel: any) => {
    if (variavel == undefined || variavel == null || variavel == "") {
        return true
    } else {
        return false;
    }
}

const isArray = (obj: any): boolean => {
    return Array.isArray(obj);
}

const isObjeto = (obj: any): boolean => {
    if (
        typeof obj === 'object' &&
        !Array.isArray(obj) &&
        obj !== null
    ) {
        return true
    }
    else {
        return false
    }
}

const isNegative = (numero: number): boolean => numero < 0;


const getPropriedadesNomes = (obj: any): string[] => {
    return Object.keys(obj);
}

const getOfflineId =  ():number => parseInt(((Date.now() + Math.random())).toFixed(6).replace(".","")) * -1;

const createUUIDSmall = (): string => {
    return crypto.randomUUID().split("-")[0];
}

const pushSafe = (arrayOriginal: Array<any>, itemToPush: any): any[] => {
    // Realiza um push que funciona também para variáveis que não foram inicializadas (undefined).
    if (arrayOriginal instanceof Array) {
        arrayOriginal.push(itemToPush);
    } else if (arrayOriginal == undefined) {
        arrayOriginal = [itemToPush];
    } else {
        console.log(`Variável: ${arrayOriginal} não pode ter um push! Função pushSafe().`);
    }
    return arrayOriginal;
}


export {
    isExistente,
    isVazio,
    isArray,
    isObjeto,
    isNegative,
    getPropriedadesNomes,
    getOfflineId,
    createUUIDSmall,
    pushSafe
};