import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.scss';
import App from './views/App';

import { store, persistor } from './store/configs/mainStore';
import { PersistGate } from 'redux-persist/integration/react'
import { Provider } from 'react-redux';

// Configurações do Toast
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

//MATERIAL UI 
import theme from './assets/styles/materialuitheme';
import { ThemeProvider } from '@mui/material';
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment';

// PWA Configs
import * as serviceWorkerRegistration from './serviceWorkerRegistration';
import reportWebVitals from './reportWebVitals';

//Locale
import { LocalizationProvider } from '@mui/x-date-pickers';
import 'moment/locale/pt-br'

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const locale = 'pt-br';

root.render(
    <ThemeProvider theme={theme}>
      <LocalizationProvider dateAdapter={AdapterMoment} locale={locale}>
        <Provider store={store}>
          <PersistGate loading={null} persistor={persistor}>
            <App />
            <ToastContainer />
          </PersistGate>
        </Provider>
      </LocalizationProvider>
    </ThemeProvider>
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://cra.link/PWA
serviceWorkerRegistration.register();

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
