
import { ReactNode } from 'react';
import HomeIcon from '../../components/UI/HomeIcon/HomeIcon';
import Layout from '../../components/UI/Layout/Layout';
import MenuItem from '../../models/general/MenuItem';
import { useAppSelector } from '../../store/configs/hooks';
import { useGetMenuItens } from '../../utils/MenuItemsUtils';

import './Home.scss'

// Component
const Home = () => {
    // Variables
    const { itensMenu } = useGetMenuItens();
    const selector = useAppSelector((state) => state);

    // General
    const renderButtons = (): ReactNode => {
        return itensMenu.filter(item => item.title != "Início").map(item => {
            return (<HomeIcon key={`${item.idMenuItem}`} menuItem={item} />)
        });
    }

    // TSX
    return (
        <Layout headerTitle="Página Inicial">
            <div className="iconesWrapper">
                {renderButtons()}
            </div>
        </Layout>
    );
}

// Exports
export default Home;