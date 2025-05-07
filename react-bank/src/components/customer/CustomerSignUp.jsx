import { useState } from 'react';
import '../css/CustomerSignUp.css';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useEffect } from 'react';
function CustomerSignup() {
    const [name, setName] = useState("")
    const [dob, setDob] = useState("")
    const [gender, setGender] = useState("")
    const [email, setEmail] = useState("")
    const [mobile, setMobile] = useState("")
    const [address, setAddress] = useState("")
    const [city, setCity] = useState("")
    const [state, setState] = useState("")
    const [pincode, setPincode] = useState("")
    const [pan, setPan] = useState("")
    const [aadhar, setAadhar] = useState("")
    const [accountNumber, setAccountNumber] = useState("")
    const [ifsc, setIfsc] = useState("")
   
    const [username, setUsername] = useState("");  // Add state for username
    const [password, setPassword] = useState("");  // Add state for password
    const signUp = async ($e) => {
        $e.preventDefault();
        const requestData = {
            "name": name,
            "dob": dob,
            "gender": gender,
            "email": email,
            "mobileNo": mobile,
            "address": address,
            "city": city,
            "state": state,
            "pincode": pincode,
            "panNumber": pan,
            "aadhaarNumber": aadhar,
            "accountNumber": accountNumber,
            "ifscCode": ifsc,
            
            "user": {
                "username": username,
                "password": password
            }
        };
        
        console.log(requestData);
    
        try {
            const response = await axios.post("http://localhost:8081/api/newuser/signup", requestData);
            console.log("Signup successful", response.data);
            alert("Signup successful! Please login to continue.");
        } catch (error) {
            console.log("Error occurred:", error.response ? error.response.data : error);
        }
    }
    
    return (
        <div>
            <div className="container-fluid"><br />
                <div className="row">
                    <ul className="nav nav-tabs">
                        <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="#">Signup to Continue</a>
                        </li>

                    </ul>
                </div>
                {/* form div */}
                <div className='row'>
                    <form onSubmit={($e) => signUp($e)}>
                        <h3>Personal Information</h3>
                        <div className="mb-3">
                            <label htmlFor="name" className="form-label">Name</label>
                            <input type="text" className="form-control" id="name" name="name" placeholder="Enter your name"
                                onChange={(e) => setName(e.target.value)} autoComplete="name" />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="dob" className="form-label">Date of Birth</label>
                            <input type="date" className="form-control" id="dob" name="dob" autoComplete="bday"
                                onChange={(e) => setDob(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="gender" className="form-label">Gender</label>
                            <select className="form-control" id="gender" name="gender" onChange={(e) => setGender(e.target.value)} value={gender}>
                                <option value="">choose...</option>
                                <option value="MALE">Male</option>
                                <option value="FEMALE">Female</option>
                            </select>

                        </div>

                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">Email</label>
                            <input type="email" className="form-control" id="email" name="email" autoComplete="email"
                                onChange={(e) => setEmail(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="mobile" className="form-label">Mobile Number</label>
                            <input type="number" className="form-control" id="mobile" name="mobile"
                                onChange={(e) => setMobile(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="address" className="form-label">Address</label>
                            <input type="text" className="form-control" id="address" name="address" autoComplete="address"
                                onChange={(e) => setAddress(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="city" className="form-label">City</label>
                            <input type="text" className="form-control" id="city" name="city"
                                onChange={(e) => setCity(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="state" className="form-label">State</label>
                            <input type="text" className="form-control" id="state" name="state"
                                onChange={(e) => setState(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="pincode" className="form-label">Pincode</label>
                            <input type="number" className="form-control" id="pincode" name="pincode"
                                onChange={(e) => setPincode(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="pan" className="form-label">PAN Card Number</label>
                            <input type="text" className="form-control" id="pan" name="pan"
                                onChange={(e) => setPan(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="aadhar" className="form-label">Aadhar Number</label>
                            <input type="text" className="form-control" id="aadhar" name="aadhar"
                                onChange={(e) => setAadhar(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="accountNumber" className="form-label">Account Number</label>
                            <input type="text" className="form-control" id="accountNumber" name="accountNumber"
                                onChange={(e) => setAccountNumber(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="ifsc" className="form-label">IFSC Code</label>
                            <input type="text" className="form-control" id="ifsc" name="ifsc"
                                onChange={(e) => setIfsc(e.target.value)} />
                        </div>


                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">Username</label>
                            <input type="text" className="form-control" id="username" name="username" autoComplete="username"
                                onChange={(e) => setUsername(e.target.value)} />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">Password</label>
                            <input type="password" className="form-control" id="password" name="password" autoComplete="current-password" 
                                onChange={(e) => setPassword(e.target.value)} />
                        </div>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    )

}
export default CustomerSignup;