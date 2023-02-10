import * as React from "react";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import MUIDrawer from "../Drawer/MUIDrawer";

import './Navbar.scss';


import { Link } from 'react-router-dom';

// Images
import logo from '../../../../assets/images/LogoIcon.png';

// Estilos
import { AppBar, useTheme } from "@mui/material";

import { useNavigate } from 'react-router-dom';

interface NavBarProps {
	title?: string;
	drawer?: boolean;
	voltar?: boolean;
}

export default function Navbar(props: NavBarProps) {
    const navigate  = useNavigate();
	const theme = useTheme();

	const [open, setOpen] = React.useState(false);

	const handleDrawerOpen = () => {
		setOpen(true);
	};

	const handleDrawerClose = () => {
		setOpen(false);
	};	



	return (
		<>
			<AppBar 
				position="static"
				className="appbarHeight"
			>
				<Toolbar className="toolbar">
					{props.voltar && 
						<IconButton
							onClick={() => navigate(-1)}
							edge="start"
							color="inherit"
							aria-label="menu"
							sx={{ mr: 2 }}
						>
							<ArrowBackIosIcon />
						</IconButton>
					}
					{props.drawer && 
					<IconButton
						size="large"
						edge="start"
						color="inherit"
						aria-label="menu"
						sx={{ mr: 2 }}
						onClick={handleDrawerOpen}
					>
						<MenuIcon />
					</IconButton>
					}
					{props.title && (
						<Typography variant="h6" noWrap className="headerTitle">
							<img src={logo} width={50} height={40} alt="Logo" style={{marginRight: 10}} />
							{props.title}
						</Typography>
					)}
				</Toolbar>
			</AppBar>
			<MUIDrawer isOpen={open} handleDrawerClose={handleDrawerClose} />
		</>
	);
}
