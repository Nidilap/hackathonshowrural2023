import thunk from 'redux-thunk';

import { persistReducer, persistStore } from 'redux-persist';

import storage from 'redux-persist/lib/storage' // defaults to localStorage for web
import { createReduxHistoryContext } from "redux-first-history";
import { createBrowserHistory } from "history";

import rootReducer from './rootReducer';
import {
    Action,
    combineReducers,
    configureStore,
    ThunkAction,
} from '@reduxjs/toolkit';



// Configura o route history para usar dentro do redux
const {
    createReduxHistory,
    routerMiddleware,
    routerReducer
  } = createReduxHistoryContext({ history: createBrowserHistory() });


// Configurações de persistencia para salvar os dados offline
const persistConfig = {
    key: 'root',
    storage: storage,
    blacklist: ['auth'],
}


// Configurações finais da store
const store = configureStore(
    {
        reducer: persistReducer(persistConfig, combineReducers({root: rootReducer, router: routerReducer})),
        devTools: process.env.NODE_ENV !== 'production',
        middleware: (getDefaultMiddleware) =>
            getDefaultMiddleware({
                serializableCheck: false
            }).concat([thunk, routerMiddleware])
    }
)
const persistor = persistStore(store);
const history = createReduxHistory(store);

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch;

export type AppThunk<ReturnType = void> = ThunkAction<
    ReturnType,
    RootState,
    unknown,
    Action<string>
>;

export { store, persistor, history };