import { useState, useEffect } from 'react';
import Layout from '../../../components/UI/Layout/Layout';
import './AgendaCadastroScreen.scss';
import CustomButton from '../../../components/UI/Button/Button';
import { TextField } from '@mui/material';
import Select from 'react-select';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { useAppDispatch } from '../../../store/configs/hooks';
import { fetchPessoas } from '../../../store/features/pessoa';


const AgendaCadastroScreen = () => {
    const dispatch = useAppDispatch();

    useEffect(() => {
        dispatch(fetchPessoas());
    }, [])

    const optionsNome: any = [
        { value: '1', label: 'Mateus' },
        { value: '2', label: 'Pedro' },
        { value: '3', label: 'Odete' },
    ];

    const optionsEndereco: any = [
        { value: '1', label: 'anchieta' },
        { value: '2', label: 'padre' },
        { value: '3', label: 'carvalho' },
    ];

    const [nomePessoa, setNomePessoa] = useState("");
    const [dataVisita, setDataVisita] = useState(new Date());
    const [enderecoVisita, setEnderecoVisita] = useState("");
    const [observacao, setObservacao] = useState("");

    const cadastrar = () => {

    }

    return (
        <Layout headerTitle='AGENDA' botaoVoltar={true}>
            <form className="inputsWrapperAgenda" onSubmit={cadastrar}>
                <Select
                    placeholder="Nome"
                    value={nomePessoa}
                    onChange={(valor: any) => {
                        setNomePessoa(valor);
                    }}
                    options={optionsNome}
                    className="inputAgenda"
                    styles={{ menuPortal: base => ({ ...base, zIndex: 99999 }) }}
                />

                <DateTimePicker
                    renderInput={(props: any) => <TextField {...props} />}
                    label="Data da Visita"
                    value={dataVisita}
                    onChange={(newValue: any) => {
                        setDataVisita(newValue);
                    }}
                    className="inputAgenda data"
                />
                <TextField
                    className="inputAgenda"
                    id="observacaoInput"
                    label="Observação"
                    variant="outlined"
                    value={observacao}
                    onChange={(e) => {
                        setObservacao(e.target.value);
                    }}
                    multiline
                    rows={2}
                    maxRows={4}
                />
                <CustomButton className="botaoAcessar" type="submit">
                    ACESSAR
                </CustomButton>
                {/* <Typography variant="caption" display="block" align="center">
                    <Checkbox
                        color="primary"
                        value="checkedA"
                        inputProps={{ "aria-label": "Checkbox A" }}
                        onChange={handleDisable}
                    />{" "}
                    Declaro que li e estou de acordo
                    <br /> com o decreto de lei LGPD
                </Typography> */}
            </form>
        </Layout>
    );
}

export default AgendaCadastroScreen;