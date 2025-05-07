import { useState, useEffect } from 'react';
import '../css/ServiceRequest.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

function ServiceRequests() {
  const [serviceRequest, setServiceRequest] = useState([]);
 const [showQuery, setShowQuery] = useState(true);
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

  const addRequest = async ($e) => {
    $e.preventDefault();

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
      alert("Service request raised successfully!");
      setCategory(''); // Clear the category after submission
      console.log(response.data);
    } catch (err) {
      console.error(err);
    }
  };
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        
        const token = localStorage.getItem('token');

        const response = await axios.get('http://localhost:8081/api/service-request/categories',{
          headers: {
            "Authorization": `Bearer ${token}`  // Using the token in the headers
          }
        });
        setCategories(response.data); // Store the categories in the state
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };

    const getPreviousQuery = async () => {
      const token = localStorage.getItem('token');

    
      const response = await axios.get(`http://localhost:8081/api/service-request/customer/${customerId}`,{
        headers: {
          "Authorization": `Bearer ${token}`  // Using the token in the headers
        }
      })
      console.log(response.data)
      setServiceRequest(response.data)
    }

    fetchCategories();
    getPreviousQuery(); // Fetch previous queries when the component mounts
  }, []);

  return (
    
      <div className="row">
        {/* Sidebar */}
        <div className=" sidebar" style={{ backgroundColor: "#00509e" }}>
          <div className="text-center">
            <h3 className="text-white mt-3">Maverick Bank</h3>
          </div>
          <ul className="nav flex-column mt-4">
            <li className="nav-item"><Link className="nav-link text-white" to="/customer">Dashboard</Link></li>
            <li className="nav-item"><Link className="nav-link text-white" to="/account">Accounts</Link></li>
            <li className="nav-item"><Link className="nav-link text-white" to="/transaction">Transactions</Link></li>
            <li className="nav-item"><Link className="nav-link text-white" to={`/beneficiary/${customerId}`}>Beneficiary</Link></li>
            <li className="nav-item"><Link className="nav-link active text-white" to="/service-request">Service Request</Link></li>
            <li className="nav-item"><Link className="nav-link text-white" to="/profile">Profile</Link></li>
            <li className="nav-item"><Link className="nav-link text-white" to="/">Logout</Link></li>
          </ul>
        </div>

        {/* Main Content */}
        <div className="col-md-10 p-4">
          <div className="d-flex justify-content-between align-items-center mb-3">
            <h4>Service Requests</h4>
            <div>
              <button className="btn btn-primary me-2" onClick={() => populate('raise')}>Raise Query</button>
              <button className="btn btn-primary" onClick={() => populate('view')}>Previous Query</button>
            </div>
          </div>

          {
            showQuery == true ? (
              <form onSubmit={($e)=>addRequest($e)}><pre>
                <div className="form-group mb-3">
                  <label>Category</label>
                  <select required className="form-control" onChange={(e) => setCategory(e.target.value)}>
                    <option value="">Select Category</option>
                    {
                    categories.map((category, index) => (
                      <option key={index} value={category}>
                        {category}
                        </option>
                    ))}
                  </select>
                </div>

                <div className="form-group mb-3">
                  <label>Subject</label>
                  <input type="text" className="form-control" placeholder="Brief description of your issue" required
                    onChange={(e) => setSubject(e.target.value)} />
                </div>

                <div className="form-group mb-3">
                  <label>Message</label>
                  <textarea className="form-control" placeholder="Describe your issue in detail" required
                    onChange={(e) => setMessage(e.target.value)}></textarea>
                </div>

                <button type="submit" className="btn btn-success">Submit Request</button></pre>
              </form>
            ) : ""
          }

          {/* Previous Queries */}
          {
            showPreviousQuery == true ?
              <div className="card mt-4">
                <div className="card-header">
                  <h5>Previous Queries</h5>
                </div>
                <div className="card-body">
                  {
                    serviceRequest.map((req, index) => (
                      <div key={index}>
                        <p><strong>Category:</strong> {req.category}</p>
                        <p><strong>Subject:</strong> {req.subject}</p>
                        <p><strong>Message:</strong> {req.message}</p><hr />
                      </div>
                    ))
                  }
                </div>
              </div>
              : ""
          }
        </div>
      </div>
    
  );
}

export default ServiceRequests;