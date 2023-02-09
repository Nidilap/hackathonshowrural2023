import { SvgIconProps } from "@mui/material";

class MenuItem {
    idMenuItem: number;
    title: string;
    icone: React.ReactElement<SvgIconProps>;
    url: string;

    constructor(idMenuitem: number, title: string, icone: React.ReactElement<SvgIconProps>, url: string) {
        this.idMenuItem = idMenuitem;
        this.title = title;
        this.icone = icone;
        this.url = url;
    }
}

export default MenuItem;