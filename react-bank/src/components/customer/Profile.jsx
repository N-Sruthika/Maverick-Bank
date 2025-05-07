import { useState, useEffect } from 'react';
import '../css/Profile.css';
import axios from 'axios';
import { Link } from 'react-router';
function Profile() {
  const [customer, setCustomer] = useState([]);
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const customerId = localStorage.getItem('customerId');
  const updateCustomer = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(`http://localhost:8081/api/customer/updateProfile/${customerId}`, {
        email: email,
        address: address
      }, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
        },
      });
      let pObj= customer.filter((p)=>p.id==customerId)
      pObj.email=email;
      pObj.address=address;
      let temp=[...customer.filter((p)=>p.id!=customerId)];
      temp.push(pObj)
      setCustomer(temp);
      alert("Profile updated successfully!");
      setAddress("");
      setEmail("");
      
    } catch (error) {
      
    }
  }
  useEffect(() => {
    const getCustomer = async () => {
      try {

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

      <div className="row">
        {/* Sidebar */}
        <div className="sidebar" style={{width: "100%", backgroundColor: "#00509e"}}>
                <div className="text-center mb-4">
                    <h3 className="text-white">Maverick Bank</h3>
                </div>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link className="nav-link" to="/customer">Dashboard</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/account">Accounts</Link>
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
                        <Link className="nav-link  active" to="/profile">Profile</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/">Logout</Link>
                    </li>
                </ul>
            </div>
        {/* Main Content */}
        <div className="col-md-10 p-4">
          
          {customer ?
            <div className="profile-card mb-4">
              <div className="profile-header text-center">
                <h5 className="card-title">Customer Information</h5>
              </div>
              <div className="profile-item"><strong>Name:</strong> {customer.name}</div>
              <div className="profile-item"><strong>Date of Birth:</strong> {customer.dob}</div>
              <div className="profile-item"><strong>Email:</strong> {customer.email}</div>
              <div className="profile-item"><strong>Mobile No:</strong> {customer.mobileNo}</div>
              <div className="profile-item"><strong>Address:</strong> {customer.address}</div>
              <div className="profile-item"><strong>City:</strong> {customer.city}</div>
              <div className="profile-item"><strong>Gender:</strong> {customer.gender}</div>
              <div className="profile-item"><strong>Pincode:</strong> {customer.pincode}</div>
              <div className="profile-item"><strong>State:</strong> {customer.state}</div>
              <div className="profile-item"><strong>Account Number:</strong> {customer.accountNumber}</div>
              <div className="profile-item"><strong>IFSC Code:</strong> {customer.ifscCode}</div>
              <div className="profile-item"><strong>PAN Number:</strong> {customer.panNumber}</div>

              <div className="text-center mt-4">
              <button className="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target={`#update-${customer.id}`}>Update</button>  </div>
              <hr />
            </div>
            : "Customer not found"}
            { customer?
          <div key={customer.id} className="modal fade" id={`update-${customer.id}`} tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div className="modal-dialog">
              <div className="modal-content">
                <button type="button" className="btn-close m-2 align-self-end" data-bs-dismiss="modal" aria-label="Close"></button>
                <div className="modal-body">
                  <h5 className="modal-title mb-3" id="userInfoModalLabel">Enter User Info</h5>
                  <form onSubmit={($e) => updateCustomer($e)}>
                    
                    <div className="mb-3">
                      <label htmlFor="userEmail" className="form-label">Email</label>
                      <input type="email" className="form-control" id="userEmail" placeholder="Enter email" required 
                      onChange={(e)=>{setEmail(e.target.value)}}/>
                    </div>
                    
                    <div className="mb-3">
                      <label htmlFor="userAddress" className="form-label">Address</label>
                      <input type="text" className="form-control" id="userAddress" placeholder="Enter address" required 
                      onChange={(e)=>{setAddress(e.target.value)}}/>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
            : ""}
            
        </div>

      </div>
   
  );
}

export default Profile;
