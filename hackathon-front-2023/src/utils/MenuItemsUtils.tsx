import MenuItem from "../models/general/MenuItem";
import {
    Home,
    CalendarMonth,
    Map,
    Groups,
    Schedule,
    Handshake,
    PermPhoneMsg
} from '@mui/icons-material';

const getItensMenuGeral = () => {
    return [
        new MenuItem(1, "Mapa", <Map />, "/mapaVendas"),
        new MenuItem(2, "Início", <Home />, "/"),
        new MenuItem(3, "Calendário", <CalendarMonth />, "/agenda"),
        new MenuItem(4, "Agendar", <Schedule />, "/agendacadastro"),
        new MenuItem(5, "Cliente", <Groups />, "/clientecadastro"),
        new MenuItem(6, "Assistência", <Handshake />, "/comunicarassistencia"),
        new MenuItem(7, "Chamado", <PermPhoneMsg />, "/comunicarassistencia"),
    ]
}


const useGetMenuItens = () => {
    return { itensMenu: getItensMenuGeral() };
}
export { getItensMenuGeral, useGetMenuItens }