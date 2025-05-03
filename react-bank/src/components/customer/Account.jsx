import { useState, useEffect } from "react";
import "./Account.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

function Account() {
  const customerId = localStorage.getItem("customerId");

  const { accounts } = useSelector((state) => state.accounts)

  const account = accounts.find(acc => acc.customer.id == customerId);
  return (
    <div className="app-container">
      {/* Sidebar */}
      <div className="sidebar">
        <div className="text-center mb-4">
          <h3 className="text-white">Maverick Bank</h3>
        </div>
        <ul className="nav flex-column">
          <li className="nav-item">
            <Link className="nav-link " to="/customer">Dashboard</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link active" to="/account">Accounts</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/transaction">Transactions</Link>
          </li>
          <li className="nav-item">
            <Link to={`/beneficiary/${customerId}`} className="nav-link">Beneficiary</Link>

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


      {/* Main Area */}
      <div className="main-area">
        <div className="top-bar">Your Account Overview</div>
        <div className="account-content">
          <div className="account-cards">
            <div className="card">
              <div className="card-header">Account Details</div>
              <div className="card-body">
                {account ?
                  <div>
                    <div>Account Number: {account.accountNumber}</div>
                    <div>Account Type: {account.accountType}</div>
                    <div>Available Balance: {account.balance}</div>
                    <div>IFSC Code: {account.ifscCode}</div>
                    <div>Status: {account.status}</div>
                  </div>
                  : " No account found"
                }
              </div>
            </div>
            {/* Add more cards here, e.g. Recent Transactions */}
          </div>
          <div className="quick-actions-card">
            <h4>Quick Actions</h4>
            <div className="quick-links">
              <Link to="/transaction"> Fund Transfer</Link>
              <Link to="/service-request"> Raise Query</Link>
              <Link to={`/beneficiary/${customerId}`}> Add beneficiary</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Account;


