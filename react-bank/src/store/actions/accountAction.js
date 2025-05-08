import axios from "axios";
import { setAccounts } from "../accountSlice";

const fetchAccounts = () => async (dispatch) => {
    const resp = await axios.get("http://localhost:8081/api/accounts");
    dispatch(setAccounts({ accounts: resp.data }));
};

export default fetchAccounts;
