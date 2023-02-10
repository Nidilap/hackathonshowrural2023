import React from "react";
import { Route, BrowserRouter, Routes as RoutesWrapper, Navigate } from "react-router-dom";

// Configurações de history para usar dentro do redux
import { HistoryRouter} from "redux-first-history/rr6";
import { history } from "../store/configs/mainStore";

import Home from "./../views/home/Home";
import Sobre from "./../views/sobre/Sobre";
import Modal from "../views/modal/Modal";
import Login from "./../views/login/Login";
import PrivateRoute from "../components/auth/PrivateRoute";
import LoteScreen from "../views/lote/loteScreen/LoteScreen";
import MapVendasScreen from "../views/mapVendas/MapVendas";
import AgendaScreen from "../views/agenda/agendaScreen/AgendaScreen";
import AgendaCadastroScreen from "../views/agenda/agendaCadastroScreen/AgendaCadastroScreen";
import ClienteCadastro from "../views/clienteCadastro/ClienteCadastro";
import Chamado from "../views/chamado/Chamado";

const Routes = () => {
    return (
        <HistoryRouter history={history}>
            <RoutesWrapper>
                <Route path='/' element={<PrivateRoute/>}>
                    <Route path="/" element={<Home />} />
                    <Route path="/sobre" element={<Sobre />} />
                    <Route path="/modal" element={<Modal />} />
                    <Route path="/lote" element={<LoteScreen />} />
                    <Route path="/mapaVendas" element={<MapVendasScreen />} />
                    <Route path="/agenda" element={<AgendaScreen />} />
                    <Route path="/agendacadastro" element={<AgendaCadastroScreen />} />
                    <Route path="/clientecadastro" element={<ClienteCadastro />} />
                    <Route path="/comunicarassistencia" element={<Chamado />} />
                    <Route path="*" element={<Navigate to="/" />} />
                </Route>
                <Route path="/login" element={<Login />} />
            </RoutesWrapper>
        </HistoryRouter>
    )
}

export default Routes;