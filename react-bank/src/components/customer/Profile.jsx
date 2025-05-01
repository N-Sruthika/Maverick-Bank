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
  <h3 className="text-center mb-4">Profile</h3>

  <div className="profile-card">
    <div className="profile-header text-center">
      <h5 className="card-title">Customer Information</h5>
    </div>

    <div className="profile-body">
      <div className="profile-item">
        <strong>Name:</strong> {customer?.name || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Date of Birth:</strong> {customer?.dob || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Email:</strong> {customer?.email || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Mobile No:</strong> {customer?.mobileNo || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Address:</strong> {customer?.address || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>City:</strong> {customer?.city || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Gender:</strong> {customer?.gender || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Pincode:</strong> {customer?.pincode || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>State:</strong> {customer?.state || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>Account Number:</strong> {customer?.accountNumber || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>IFSC Code:</strong> {customer?.ifscCode || "Loading..."}
      </div>

      <div className="profile-item">
        <strong>PAN Number:</strong> {customer?.panNumber || "Loading..."}
      </div>
    </div>

    <div className="text-center mt-4">
      <button className="btn btn-primary btn-sm">Edit</button>
    </div>
  </div>
</div>

            </div>
        </div>
    );
}

export default Profile;
