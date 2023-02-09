import { styled } from "@mui/material/styles";

// Material UI Components
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import {
  Drawer,
  Box,
  IconButton,
  CssBaseline,
  Divider,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  ListItemButton,
  Typography,
  useTheme
} from "@mui/material";

/* Configurações de menu */
import { useGetMenuItens } from "../../../../utils/MenuItemsUtils";
import MenuItem from "../../../../models/general/MenuItem";
import { Link } from "react-router-dom";
import CustomButton from "../../Button/Button";
import { logout } from "../../../../store/features/auth/actions";
import { useAppDispatch } from "../../../../store/configs/hooks";

// Estilos
import './MUIDrawer.scss';


const DrawerHeader = styled("div")(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  padding: theme.spacing(0, 1),
  ...theme.mixins.toolbar,
  justifyContent: "flex-end",
}));

type DrawerProps = {
  isOpen: boolean;
  handleDrawerClose: any;
};

export default function MUIDrawer(props: DrawerProps) {
  const theme = useTheme();
  const dispatch = useAppDispatch();

  const { itensMenu } = useGetMenuItens();

  const deslogar = () => {
    dispatch(logout());
  }

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <Drawer
        variant="persistent"
        anchor="left"
        open={props.isOpen}
        PaperProps={{
          sx: { width: "60%" },
        }}
      >
        <DrawerHeader>
          <Typography variant="h6" noWrap color="primary">
            SysAgro
          </Typography>
          <IconButton onClick={props.handleDrawerClose}>
            {theme.direction === "ltr" ? (
              <ChevronLeftIcon />
            ) : (
              <ChevronRightIcon />
            )}
          </IconButton>
        </DrawerHeader>
        <Divider />
        <List
          component="div"
        >
          {
            itensMenu.map((item: MenuItem) => {
              return (
                <ListItem key={item.idMenuItem} disablePadding>
                  <ListItemButton component={Link} to={item.url}>
                    <ListItemIcon>
                      {item.icone}
                    </ListItemIcon>
                    <ListItemText primary={item.title} />
                  </ListItemButton>
                </ListItem>
              )
            })
          }
        </List>
        <Divider />
        <CustomButton className="botaoAcessar" onClick={deslogar}>
          DESLOGAR
        </CustomButton>

      </Drawer>
    </Box>
  );
}
