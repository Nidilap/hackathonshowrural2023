import React from 'react';
import { Box, Typography } from '@mui/material'

import MenuItem from '../../../models/general/MenuItem';
import './HomeIcon.scss';
import { Link, useNavigate } from 'react-router-dom';

interface ButtonProps {
    menuItem: MenuItem;

    //Todas as outras props.
    [x: string]: any;
}

const HomeIcon = (props: ButtonProps) => {
    const navigate = useNavigate();
    const icone = React.cloneElement(props.menuItem.icone, {
        // Using clsx to combine the new class name with any existing ones that may already be on the element
        className: "icone"
    });
    // const Icone: any = props.menuItem.icone;
    return (
        <Box component={Link} className="boxWrapper" to={props.menuItem.url}>
            <div className="iconeWrapper">
                {icone}
            </div>
            <Typography className="tituloIcone">
                {props.menuItem.title}
            </Typography>
        </Box>
    )
}

export default HomeIcon