import { Route, Routes } from "react-router"
import Login from "./components/auth/Login"
import Account from "./components/customer/Account"
import Beneficiary from "./components/customer/Beneficiary"
import Dashboard from "./components/customer/Dashboard"
import Profile from "./components/customer/Profile"
import ServiceRequests from "./components/customer/ServiceRequests"
import CustomerSignup from "./components/customer/CustomerSignUp"
import Home from "./components/customer/Home"
import Transaction from "./components/customer/Transaction"

import { useDispatch } from "react-redux"
import { useEffect } from "react"
import  fetchAccounts  from "./store/actions/accountAction" // Import the action to fetch accounts

function App() {

  const dispatch = useDispatch(); // Get dispatch function

  useEffect(() => {
    dispatch(fetchAccounts()); // Dispatch the fetchAccounts action when the app loads 
    
  }, [dispatch]);

  return (
    <Routes>
      <Route index path="" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/customer/signup" element={<CustomerSignup />} />
      <Route path="customer" element={<Dashboard />} />
      <Route path="/account" element={<Account />} />
      <Route path="/beneficiary/:customerId" element={<Beneficiary />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/service-request" element={<ServiceRequests />} />
      <Route path="/transaction" element={<Transaction />} />

    </Routes>

  )

}

export default App