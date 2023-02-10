import { createSelector } from '@reduxjs/toolkit';
import { RootState } from '../../configs/mainStore';

export const selectPessoa = (state: RootState) => state.root.pessoa;

export const pessoaSelector = createSelector(selectPessoa, state => state);
