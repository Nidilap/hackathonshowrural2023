import { ReactNode, useEffect, useState } from 'react';
import Layout from '../../../components/UI/Layout/Layout';
import { ArrowForwardIos, ArrowBackIosNew, Add } from '@mui/icons-material';
import './AgendaScreen.scss';
import HoraAgenda from '../../../components/UI/HoraAgenda/HoraAgenda';
import CustomButton from '../../../components/UI/Button/Button';
import { useAppDispatch, useAppSelector } from '../../../store/configs/hooks';
import { fetchVisitasByUsuario } from '../../../store/features/visita';
import Visita from '../../../models/redux/visita';

import { Link } from 'react-router-dom';
import { fetchPessoas } from '../../../store/features/pessoa';
import Pessoa from '../../../models/redux/pessoa';
import { AnyIfEmpty } from 'react-redux';

// Component
const AgendaScreen = () => {

    const [diasAgenda, setDiasAgenda] = useState<any>([]);
    const dispatch = useAppDispatch();
    const selector = useAppSelector((state: any) => state.root.visita);

    const tratarDados = async () => {
        iniciarDias();
    }

    useEffect(() => {
         dispatch(fetchVisitasByUsuario());
    }, [])
    // Constructor
    useEffect(() => {
        tratarDados()
    }, [JSON.stringify(selector)])


    // General
    const iniciarDias = (): void => {
        let dias = [];
        for (let hora = 7; hora <= 23; hora++) {
            dias.push(renderHoraAgenda(hora));
        }
        console.log("Sel > ", selector);
        setDiasAgenda(dias);
    }

    // Other TSX
    const renderHoraAgenda = (hora: number): ReactNode => {
        return (
            <HoraAgenda key={Math.random()} hora={hora < 10 ? `0${hora}` : hora.toString()}>
                {renderVisitaAgenda(hora)}
            </HoraAgenda>
        );
    }

    const renderVisitaAgenda = (hora: number): ReactNode => {
        let visita: any = selector.visitasDoUsuario.filter((visita: Visita) => {
            visita.dataAgendada = (visita.dataAgendada ? new Date(visita.dataAgendada) : null);
            return visita.dataAgendada
                && visita.dataAgendada.getDate() === 10
                && visita.dataAgendada.getMonth() === 1
                && visita.dataAgendada.getFullYear() === 2023
                && visita.dataAgendada.getHours() >= hora
                && visita.dataAgendada.getHours() < (hora + 1)
        })[0];
        if (visita) {
            return <Link to="/mapaVendas" className='objetoVisita'>{visita.nomePessoa}</Link>;
            
        } else {
            return null;
        }
    }

    // TSX
    return (
        <Layout headerTitle='AGENDA'>
            <div className="agendaCardDia">
                <div className="agendaSetaWrapper">
                    <ArrowBackIosNew sx={{ color: "rgba(255, 255, 255, 1)" }} />
                    <div className='diaAgenda'>SEXTA-FEIRA - 10 FEV 2023</div>
                    <ArrowForwardIos sx={{ color: "rgba(255, 255, 255, 1)" }} />
                </div>
                <div className="horasAgendas">
                    {diasAgenda}
                </div>
            </div>
            <CustomButton className="botaoAgendamento" component={Link} to="/agendacadastro">AGENDAMENTO <Add sx={{ color: "rgba(255, 255, 255, 1)" }} fontSize={"large"} /></CustomButton>
        </Layout>
    );
}

// Exports
export default AgendaScreen;