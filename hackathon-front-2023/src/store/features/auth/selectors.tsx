import { createSelector } from '@reduxjs/toolkit';
import { RootState } from '../../configs/mainStore';

export const selectAuth = (state: RootState) => state.root.auth;

export const authSelector = createSelector(selectAuth, state => state);
