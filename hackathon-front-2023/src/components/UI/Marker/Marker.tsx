// Foreign imports
import React, { ReactNode, Fragment } from "react";
import { Room } from '@mui/icons-material';

// CSS
import './Marker.scss';

// Component
const Marker = (props: any) => {
    // General
    const getRoomCSS = () => {
        return {
            color: (props.color || "#F12121")
        };
    }

    // Other TSX
    const renderTitle = (): ReactNode => {
        if (props.title) {
            return (
                <div className="container-titulo">
                    <span className="titulo">{props.title}</span>
                </div>
            );
        } else {
            return null;
        }
    }

    // TSX
    return (
        <div className="container-marcacao">
            <Room
                sx={getRoomCSS()}
                fontSize="large"
                onClick={props.onClick}
            />
            {renderTitle()}
        </div>
    );
}

// Exports
export default Marker;