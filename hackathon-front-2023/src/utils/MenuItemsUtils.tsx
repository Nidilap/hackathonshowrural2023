import MenuItem from "../models/general/MenuItem";
import {
    Home,
    CalendarMonth,
    Map,
    Schedule
} from '@mui/icons-material';

const getItensMenuGeral = () => {
    return [
        new MenuItem(1, "Mapa", <Map />, "/mapaVendas"),
        new MenuItem(2, "Início", <Home />, "/"),
        new MenuItem(3, "Calendário", <CalendarMonth />, "/agenda"),
        new MenuItem(4, "Agendar", <Schedule />, "/agenda/criaragenda"),
    ]
}


const useGetMenuItens = () => {
    return { itensMenu: getItensMenuGeral() };
}
export { getItensMenuGeral, useGetMenuItens }