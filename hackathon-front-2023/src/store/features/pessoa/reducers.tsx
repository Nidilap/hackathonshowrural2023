//#region Imports
import {
	createReducer
} from '@reduxjs/toolkit';
import Pessoa from '../../../models/redux/pessoa';

import {
	fetchPessoas
} from './actions';



//#endregion Imports

//#region Reducers
export type AuthState = {
	pessoas: Pessoa[];
}

const estadoInicial: AuthState = {
	pessoas: []
};

export const pessoaReducer = createReducer(estadoInicial, builder => {
	builder
		.addCase(fetchPessoas.fulfilled, (state: any, action: any) => {
			if(action?.payload?.pessoas) {
				state.pessoas = action.payload.pessoas;
			}
		})
});

//#region Reducers