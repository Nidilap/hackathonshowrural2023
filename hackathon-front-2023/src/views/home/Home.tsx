
import { Typography } from '@mui/material';
import { ReactNode } from 'react';
import CustomButton from '../../components/UI/Button/Button';
import HomeIcon from '../../components/UI/HomeIcon/HomeIcon';
import Layout from '../../components/UI/Layout/Layout';
import MenuItem from '../../models/general/MenuItem';
import { useAppDispatch, useAppSelector } from '../../store/configs/hooks';
import { logout } from '../../store/features/auth';
import { useGetMenuItens } from '../../utils/MenuItemsUtils';

import './Home.scss'

// Component
const Home = () => {
    // Variables
    const { itensMenu } = useGetMenuItens();
    const selector = useAppSelector((state) => state);

    const dispatch = useAppDispatch();
    
    const deslogar = () => {
      dispatch(logout());
    }

    // Other TSX
    const renderButtons = (): ReactNode => {
        return itensMenu.filter(item => item.title != "Início").map(item => {
            return (<HomeIcon key={`${item.idMenuItem}`} menuItem={item} />)
        });
    }

    // TSX
    return (
        <Layout headerTitle="Página Inicial">
            <Typography className="textoBemVindo">Seja bem vindo!</Typography>
            <div className="iconesWrapper">
                {renderButtons()}
            </div>
            <div className='linhaDeslogar'></div>
            <CustomButton onClick={deslogar}>DESLOGAR</CustomButton>
        </Layout>
    );
}

// Exports
export default Home;