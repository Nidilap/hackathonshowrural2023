import { ReactNode } from 'react'

// Styles
import Navbar from '../Header/Navbar/Navbar';
import MainTabbar from '../Header/Tabbar/Tabbar';
import './Layout.scss'


interface LayoutProps {
    tabbarInvisivel?: boolean;
    headerInvisivel?: boolean;
    headerTitle?: string;
    drawer?: boolean;
    botaoVoltar?: boolean;
	//Todas as outras props.
	[x: string]: any;
}

const Layout = (props: LayoutProps) => {
    return (

        <div className="layoutWrapper">
            {!props.headerInvisivel && <Navbar title={props.headerTitle ? props.headerTitle : ''} drawer={props.drawer} voltar={props.botaoVoltar}/>}
            {props.children}
            {!props.tabbarInvisivel && <MainTabbar />}
        </div>
    )
}

export default Layout