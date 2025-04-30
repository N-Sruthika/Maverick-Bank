import { Link } from "react-router";
import "./Transaction.css";
import { useState } from "react";
import axios from "axios";

function Transaction() {
    const [bankobj, setBankObj] = useState({});
    const [upiobj, setUpiObj] = useState({});
    const [showbank, setShowBank] = useState(false);
    const [showupi, setShowUpi] = useState(false);
    const [showTransferOptions, setShowTransferOptions] = useState(false);

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

                                <form>
                                    <div className="form-group">
                                        <label>Beneficiary Name</label>
                                        <input type="text" className="form-control" placeholder="Enter Beneficiary Name" />
                                    </div>
                                    <div className="form-group">
                                        <label>Account Number</label>
                                        <input type="text" className="form-control" placeholder="Enter Account Number" />
                                    </div>
                                    <div className="form-group">
                                        <label>IFSC Code</label>
                                        <input type="text" className="form-control" placeholder="Enter IFSC Code" />
                                    </div>
                                    <div className="form-group">
                                        <label>Bank Name</label>
                                        <input type="text" className="form-control" placeholder="Enter Bank Name" />
                                    </div>
                                    <div className="form-group">
                                        <label>Amount</label>
                                        <input type="text" className="form-control" placeholder="Enter Amount" />
                                    </div>
                                    <button type="submit" className="btn btn-primary">Confirm Transfer</button>
                                </form>
                            </div>
                        }

                        {showupi === false ? "" :
                            <div className="card-content">
                                <form>
                                    <div className="form-group">
                                        <label>UPI ID</label>
                                        <input type="text" className="form-control" placeholder="Enter UPI ID" />
                                    </div>
                                    <div className="form-group">
                                        <label>Amount</label>
                                        <input type="text" className="form-control" placeholder="Enter Amount" />
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
