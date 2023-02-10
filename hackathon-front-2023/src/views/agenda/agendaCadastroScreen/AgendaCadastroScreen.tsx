import { useState, useEffect } from 'react';
import Layout from '../../../components/UI/Layout/Layout';
import './AgendaCadastroScreen.scss';
import CustomButton from '../../../components/UI/Button/Button';
import { TextField } from '@mui/material';
import AsyncSelect from 'react-select/async';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { useAppDispatch, useAppSelector } from '../../../store/configs/hooks';
import { cadastrarVisita, fetchPessoas } from '../../../store/features/pessoa';
import Pessoa from '../../../models/redux/pessoa';

// Alert
import { toast } from "react-toastify";

const AgendaCadastroScreen = () => {
    const dispatch = useAppDispatch();

    useEffect(() => {

    }, [])


    const selector = useAppSelector((state: any) => state.root.pessoa);
    
    let optionsNome: any = [];

    const [nomePessoa, setNomePessoa] = useState<any>({value: "", label: ""});
    const [dataVisita, setDataVisita] = useState(new Date());
    const [enderecoVisita, setEnderecoVisita] = useState("");
    const [observacao, setObservacao] = useState("");

    useEffect(() => {
        if(selector.pessoas && selector.pessoas.length > 0) {
            let pessoaAtual: Pessoa = selector.pessoas.find((pessoa: Pessoa) => pessoa.idPessoa == nomePessoa.value)
            if(pessoaAtual?.enderecos && pessoaAtual.enderecos.length > 0) {
                setEnderecoVisita(pessoaAtual.enderecos[0].car)
            }
            
        }
    }, [nomePessoa]);

    const getPessoasClientes = async (input: any) => {
        await dispatch(fetchPessoas());
        let optionsNome: any = [];
        if (selector.pessoas && selector.pessoas.length > 0) {
            selector.pessoas.map((pessoa: Pessoa) => {
                if (pessoa.razaoSocial !== "PESSOA DESCONHECIDA") {
                    optionsNome.push({ value: pessoa.idPessoa, label: pessoa.razaoSocial })
                }
            })
        }
        return optionsNome;
    }

    const cadastrar = (e: any) => {
        e.preventDefault();
        if (nomePessoa && dataVisita && enderecoVisita) {
            let pessoaAtual: Pessoa = selector.pessoas.find((pessoa: Pessoa) => pessoa.idPessoa == nomePessoa.value)
            if(pessoaAtual?.enderecos && pessoaAtual.enderecos.length > 0) {
                
                dispatch(cadastrarVisita({ 
                    idPessoa: pessoaAtual.idPessoa,
                    idEndereco: pessoaAtual.enderecos[0].idEndereco,
                    dataAgendada: dataVisita,
                    observacao: observacao
                }));
            }

        } else {
          toast.error("Preencha os dados para cadastrar!", {
            position: "top-center",
          });
        }
    }

    return (
        <Layout headerTitle='AGENDA' botaoVoltar={true}>
            <form className="inputsWrapperAgenda" onSubmit={cadastrar}>
                <AsyncSelect
                    placeholder="Nome"
                    value={nomePessoa}
                    onChange={(valor: any) => {
                        setNomePessoa(valor);
                    }}
                    options={optionsNome}
                    className="inputAgenda"
                    defaultOptions
                    loadOptions={(e) => getPessoasClientes(e)}
                    
                    styles={{
                        // Fixes the overlapping problem of the component
                        menu: provided => ({ ...provided, zIndex: 9999 })
                    }}

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
                    id="enderecoInput"
                    label="Endereço"
                    variant="outlined"
                    value={enderecoVisita}
                    onChange={(e) => {
                        setEnderecoVisita(e.target.value);
                    }}
                    disabled={true}
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
                    AGENDAR
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