import cookie from 'js-cookie';
import { DispatchWithoutAction } from 'react';
import { AppDispatch } from '../store/configs/mainStore';
import { logout } from '../store/features/auth/actions';

const setCookie = (key: string, value: string) => {
    if (typeof window) {
        cookie.set(key, value, {
            expires: 1,
            path: '/'
        });
    }
};

const removeCookie = (key: string) => {
    if (typeof window) {
        cookie.remove(key, {
            expires: 1
        });
    }
};

const getCookie = (key: string, req: any) => {
    return typeof window ? getCookieFromBrowser(key) : getCookieFromServer(key, req);
};

const getCookieFromBrowser = (key: string) => {
    return cookie.get(key);
};

const getCookieFromServer = (key: string, req: any) => {
    if (!req.headers.cookie) {
        return undefined;
    }
    const rawCookie = req.headers.cookie.split(';').find((cookie: any) => cookie.trim().startsWith(`${key}=`));
    if (!rawCookie) {
        return undefined;
    }
    return rawCookie.split('=')[1];
};

const deslogarHandler = (dispatch: AppDispatch) => {
    dispatch(logout())
}

export { setCookie, removeCookie, getCookie, getCookieFromBrowser, deslogarHandler};