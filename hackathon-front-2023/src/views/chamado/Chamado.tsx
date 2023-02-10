import Layout from '../../components/UI/Layout/Layout';
import './Chamado.scss';
import CustomButton from '../../components/UI/Button/Button';
import { TextField } from '@mui/material';

// Alert
import { toast } from "react-toastify";

const Chamado = () => {
    

    return (
        <Layout headerTitle='ASSISTÊNCIA' botaoVoltar={true}>
            <form className="inputsWrapperAgenda" onSubmit={() => { }}>


                <TextField
                    className="inputAgenda"
                    id="nomeInput"
                    label="Data"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="telefoneInput"
                    label="Hora"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="enderecoInput"
                    label="Motivo"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="enderecoNascimentoInput"
                    label="Observação"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                    multiline
                    rows={2}
                    maxRows={4}
                />
                <CustomButton className="botaoAcessar" type="submit">
                    COMUNICAR
                </CustomButton>
            </form>
        </Layout>
    );
}

export default Chamado;