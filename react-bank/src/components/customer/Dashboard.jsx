import { useState, useEffect } from "react";
import "./Dashboard.css";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

function Dashboard() {
    
    const [totalBalance, setTotalBalance] = useState(null);
    const [totalBeneficiary, setTotalBeneficiary] = useState(null);
    const [totalActiveAccounts, setTotalActiveAccounts] = useState(null);
    const [transactionHistory, setTransactionHistory] = useState([]);
    const{cid } = useParams(); // Assuming you are passing accountNumber as a URL parameter
    
    useEffect(() => {
        const getBalance = async () => {
            try {
                let accno = localStorage.getItem('accountNumber')
                const response = await axios.get(`http://localhost:8081/api/account/get/balance/${accno}`);
                console.log(response.data);
                setTotalBalance(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        const getBeneficiary = async () => {
            try {
                let cid = localStorage.getItem('customerId');
                const response = await axios.get(`http://localhost:8081/api/beneficiaries/count/${cid}`);
                console.log(response.data);
                setTotalBeneficiary(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        const getActiveAccounts = async () => {
            try {
                let cid = localStorage.getItem('customerId');
                const response = await axios.get(`http://localhost:8081/api/accounts/active/count/${cid}`);
                console.log(response.data);
                setTotalActiveAccounts(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        const getTransactionHistory = async () => {
            try {
                let cid = localStorage.getItem('customerId');
                const response = await axios.get(`http://localhost:8081/api/transactions/customer/${cid}`);
                console.log(response.data);
                setTransactionHistory(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        getBalance();
        getBeneficiary();
        getActiveAccounts();
        getTransactionHistory();
    }, []);

    return (
        <div className="dashboard-layout">
            {/* Sidebar */}
            <div className="sidebar">
                <div className="text-center mb-4">
                    <h3 className="text-white">Maverick Bank</h3>
                </div>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link className="nav-link active" to="/customer">Dashboard</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/account">Accounts</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/transaction">Transactions</Link>
                    </li>
                    <li className="nav-item">
                    <Link to={`/beneficiary/${localStorage.getItem('customerId')}`} className="nav-link">Beneficiary</Link>

                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/service-request">Service Request</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/profile">Profile</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/">Logout</Link>
                    </li>
                </ul>
            </div>
            {/* Main Content */}
            <div className="dashboard-main">
                <div className="main-container">
                    <div className="dashboard-grid">
                        <div className="balance-card">
                            <div className="balance-label">Total Balance</div>
                            <div className="balance-amount">{totalBalance !== null ? totalBalance : "0"}</div>
                        </div>
                        <div className="mini-card">
                            <div className="mini-card-header">
                                <div className="mini-card-title">Total Beneficiary</div>
                                
                            </div>
                            <div className="mini-card-value">{totalBeneficiary !== null ? totalBeneficiary : 0}</div>

                        </div>
                        <div className="mini-card">
                            <div className="mini-card-header">
                                <div className="mini-card-title">Total Active Accounts</div>
                                
                            </div>
                            <div className="mini-card-value">{totalActiveAccounts === null ? 0 : totalActiveAccounts}</div>
                            <div className="mini-card-label">Active now</div>
                        </div>
                    </div>

                    {/* Transaction History Table */}
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
                                        {transactionHistory.map((transaction) => (
                                            <tr key={transaction.id}>
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

                </div>
            </div>
        </div>

    );
}

export default Dashboard;
