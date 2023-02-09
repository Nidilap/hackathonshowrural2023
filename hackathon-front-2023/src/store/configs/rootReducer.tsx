import { combineReducers } from "redux";

//Redux - reducers
import { authReducer } from "../features/auth";
import storage from 'redux-persist/lib/storage' // defaults to localStorage for web

import { persistReducer } from "redux-persist";

const authConfig = {
  key: "auth",
  storage: storage,
  blacklist: ["authToken", "tentouAutoLogin"],
};

const rootReducer = combineReducers({
  auth: persistReducer(authConfig, authReducer)
} as Istore);

interface Istore {
  auth: any;
}

export type { Istore };

export default rootReducer;