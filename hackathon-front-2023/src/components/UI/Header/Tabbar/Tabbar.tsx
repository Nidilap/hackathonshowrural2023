import * as React from 'react';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import { Link, useLocation } from 'react-router-dom';
import { useGetMenuItens } from '../../../../utils/MenuItemsUtils';
import MenuItem from '../../../../models/general/MenuItem';

import './Tabbar.scss';

export default function MainTabbar() {
  const [value, setValue] = React.useState(0);

  const location = useLocation();
  console.log(location.pathname);

  const { itensMenu } = useGetMenuItens();

  return (
    <Box sx={{ width: '100%', bgcolor: '#029049', position: 'fixed', bottom: 0, borderTop: '1px solid #ccc'}}>
      <Tabs value={value} centered>
          {
            itensMenu.map((item: MenuItem) => {
              return (
                <Tab className="tabItem" label={item.title}  icon={item.icone} component={Link} to={item.url}/>
              )
            })
          }
      </Tabs>
    </Box>
  );
}