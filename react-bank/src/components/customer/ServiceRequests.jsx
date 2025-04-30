import { useState, useEffect } from 'react';
import './ServiceRequest.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

function ServiceRequests() {
  const [serviceRequest, setServiceRequest] = useState({});
  const [raiseQuery, setRaiseQuery] = useState(false);
  const [previousQuery, setPreviousQuery] = useState(false);
  const [showQuery, setShowQuery] = useState(false);
  const [showPreviousQuery, setShowPreviousQuery] = useState(false);
  const [category, setCategory] = useState(null);
  const [message, setMessage] = useState('');
  const [subject, setSubject] = useState('');
  const [categories, setCategories] = useState([]); // State for storing categories from the backend
  const customerId = localStorage.getItem('customerId');

  // Fetch categories from backend on component load
 

  const populate = (val) => {
    switch (val) {
      case 'raise':
        setShowQuery(true);
        setShowPreviousQuery(false);
        break;
      case 'view':
        setShowQuery(false);
        setShowPreviousQuery(true);
        break;
      default:
        break;
    }
  };

  const addRequest = async (e) => {
    e.preventDefault();

    let obj = {
      "category": category,
      "subject": subject,
      "message": message
    };
    let token = localStorage.getItem('token');  // Token should be retrieved from localStorage

    let headers = {
      headers: {
        "Authorization": `Bearer ${token}`  // Using the token in the headers
      }
    };

    try {
      const response = await axios.post(`http://localhost:8081/api/service-request/raise/${customerId}`, obj, headers);
      console.log(response.data);
    } catch (err) {
      console.error(err);
    }
  };
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get('http://localhost:8081/api/service-request/categories');
        setCategories(response.data); // Store the categories in the state
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };

    const getPreviousQuery=async()=>{
      const response=await axios.get(``)
    }

    fetchCategories();
  }, []);

  return (
    <div className="container-fluid">
      <div className="row">
        {/* Sidebar */}
        <div className="col-md-2 sidebar" style={{ backgroundColor: "#00509e" }}>
          <div className="row">
            <div className="text-center">
              <h3>Maverick Bank</h3>
            </div>
            <ul className="nav flex-column">
              <li className="nav-item">
                <Link className="nav-link" to="customer">Dashboard</Link>
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
                <Link className="nav-link active" to="/service-request">Service Request</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/profile">Profile</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="#">Logout</Link>
              </li>
            </ul>
          </div>
        </div>

        {/* Main Content */}
        <div className="col-md-10 main-content">
          <div className="tab">
            <h4>Submit New Service Request</h4>
          </div>
          <button onClick={() => { populate('raise') }}>Raise Query</button>
          <button onClick={() => { populate('view') }}>Previous Query</button>

          <div className="main-content">
            <div className="row">
              {/* Left Column - Form */}
              <div className="col-md-9">
                <div className="tabs">
                  {showQuery !==true? 
                    <form onSubmit={addRequest}>
                    <div className="form-group">
                      <label>Category</label>
                      <select required className="form-control" onChange={($e) => setCategory($e.target.value)}><option value="">Select Category</option>
                      {categories.map((category, index) => (<option key={index} value={category}>{category}</option>))}</select>
                      </div><br />
                  
                    <div className="form-group">
                      
                      <label>Subject</label>
                      <input type="text" className="form-control" placeholder="Brief description of your issue" required 
                      onChange={($e) => setSubject($e.target.value)} />
                      </div><br />
                  
                    <div className="form-group">
                      <label>Message</label>
                      <textarea className="form-control" placeholder="Describe your issue in detail" required 
                      onChange={($e) => setMessage($e.target.value)}></textarea>
                      </div><br />
                  
                    <button type="submit" className="btn btn-primary mt-3">Submit Request</button>
                  </form>
                  
                   : ""}
                </div>
              </div>

              {/* Right Column - Previous Query */}
              <div className="col">
                {showPreviousQuery ? (
                  <div className="card shadow-">
                    <h4>Previous Query</h4>
                    <div className="card">
                      <div className="card-body">
                        {/* Previous query details will be displayed here */}
                      </div>
                    </div>
                  </div>
                ) : null}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ServiceRequests;
