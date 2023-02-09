import MenuItem from "../models/general/MenuItem";
import {
    Info,
    Home,
    Inbox,
    AccountCircle
} from '@mui/icons-material';

const getItensMenuGeral = () => {
    return [
        new MenuItem(1, "Início", <Home />, "/"),
        new MenuItem(2, "Modal", <AccountCircle />, "/modal"),
        new MenuItem(3, "Sobre", <Info />, "/sobre"),
        new MenuItem(4, "Lotes", <Inbox />, "/lote"),
    ]
}


const useGetMenuItens = () => {
    return { itensMenu: getItensMenuGeral() };
}
export { getItensMenuGeral, useGetMenuItens }