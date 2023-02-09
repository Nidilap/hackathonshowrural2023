import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import './CustomModal.scss';

interface ModalProps {
  children: any;
  aberto: boolean;
  handleClose: any;
  estilo?: any;

  //Todas as outras props.
  [x: string]: any;
}

export default function CustomModal(props: ModalProps) {

  return (
    <Modal
      open={props.aberto}
      onClose={props.handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box className={"modalBox"} style={props.estilo}>
        {props.children}
      </Box>
    </Modal>
  );
}