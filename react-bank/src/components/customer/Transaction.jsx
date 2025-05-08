import { Link } from "react-router-dom";
import "../css/Transaction.css";
import { useEffect, useState } from "react";
import axios from "axios";


function Transaction() {
    const [showbank, setShowBank] = useState(true);
    const [showupi, setShowUpi] = useState(false);
    const [showTransferOptions, setShowTransferOptions] = useState(false);
    const [beneficiaryName, setBeneficiaryName] = useState('');
    const [accountNumber, setAccountNumber] = useState('');
    const [ifscCode, setIfscCode] = useState('');
    const [bankName, setBankName] = useState('');
    const [amount, setAmount] = useState('');
    const [accountType, setAccountType] = useState('');
    const [upiId, setUpiId] = useState('');
    const [transactionHistory, setTransactionHistory] = useState([]);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [totalPages, setTotalPages] = useState(0);

    let accno = localStorage.getItem('accountNumber')

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
                `http://localhost:8081/api/transactions/bank-transfer/${accno}`,
                obj,
                {
                    headers: {
                        "Authorization": `Bearer ${token}`  // Proper header structure
                    }
                }
            ); alert("Transaction Successful")
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
                `http://localhost:8081/api/transactions/upi-transfer/${accno}`,
                obj,
                {
                    headers: {
                        "Authorization": `Bearer ${token}`  // Proper header structure
                    }
                }
            ); alert("Transaction Successful")



        }
        catch (err) {
            console.error(err);
            alert("Transaction failed! Insufficient Bank Balance")
        }
    }
    const getTransactionHistory = async () => {
        try {
            let token = localStorage.getItem('token');
            let cid = localStorage.getItem('customerId');
            const response = await axios.get(`http://localhost:8081/api/transactions/customer/history/${cid}?page=${page}&size=${size}`,{
                headers: {
                "Authorization": `Bearer ${token}`  // Proper header structure
            }
        });
            console.log(response.data);
            setTransactionHistory(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            console.log(error);
        }
    };
    useEffect(() => {

        getTransactionHistory();

    }, [page]);

    return (
        <div className="transaction-wrapper">
            <div className="sidebar">
                <div className="sidebar-header text-center">
                    <h3>Maverick Bank</h3>
                </div><br/>
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
                        <div className="text p-3">

                            <button className="btn btn-primary" onClick={() => setShowTransferOptions(true)}>
                                One Time Transfer
                            </button>
                        </div>

                        {/* transaction history */}
                        <div className="row">
                            <div className="chart-section mt-4">
                                <h5>Transaction History</h5>
                                <div className="card" style={{ width: "70%" }}>
                                    <div className="card-header">Transaction History</div>
                                    <div className="card-body">

                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Id</th>
                                            <th scope="col">Amount</th>
                                            <th scope="col">Status</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Type</th>
                                            <th scope="col">Mode</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {transactionHistory.map((transaction,index) => (
                                            <tr key={index}>
                                                <td>{transaction.id}</td>
                                                <td>{transaction.amount}</td>
                                                <td>{transaction.status}</td>
                                                <td>{transaction.transactionDate}</td>

                                                <td>{transaction.transactionType}</td>
                                                <td>{transaction.transactionMode}</td>
                                            </tr>
                                        ))}
                                    </tbody>

                                </table>

                            </div>
                                </div>
                            </div>
                            <br />
                            <div className="col-md-6">
                                <nav aria-label="Page navigation example">
                                    <ul className="pagination">
                                        <li className="page-item"><a className="page-link" href="#"
                                            onClick={(e) => { e.preventDefault();
                                                 page === 0 ? setPage(0) : setPage(page - 1) }}>Previous</a></li> &nbsp;&nbsp;


                                        <li className="page-item"><a className="page-link" href="#"
                                            onClick={(e) => {e.preventDefault(); page === totalPages - 1 ? setPage(page) : setPage(page + 1) }} >Next</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>

                    </div>
                    : ""}<br />
                {showTransferOptions === false ? "" :

                    <div className="card">
                        <h5>Choose your paymethod</h5><br />

                        <div className="transfer-buttons">
                            <button className="transfer-btn" onClick={() => populate('bank')}>Bank Transfer</button>
                            <button className="transfer-btn" onClick={() => populate('upi')}>UPI Transfer</button>
                        </div>

                        {showbank === false ? "" :
                            <div className="card-content">

                                <form onSubmit={(e) => addBankTransfer(e)}>
                                    <div className="form-group">
                                        <label>Beneficiary Name</label>
                                        <input type="text" className="form-control" placeholder="Enter Beneficiary Name"
                                            onChange={(e) => { setBeneficiaryName(e.target.value) }} />
                                    </div>
                                    <div className="form-group">
                                        <label>Account Number</label>
                                        <input type="text" className="form-control"
                                            onChange={(e) => { setAccountNumber(e.target.value) }} placeholder="Enter Account Number" />
                                    </div>
                                    <div className="form-group">
                                        <label>IFSC Code</label>
                                        <input type="text" className="form-control" placeholder="Enter IFSC Code"
                                            onChange={(e) => { setIfscCode(e.target.value) }} />
                                    </div>
                                    <div className="form-group">
                                        <label>Bank Name</label>
                                        <input type="text" className="form-control" placeholder="Enter Bank Name"
                                            onChange={(e) => { setBankName(e.target.value) }} />
                                    </div>
                                    <div className="form-group">
                                        <label>Amount</label>
                                        <input type="text" className="form-control" placeholder="Enter Amount"
                                            onChange={(e) => { setAmount(e.target.value) }} />
                                    </div>
                                   
                                    <button type="submit" className="btn btn-primary">Confirm Transfer</button>
                                </form>
                            </div>
                        }

                        {showupi === false ? "" :
                            <div className="card-content">
                                <form onSubmit={(e) => addUpiTransfer(e)}>
                                    <div className="form-group">
                                        <label>UPI ID</label>
                                        <input type="text" className="form-control" placeholder="Enter UPI ID"
                                            onChange={(e) => { setUpiId(e.target.value) }} />
                                    </div>
                                    <div className="form-group">
                                        <label>Amount</label>
                                        <input type="text" className="form-control" placeholder="Enter Amount"
                                            onChange={(e) => { setAmount(e.target.value) }} />
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
