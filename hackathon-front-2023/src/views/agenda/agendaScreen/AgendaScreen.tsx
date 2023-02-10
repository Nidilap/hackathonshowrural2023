import Layout from '../../../components/UI/Layout/Layout';
import { ArrowForwardIos, ArrowBackIosNew, Add} from '@mui/icons-material';
import './AgendaScreen.scss';
import HoraAgenda from '../../../components/UI/HoraAgenda/HoraAgenda';
import CustomButton from '../../../components/UI/Button/Button';



const AgendaScreen = () => {
    // const dispatch = useAppDispatch();

    // useEffect(() => {
    //     dispatch(fetchLotes());
    // }, [])

    const dias = [];
    for (let i = 7; i <= 23; i++) {
        dias.push(<HoraAgenda hora={i < 10 ? `0${i}` : i.toString()}><div>teste</div></HoraAgenda>);
    }

    return (
        <Layout headerTitle='AGENDA'>
            <div className="agendaCardDia">
                <div className="agendaSetaWrapper">
                    <ArrowBackIosNew sx={{ color: "rgba(255, 255, 255, 1)" }}/>
                    <div className='diaAgenda'>SEXTA-FEIRA - 10 FEV 2023</div>
                    <ArrowForwardIos sx={{ color: "rgba(255, 255, 255, 1)" }} />
                </div>
                <div className="horasAgendas">
                    {dias}
                </div>
            </div>
            <CustomButton className="botaoAgendamento" onClick={() => {}}>AGENDAMENTO <Add sx={{ color: "rgba(255, 255, 255, 1)" }} fontSize={"large"} /></CustomButton>
        </Layout>
    );
}

export default AgendaScreen;