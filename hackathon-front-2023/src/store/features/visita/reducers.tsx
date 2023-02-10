//#region Imports
import {
	createReducer
} from '@reduxjs/toolkit';
import Pessoa from '../../../models/redux/pessoa';
import Visita from '../../../models/redux/visita';

import {
	fetchVisitasByUsuario
} from './actions';



//#endregion Imports

//#region Reducers
export type VisitaState = {
	visitasDoUsuario: Visita[];
}

const estadoInicial: VisitaState = {
	visitasDoUsuario: []
};

export const visitaReducer = createReducer(estadoInicial, builder => {
	builder
		.addCase(fetchVisitasByUsuario.fulfilled, (state: any, action: any) => {
			if(action?.payload?.visitasDoUsuario) {
				state.visitasDoUsuario = action.payload.visitasDoUsuario;
			}
		})
});

//#region Reducers