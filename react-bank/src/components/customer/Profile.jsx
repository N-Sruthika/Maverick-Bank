import { useState, useEffect } from 'react';
import './Profile.css';
import axios from 'axios';
import { Link } from 'react-router';
function Profile() {
    const [customer, setCustomer] = useState(null);

    useEffect(() => {
        const getCustomer = async () => {
            try {
                const customerId = localStorage.getItem('customerId');
                const token = localStorage.getItem('token');


                const response = await axios.get(`http://localhost:8081/api/getall/${customerId}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });

                console.log("Customer Data:", response.data);
                setCustomer(response.data);
            } catch (error) {
                console.error("Error fetching customer data:", error);
            }
        };

        getCustomer();
    }, []);

    return (
        <div className="container-fluid">
            <div className="row">
                {/* Sidebar */}
                <div className="col-md-2 sidebar" style={{ backgroundColor: "#00509e" }}>
                    <div className="text-center mb-4">
                        <h3 className="text-white">Maverick Bank</h3>
                    </div>
                    <ul className="nav flex-column">
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/customer">Dashboard</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/account">Accounts</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/transaction">Transactions</Link>
                        </li>
                        <li className="nav-item">
                        <Link to={`/beneficiary/${localStorage.getItem('customerId')}`} className="nav-link">Beneficiary</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/service-request">Service Request</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="/profile">Profile</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link text-white" to="#" onClick={() => {
                                localStorage.clear();
                                window.location.href = '/login'; // Redirect to login page
                            }}>
                                Logout
                            </Link>
                        </li>
                    </ul>
                </div>

                {/* Main Content */}
                <div className="col-md-10 p-4">
                    <h4>Profile</h4>

                    <div className="card mb-4">
                        <div className="card-body">
                            <div className="container text-left">
                                <div className="row">
                                    
                                    <div className="col">
                                        <h5>Name</h5>
                                        {customer ? customer.name : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>Date of Birth</h5>
                                        {customer ? customer.dob : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>Email</h5>
                                        {customer ? customer.email : "Loading..."}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <h5>Contact Information</h5>
                    <div className="card mb-4">
                        <div className="card-body">
                            <div className="container text-left">
                                <div className="row">
                                    <div className="col">
                                        <h5>Mobile No</h5>
                                        {customer ? customer.mobileNo : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>Address</h5>
                                        {customer ? customer.address : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>City</h5>
                                        {customer ? customer.city : "Loading..."}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <h5>Personal Information</h5>
                    <div className="card mb-4">
                        <div className="card-body">
                            <div className="container text-left">
                                <div className="row">
                                    
                                    <div className="col">
                                        <h5>Gender</h5>
                                        {customer ? customer.gender : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>Pincode</h5>
                                        {customer ? customer.pincode : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>State</h5>
                                        {customer ? customer.state : "Loading..."}
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>

                    <h5>Account Information</h5>
                    <div className="card mb-4">
                        <div className="card-body">
                            <div className="container text-left">
                                <div className="row">
                                    <div className="col">
                                        <h5>Account Number</h5>
                                        {customer ? customer.accountNumber : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>IFSC Code</h5>
                                        {customer ? customer.ifscCode : "Loading..."}
                                    </div>
                                    <div className="col">
                                        <h5>PAN Number</h5>
                                        {customer ? customer.panNumber : "Loading..."}
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Profile;
