import Typography from '@mui/material/Typography';
import {useState} from 'react';
import { Link } from "react-router-dom";
import CustomButton from '../../components/UI/Button/Button';
import CustomModal from "../../components/UI/CustomModal/CustomModal";
import Layout from '../../components/UI/Layout/Layout';

const Modal = () => {

    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <Layout botaoVoltar={true}>
            <h1>Modal</h1>
            <CustomButton onClick={handleOpen}>Open modal</CustomButton>
            <CustomModal aberto={open} handleClose={handleClose} >
                
                <Typography id="modal-modal-title" variant="h6" component="h2">
                    Text in a modal
                </Typography>
                <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
                </Typography>
            </CustomModal>
            <Link to="/">retornar a página inicial</Link>
        </Layout>
    );
}

export default Modal;