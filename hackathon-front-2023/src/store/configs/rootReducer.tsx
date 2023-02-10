import { combineReducers } from "redux";

//Redux - reducers
import { authReducer } from "../features/auth";
import storage from 'redux-persist/lib/storage' // defaults to localStorage for web
import { pessoaReducer } from "../features/pessoa";

import { persistReducer } from "redux-persist";
import { visitaReducer } from "../features/visita";

const authConfig = {
  key: "auth",
  storage: storage,
  blacklist: ["authToken", "tentouAutoLogin"],
};

const rootReducer = combineReducers({
  auth: persistReducer(authConfig, authReducer),
  pessoa: pessoaReducer,
  visita: visitaReducer
} as Istore);

interface Istore {
  auth: any;
  pessoa: any;
  visita: any;
}

export type { Istore };

export default rootReducer;