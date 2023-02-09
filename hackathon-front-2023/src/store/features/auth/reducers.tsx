//#region Imports
import {
	createReducer
} from '@reduxjs/toolkit';

import {
	autenticar,
	logout
} from './actions';



//#endregion Imports

//#region Reducers
export type AuthState = {
	authToken: string;
	idUsuario: string;
}

const estadoInicial: AuthState = {
	authToken: '',
	idUsuario: ''
};

export const authReducer = createReducer(estadoInicial, builder => {
	builder
		.addCase(autenticar.fulfilled, (state: any, action: any) => {
			if(action?.payload?.authToken) {
				state.authToken = action.payload.authToken;
				state.idUsuario = action.payload.idUsuario;
			}
		})
		.addCase(logout.fulfilled, (state: any) => {
			state = estadoInicial;
		});
});

//#region Reducers