import { useState } from 'react';

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

const MapVendasScreen = () => {

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);


  const defaultProps = {
    center: {
      lat: -24.9848725,
      lng: -53.3408382
    },
    zoom: 10
  };

  const abrirModal = (item?: any) => {
    handleOpen();
  }


  
  return (
    <Layout headerTitle="Mapa de Venda">
      <GoogleMapReact
        bootstrapURLKeys={{ key: "AIzaSyBgkk9MPQJavPiBUqwhQ8LpF0p9dmTO2qo" }}
        defaultCenter={defaultProps.center}
        defaultZoom={defaultProps.zoom}
        options={(map: any) => ({ mapTypeId: map.MapTypeId.SATELLITE })}
        style={{ width: '100%', height: '100%', position: 'relative', flex: 1 }}
      >
        <Marker
          lat={-24.987458333333333}
          lng={-53.36433888888889}
          onClick={abrirModal.bind(this)}
        />
      </GoogleMapReact>
      <CustomModal aberto={open} handleClose={handleClose} estilo={{ padding: 0, boxShadow: "0px 2px 10px 2px #333", border: 0, overflow: 'hidden' }} >
        <div className="figura">
          <div className="figuraInfoWrapper">
            <div className="figuraInfo">
              <div className="informacoesFigura">
                <Typography className="tituloInfoFigura">Fazenda X</Typography>
                <Typography className="enderecoInfoFigura">Endereço: R. Antonina, 2397 Centro Cascavel - PR</Typography>
              </div>
              <Button className="botao" onProgress={() => { }}><Add sx={{ color: "rgba(255, 255, 255, 1)" }} fontSize={"large"} /></Button>

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
            <Typography className="infosAdicionaisModal infoAdicionalAgricultavel">SIM</Typography>
          </div>
          <div className="informacoesAbaixo">
            <Typography className="subtituloModal"><CropFree />&nbsp;Tamanho</Typography>
            <Typography className="infosAdicionaisModal">20ht</Typography>
          </div>
        </div>
      </CustomModal>
    </Layout>
  );
}

export default MapVendasScreen;