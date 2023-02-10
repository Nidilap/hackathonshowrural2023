import Layout from '../../components/UI/Layout/Layout';
import './ClienteCadastro.scss';
import CustomButton from '../../components/UI/Button/Button';
import { TextField } from '@mui/material';

// Alert
import { toast } from "react-toastify";

const ClienteCadastro = () => {
    // const dispatch = useAppDispatch();


    // const selector = useAppSelector((state: any) => state.root.pessoa);

    // let optionsNome: any = [];

    // const [nomePessoa, setNomePessoa] = useState<any>({value: "", label: ""});
    // const [dataVisita, setDataVisita] = useState(new Date());
    // const [enderecoVisita, setEnderecoVisita] = useState("");
    // const [observacao, setObservacao] = useState("");

    // useEffect(() => {
    //     if(selector.pessoas && selector.pessoas.length > 0) {
    //         let pessoaAtual: Pessoa = selector.pessoas.find((pessoa: Pessoa) => pessoa.idPessoa == nomePessoa.value)
    //         if(pessoaAtual?.enderecos && pessoaAtual.enderecos.length > 0) {
    //             setEnderecoVisita(pessoaAtual.enderecos[0].car)
    //         }

    //     }
    // }, [nomePessoa]);

    // const getPessoasClientes = async (input: any) => {
    //     await dispatch(fetchPessoas());
    //     let optionsNome: any = [];
    //     if (selector.pessoas && selector.pessoas.length > 0) {
    //         selector.pessoas.map((pessoa: Pessoa) => {
    //             if (pessoa.razaoSocial !== "PESSOA DESCONHECIDA") {
    //                 optionsNome.push({ value: pessoa.idPessoa, label: pessoa.razaoSocial })
    //             }
    //         })
    //     }
    //     return optionsNome;
    // }

    // const cadastrar = (e: any) => {
    //     e.preventDefault();
    //     if (nomePessoa && dataVisita && enderecoVisita) {
    //         let pessoaAtual: Pessoa = selector.pessoas.find((pessoa: Pessoa) => pessoa.idPessoa == nomePessoa.value)
    //         if(pessoaAtual?.enderecos && pessoaAtual.enderecos.length > 0) {

    //             dispatch(cadastrarVisita({ 
    //                 idPessoa: pessoaAtual.idPessoa,
    //                 idEndereco: pessoaAtual.enderecos[0].idEndereco,
    //                 dataAgendada: dataVisita,
    //                 observacao: observacao
    //             }));
    //         }

    //     } else {
    //       toast.error("Preencha os dados para cadastrar!", {
    //         position: "top-center",
    //       });
    //     }
    // }

    return (
        <Layout headerTitle='NOVO CLIENTE' botaoVoltar={true}>
            <form className="inputsWrapperAgenda" onSubmit={() => { }}>


                <TextField
                    className="inputAgenda"
                    id="nomeInput"
                    label="Nome"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="telefoneInput"
                    label="Telefone"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="enderecoInput"
                    label="Endereço"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="enderecoNascimentoInput"
                    label="Nascimento"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <TextField
                    className="inputAgenda"
                    id="emailInput"
                    label="E-mail"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />

                <TextField
                    className="inputAgenda"
                    id="cpfInput"
                    label="CPF"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />

                <TextField
                    className="inputAgenda"
                    id="sexi"
                    label="Sexo"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                
                <TextField
                    className="inputAgenda"
                    id="car"
                    label="CAR"
                    variant="outlined"
                    value={""}
                    onChange={() => { }}
                />
                <CustomButton className="botaoAcessar" type="submit">
                    CADASTRAR
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

export default ClienteCadastro;