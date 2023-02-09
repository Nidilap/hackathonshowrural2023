import { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import Layout from '../../../components/UI/Layout/Layout';
import { fetchLotes } from '../../../store/actions/lote';
import { useAppDispatch } from '../../../store/configs/hooks';

const LoteScreen = () => {
    const dispatch = useAppDispatch();

    useEffect(() => {
        dispatch(fetchLotes());
    }, [])

    return (
        <Layout botaoVoltar={true}>
            <h1>Modal</h1>
            <Link to="/">retornar a página inicial</Link>
        </Layout>
    );
}

export default LoteScreen;