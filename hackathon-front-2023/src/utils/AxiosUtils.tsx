import Axios, { AxiosRequestConfig, AxiosResponse, AxiosError } from "axios";
import { getCookieFromBrowser } from "./AuthUtils";
// import { getCookieFromBrowser } from "./AuthUtils";
import { axiosErrorProcessor, genericErrorProcessor } from "./ErrorUtils";
// import { getValor } from "./SecureStorage";


const axiosInstance = async () => {
    let internalHeaders = { 'Content-Type': 'application/json', Authorization: '', 'X-Requested-With': 'XMLHttpRequest' }
    let token = getCookieFromBrowser("token");
    // let token = ''; // Excluir isso depois.

    if (token != null && token.length > 0)
        internalHeaders.Authorization = `Bearer ${token}`;

    return Axios.create({
        headers: internalHeaders,
        timeout: 20000,
        timeoutErrorMessage: "TIMEOUT"
    });
}

const generalErrorHandler = (error: unknown, reject: (reason?: any) => void) => {
    genericErrorProcessor(error as Error);
    return reject(error);
}

const axiosErrorHandler = (error: AxiosError<any, any>, reject: (reason?: any) => void) => {
    axiosErrorProcessor(error);
    return reject(error);
}

const axiosGet = async (url: string, config: AxiosRequestConfig | undefined = undefined) => {
    let obj;
    var axios = await axiosInstance();
    return await new Promise<any>(async (resolve, reject) => {
        try {
            await axios.get(url, config).then((response: AxiosResponse) => {
                obj = response.data;
                return resolve(obj);
            }).catch((error: AxiosError) => {
                return axiosErrorHandler(error, reject);
            });
        } catch (error) {
            return generalErrorHandler(error, reject);
        }
    });
};

const axiosPost = async (url: string, data: Object | null, config: AxiosRequestConfig | undefined = undefined) => {
    let obj;
    var axios = await axiosInstance();
    return await new Promise<any>(async (resolve, reject) => {
        try {
            await axios.post(url, data, config).then((response: AxiosResponse) => {
                obj = response.data;

                return resolve(obj);
            }).catch((error: AxiosError) => {
                return axiosErrorHandler(error, reject);
            });
        } catch (error) {
            return generalErrorHandler(error, reject);
        }
    });

};



const axiosPut = async (url: string, data: Object | null, config: AxiosRequestConfig | undefined = undefined) => {
    let obj;
    var axios = await axiosInstance();
    return await new Promise<any>(async (resolve, reject) => {
        try {
            await axios.put(url, data, config).then((response: AxiosResponse) => {
                obj = response.data;
                return resolve(obj);
            }).catch((error: AxiosError) => {
                return axiosErrorHandler(error, reject);
            });
        } catch (error) {
            return generalErrorHandler(error, reject);
        }
    });

};

const axiosPatch = async (url: string, data: Object | null, config: AxiosRequestConfig | undefined = undefined) => {
    let obj;
    var axios = await axiosInstance();
    return await new Promise<any>(async (resolve, reject) => {
        try {
            await axios.patch(url, data, config).then((response: AxiosResponse) => {
                obj = response.data;
                return resolve(obj);
            }).catch((error: AxiosError) => {
                return axiosErrorHandler(error, reject);
            });
        } catch (error) {
            return generalErrorHandler(error, reject);
        }
    });

};

const axiosDelete = async (url: string, config: AxiosRequestConfig | undefined = undefined) => {
    let obj;
    var axios = await axiosInstance();
    return await new Promise<any>(async (resolve, reject) => {
        try {
            await axios.delete(url, config).then((response: AxiosResponse) => {
                obj = response.data;
                return resolve(obj);
            }).catch((error: AxiosError) => {
                return axiosErrorHandler(error, reject);
            });
        } catch (error) {
            return generalErrorHandler(error, reject);
        }
    });

};


export { axiosGet, axiosPost, axiosPut, axiosPatch, axiosDelete };