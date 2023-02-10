import { createSelector } from '@reduxjs/toolkit';
import { RootState } from '../../configs/mainStore';

export const selectVisita = (state: RootState) => state.root.visita;

export const visitaSelector = createSelector(selectVisita, state => state);
