import { createTheme } from '@mui/material';
import variaveis from '../constants/colors.scss';

const theme = createTheme({
    palette: {
        primary: {
            main: variaveis.corPrimaria
        },
        secondary: {
            main: variaveis.corSecundaria
        },
        error: {
            main: variaveis.danger
        },
    },

});

export default theme;