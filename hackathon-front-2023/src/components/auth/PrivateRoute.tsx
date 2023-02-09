import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { getCookieFromBrowser } from '../../utils/AuthUtils';

const PrivateRoute = () => {
    let auth = false;
    // Redireciona para a página de login se acessando uma página sem ter token.
    const token = getCookieFromBrowser("token");
    if(token) {
        auth = true
    }

    // Se não autorizado, será redirecionado para o login. Se não, o componente do outlet é renderizado.
    return auth ? <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoute;