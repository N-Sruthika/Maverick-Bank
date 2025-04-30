import { configureStore } from "@reduxjs/toolkit";

import accountSlice from './accountSlice'; 

const store = configureStore({
  reducer: {
    
    accounts: accountSlice
   
  }
});

export default store;
