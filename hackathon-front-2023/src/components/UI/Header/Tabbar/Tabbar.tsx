import React, { ReactNode } from 'react';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import { Link, useLocation } from 'react-router-dom';
import { useGetMenuItens } from '../../../../utils/MenuItemsUtils';
import MenuItem from '../../../../models/general/MenuItem';

import './Tabbar.scss';

// Component
export default function MainTabbar() {
    const [value, setValue] = React.useState(0);

    const location = useLocation();
    console.log(location.pathname);

    const { itensMenu } = useGetMenuItens();

    // Other TSX
    const renderButtons = (): ReactNode => {
        return itensMenu.filter(item => item.idMenuItem <= 4).map(item => {
            return (<Tab className="tabItem" label={item.title} icon={item.icone} component={Link} to={item.url} />);
        });
    }

    // TSX
    return (
        <Box sx={{ width: '100%', bgcolor: '#029049', position: 'fixed', bottom: 0, borderTop: '1px solid #ccc' }}>
            <Tabs value={value} centered>
                {renderButtons()}
            </Tabs>
        </Box>
    );
}