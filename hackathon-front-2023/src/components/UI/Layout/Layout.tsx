import { ReactNode } from 'react'

// Styles
import Navbar from '../Header/Navbar/Navbar';
import './Layout.scss'


interface LayoutProps {
    children: ReactNode;
    headerInvisivel?: boolean;
    headerTitle?: string;
    drawer?: boolean;
    botaoVoltar?: boolean;
}

const Layout = (props: LayoutProps) => {
    return (

        <div className="layoutWrapper">
            {!props.headerInvisivel && <Navbar title={props.headerTitle ? props.headerTitle : ''} drawer={props.drawer} voltar={props.botaoVoltar}/>}
            {props.children}
        </div>
    )
}

export default Layout