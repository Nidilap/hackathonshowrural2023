import { useState, useEffect } from 'react';

import Layout from '../../components/UI/Layout/Layout';
import './MapVendas.scss'
import GoogleMapReact from 'google-map-react';
import Marker from '../../components/UI/Marker/Marker';
import CustomModal from '../../components/UI/CustomModal/CustomModal';

import {
    Room,
    Agriculture,
    CropFree,
    Add
} from '@mui/icons-material';
import { Button, Typography } from '@mui/material';
import { fetchPessoas } from '../../store/features/pessoa';
import { useAppDispatch, useAppSelector } from '../../store/configs/hooks';
import Pessoa from '../../models/redux/pessoa';
import CustomButton from '../../components/UI/Button/Button';

import { Link } from 'react-router-dom';

const MapVendasScreen = () => {
    const dispatch = useAppDispatch();

    const [longModal, setLongModal] = useState("");
    const [latitudeModal, setLatitudeModal] = useState("");

    const [tituloInfoFigura, setTituloInfoFigura] = useState("");
    const [localizacaoFigura, setLocalizacaoFigura] = useState("");
    const [tamanhoFigura, setTamanhoFigura] = useState("");


    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);


    const [open2, setOpen2] = useState(false);
    const handleOpen2 = () => setOpen2(true);
    const handleClose2 = () => setOpen2(false);

    const [marcadoresRender, setMarcadoresRender] = useState([]);

    const [dadosPessoas, setDadosPessoas] = useState<Pessoa[]>([]);


    const selector = useAppSelector((state: any) => state.root.pessoa);

    const defaultProps = {
        center: {
            lat: -24.9848725,
            lng: -53.3408382
        },
        zoom: 11
    };


    useEffect(() => {
        dispatch(fetchPessoas());
    }, [])

    const abrirModal = (item: Pessoa) => {
        setTituloInfoFigura(item?.razaoSocial);

        setLatitudeModal(item?.enderecos[0].localizacao.coordenada.x.toFixed(4).toString())
        setLongModal(item?.enderecos[0].localizacao.coordenada.y.toFixed(4).toString());
        // setLocalizacaoFigura(item?.enderecos[0].localizacao);
        // setEnderecoInfoFigura(item?.enderecos[0].endereco);
        setTamanhoFigura(item?.enderecos[0].areaHa.toFixed(2).toString() + " ha")
        handleOpen();
    }

    useEffect(() => {
        if (selector?.pessoas?.length > 0) {
            let dadosPessoasTemp: Pessoa[] = [];
            selector.pessoas.map((pessoa: Pessoa) => {
                dadosPessoasTemp.push(pessoa);
            })
            setDadosPessoas(dadosPessoasTemp);

        }
    }, [selector]);


    useEffect(() => {
        let dadosRetorno: any = [];
        (dadosPessoas ?? []).map((item: Pessoa) => {
            if (item?.enderecos?.length > 0) {
                let nomeParaMostrar = "PROSPECÇÃO";
                let cor = "#F12121";
                if (item?.razaoSocial !== "PESSOA DESCONHECIDA") {
                    cor = "#5158FA";
                    nomeParaMostrar = item.razaoSocial;
                } else if (item.idPessoa == 22 || item.idPessoa == 29 || item.idPessoa == 27 ) {
                    cor = "#EA630B";
                    nomeParaMostrar = "PROSPECTANDO";
                }


                dadosRetorno.push(
                    <Marker
                        lat={item?.enderecos[0].localizacao.coordenada.x}
                        lng={item?.enderecos[0].localizacao.coordenada.y}
                        title={`${nomeParaMostrar}`}
                        onClick={abrirModal.bind(this, item)}
                        color={cor}
                    />
                )
            }
        });
        setMarcadoresRender(dadosRetorno);


    }, [dadosPessoas])

    return (
        <Layout headerTitle="Mapa de Venda">
            <GoogleMapReact
                bootstrapURLKeys={{ key: "AIzaSyBgkk9MPQJavPiBUqwhQ8LpF0p9dmTO2qo" }}
                defaultCenter={defaultProps.center}
                defaultZoom={defaultProps.zoom}
                options={(map: any) => ({ mapTypeId: map.MapTypeId.SATELLITE })}
                style={{ width: '100%', height: '100%', position: 'relative', flex: 1 }}>
                {marcadoresRender.map((marcador) => {
                    return marcador;
                })}
            </GoogleMapReact>
            <CustomModal aberto={open} handleClose={handleClose} estilo={{ padding: 0, boxShadow: "0px 2px 10px 2px #333", border: 0, overflow: 'hidden' }} >
                <div className="figura">
                    <div className="figuraInfoWrapper">
                        <div className="latLong">
                            lat: {latitudeModal}, long: {longModal}
                        </div>
                        <div className="figuraInfo">
                            <div className="informacoesFigura">
                                <Typography className="tituloInfoFigura">Fazenda de {tituloInfoFigura}</Typography>
                                {/* <Typography className="enderecoInfoFigura">Endereço: {enderecoInfoFigura}</Typography> */}
                            </div>
                            <Button className="botao" onClick={handleOpen2}><Add sx={{ color: "rgba(255, 255, 255, 1)" }} fontSize={"large"} /></Button>
                        </div>
                    </div>
                </div>
                <div className="informacoesAbaixoWrapper">
                    <div className="informacoesAbaixo">
                        <Typography className="subtituloModal"><Room />&nbsp;Localização</Typography>
                        <Typography className="infosAdicionaisModal">Cascavel</Typography>
                    </div>
                    <div className="informacoesAbaixo">
                        <Typography className="subtituloModal"><Agriculture />&nbsp;&nbsp;Agricultável</Typography>
                        <Typography className="infosAdicionaisModal">12ha</Typography>
                    </div>
                </div>
                <div className="informacoesAbaixoWrapper">
                    <div className="informacoesAbaixo">
                        <Typography className="subtituloModal"><CropFree />&nbsp;Reserva</Typography>
                        <Typography className="infosAdicionaisModal">3ha</Typography>
                    </div>
                    <div className="informacoesAbaixo">
                        <Typography className="subtituloModal"><CropFree />&nbsp;Tamanho</Typography>
                        <Typography className="infosAdicionaisModal">{tamanhoFigura}</Typography>
                    </div>
                </div>

            </CustomModal>


            <CustomModal aberto={open2} handleClose={handleClose2} className="modal2">
                <Typography id="modal-modal-title" variant="h6" component="h2" className="centralizar">
                    Selecione a ação abaixo
                </Typography>
                <div className="centralizarBotoes">
                    <CustomButton onClick={() => { }} className="botaoCentralizar">Mais informações sobre a fazenda</CustomButton>
                    <CustomButton component={Link} to="/clientecadastro" className="botaoCentralizar">Adicionar à carteira de cliente</CustomButton>  
                </div>
            </CustomModal>
        </Layout>
    );
}

export default MapVendasScreen;