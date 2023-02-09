import * as React from 'react';
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import { Link } from 'react-router-dom';
import { useGetMenuItens } from '../../../../utils/MenuItemsUtils';
import MenuItem from '../../../../models/general/MenuItem';

export default function MainTabbar() {
  const [value, setValue] = React.useState(0);

  const { itensMenu } = useGetMenuItens();

  return (
    <Box sx={{ width: '100%', bgcolor: 'background.paper', position: 'absolute', bottom: 0}}>
      <Tabs value={value} centered>
          {
            itensMenu.map((item: MenuItem) => {
              return (
                <Tab label={item.title}  icon={item.icone} component={Link} to={item.url}/>
              )
            })
          }
      </Tabs>
    </Box>
  );
}