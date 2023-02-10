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
        new MenuItem(4, "Agendar", <Schedule />, "/agenda/cadastro"),
        new MenuItem(5, "Cliente", <Groups />, "/cliente"),
        new MenuItem(6, "Assistência", <Handshake />, "/assistencia"),
        new MenuItem(7, "Chamado", <PermPhoneMsg />, "/chamado"),
    ]
}


const useGetMenuItens = () => {
    return { itensMenu: getItensMenuGeral() };
}
export { getItensMenuGeral, useGetMenuItens }