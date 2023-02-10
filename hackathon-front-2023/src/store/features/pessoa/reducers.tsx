//#region Imports
import {
	createReducer
} from '@reduxjs/toolkit';
import Pessoa from '../../../models/redux/pessoa';
import Visita from '../../../models/redux/visita';

import {
	fetchPessoas
} from './actions';



//#endregion Imports

//#region Reducers
export type PessoaState = {
	pessoas: Pessoa[];
}

const estadoInicial: PessoaState = {
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