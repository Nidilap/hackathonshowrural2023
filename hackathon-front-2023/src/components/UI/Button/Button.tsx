
import './Button.scss';
import { Button } from '@mui/material'

import React, { ReactNode } from 'react'

interface ButtonProps {
    children: ReactNode;
    onClick?: any;
    color?: any;

	//Todas as outras props.
	[x: string]: any;
}

const CustomButton = (props: ButtonProps) => {
  return (
    <Button variant="contained" color={props.color ? props.color : "primary"} {...props}>
        {props.children}
    </Button>
  )
}

export default CustomButton