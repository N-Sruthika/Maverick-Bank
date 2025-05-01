import { Link } from "react-router";
import "./Transaction.css";
import { useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

function Transaction() {
    const [showbank, setShowBank] = useState(true);
    const [showupi, setShowUpi] = useState(false);
    const [showTransferOptions, setShowTransferOptions] = useState(false);
    const [beneficiaryName, setBeneficiaryName] = useState('');
    const [accountNumber, setAccountNumber] = useState(''); 
    const [ifscCode, setIfscCode] = useState('');
    const [bankName, setBankName] = useState('');
    const [amount, setAmount] = useState('');
    const[accountType, setAccountType] = useState('');
    const [upiId, setUpiId] = useState('');
    const { accounts } = useSelector((state) => state.accounts);
    const customerId = localStorage.getItem("customerId");
    
    const userAccount = accounts.find(acc => acc.customer.id === customerId);
    const userAccountNumber = userAccount ? userAccount.accountNumber : null;
    
    console.log("User Account Number:", userAccountNumber);
    
  
    const populate = (val) => {
        setShowTransferOptions(true);
        switch (val) {
            case 'bank':
                setShowBank(true);
                setShowUpi(false);
                break;
            case 'upi':
                setShowBank(false);
                setShowUpi(true);
                break;
            default:
                break;
        }
    };
    const addBankTransfer = async (e) => {
        e.preventDefault();
        let obj = {
            "beneficiaryAccountNumber": accountNumber,
           
            "beneficiaryIFSC": ifscCode,
            "bankName": bankName,
            "amount": amount,
            "beneficiaryName": beneficiaryName,
            "beneficiaryAccountType": accountType
        };
        let token = localStorage.getItem('token');  // Token should be retrieved from localStorage

        

        try {
            const response = await axios.post(
                `http://localhost:8081/api/transactions/bank-transfer/${userAccountNumber}`,
                obj,
                {
                    headers: {
                        "Authorization": `Bearer ${token}`  // Proper header structure
                    }
                }
            );alert("Transaction Successful")
            setBeneficiaryName('')
            setAccountNumber('')
            setIfscCode('')
            setBankName('')
            setAmount('')
            setAccountType('')
           

        }
             catch (err) {
            console.error(err);
        }
    }
    const addUpiTransfer = async (e) => {
        e.preventDefault();
        let obj = {
                "amount": amount,
                "upiId": upiId
 
      }  // Token should be retrieved from localStorage
        let token = localStorage.getItem('token');  // Token should be retrieved from localStorage

        try {
            const response = await axios.post(
                `http://localhost:8081/api/transactions/upi-transfer/${userAccountNumber}`,
                obj,
                {
                    headers: {
                        "Authorization": `Bearer ${token}`  // Proper header structure
                    }
                }
            );alert("Transaction Successful")
            
            

        }
             catch (err) { 
            console.error(err);
        }
    }


    return (
        <div className="transaction-wrapper">
            <div className="sidebar">
                <div className="sidebar-header text-center">
                    <h3>Maverick Bank</h3>
                </div>
                <ul className="nav flex-column">
                    <li className="nav-item"><Link className="nav-link" to="/customer">Dashboard</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/account">Accounts</Link></li>
                    <li className="nav-item"><Link className="nav-link active" to="/transaction">Transactions</Link></li>
                    <li className="nav-item"><Link to={`/beneficiary/${localStorage.getItem('customerId')}`} className="nav-link">Beneficiary</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/service-request">Service Request</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/profile">Profile</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/">Logout</Link></li>
                </ul>
            </div>
           
            <div className="dashboard-content">
            {showTransferOptions === false ?
                <div className="card">
                    <h5>Pay and see history</h5>
                        <div className="text-center p-3">
                           
                            <button className="btn btn-primary" onClick={() => setShowTransferOptions(true)}>
                                One Time Transfer
                            </button>
                        </div>

                        
                    
                </div>
                : ""}<br/>
                {showTransferOptions === false ? "" :
                
                    <div className="card">
                         <h5>Choose your paymethod</h5><br/>

               
                       
                        <div className="transfer-buttons">
                            <button className="transfer-btn" onClick={() => populate('bank')}>Bank Transfer</button>
                            <button className="transfer-btn" onClick={() => populate('upi')}>UPI Transfer</button>
                        </div>

                        {showbank === false ? "" :
                            <div className="card-content">

                                <form onSubmit={(e)=>addBankTransfer(e)}>
                                    <div className="form-group">
                                        <label>Beneficiary Name</label>
                                        <input type="text" className="form-control" placeholder="Enter Beneficiary Name" 
                                        onChange={(e)=>{setBeneficiaryName(e.target.value)}}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Account Number</label>
                                        <input type="text" className="form-control" 
                                        onChange={(e)=>{setAccountNumber(e.target.value)}} placeholder="Enter Account Number" />
                                    </div>
                                    <div className="form-group">
                                        <label>IFSC Code</label>
                                        <input type="text" className="form-control" placeholder="Enter IFSC Code" 
                                        onChange={(e)=>{setIfscCode(e.target.value)}}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Bank Name</label>
                                        <input type="text" className="form-control" placeholder="Enter Bank Name"
                                        onChange={(e)=>{setBankName(e.target.value)}} />
                                    </div>
                                    <div className="form-group">
                                        <label>Amount</label>
                                        <input type="text" className="form-control" placeholder="Enter Amount"
                                        onChange={(e)=>{setAmount(e.target.value)}} />
                                    </div>
                                    <div className="form-group">
                                        <label>Account Type</label>
                                        <input type="text" className="form-control" placeholder="Enter Account Type"
                                        onChange={(e)=>{setAccountType(e.target.value)}}/>
                                    </div>
                                    <button type="submit" className="btn btn-primary">Confirm Transfer</button>
                                </form>
                            </div>
                        }

                        {showupi === false ? "" :
                            <div className="card-content">
                                <form onSubmit={(e)=>addUpiTransfer(e)}>
                                    <div className="form-group">
                                        <label>UPI ID</label>
                                        <input type="text" className="form-control" placeholder="Enter UPI ID"
                                        onChange={(e)=>{setUpiId(e.target.value)}} />
                                    </div>
                                    <div className="form-group">
                                        <label>Amount</label>
                                        <input type="text" className="form-control" placeholder="Enter Amount"
                                        onChange={(e)=>{setAmount(e.target.value)}} />
                                    </div>
                                    <button type="submit" className="btn btn-primary">Confirm Transfer</button>
                                </form>
                            </div>
                        }
                    </div>
                }
            </div>
        </div>
    );
}

export default Transaction;
