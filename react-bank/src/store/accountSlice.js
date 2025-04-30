import { createSlice } from "@reduxjs/toolkit";

const accountSlice = createSlice({
    name: "accounts",
    initialState: {
        accounts: [] // full account objects will be stored here
    },
    reducers: {
        setAccounts(state, action) {
            state.accounts = action.payload.accounts;
        }
    }
});

export const { setAccounts } = accountSlice.actions;

export default accountSlice.reducer;
