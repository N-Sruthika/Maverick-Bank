import { useState } from "react";
import users from "../../data/users.js";
import { Link, useNavigate } from "react-router";

import axios from "axios";
import { useEffect } from "react";
import "./Login.css";

function Login() {
    const [username, setUsername] = useState(null);
    const [password, setPassword] = useState(null);
    const [usernamemsg, setUsernameMsg] = useState(null);
    const [passwordmsg, setPasswordMsg] = useState(null);
    const [userData, setUserData] = useState(users);
    const navigate = useNavigate();
    const login = () => {
        let isCorrect = false;

        if (username == null || username === "" || username === undefined) {
            setUsernameMsg("Username is required");
            return;
        } else {
            setUsernameMsg(null);
        }

        if (password == null || password === "" || password === undefined) {
            setPasswordMsg("Password is required");
            return;
        } else {
            setPasswordMsg(null);
        }

       let body={
        'username': username,
        'password': password
       }
       axios.post('http://localhost:8081/api/auth/token/generate', body)
            .then(response => {
                //console.log(response)
                let token = response.data.token
                //save the token in localstorage memory of web browser 
                localStorage.setItem('token', token)
                localStorage.setItem('username', username)

                axios.get('http://localhost:8081/api/customer/get',{
                    headers: {
                        "Authorization": `Bearer ${token}`  //token goes here but not getting detected in backend
                    }
                }).then(
                    resp=>{
                        localStorage.setItem('accountNumber', resp.data.accountNumber)
                        localStorage.setItem('customerId', resp.data.id)
                    }
                )
                

                //console.log(token)
                axios.get('http://localhost:8081/api/auth/user/details',
                    {
                        headers: {
                            "Authorization": `Bearer ${token}`  //token goes here but not getting detected in backend
                        }
                    }
                ).then(resp => {
                    //console.log(resp.data)
                    let cid=resp.data.customerId
 
                           switch (resp.data.role) {
                            case 'CUSTOMER':
                                //navigate to customer dashboard
                                navigate("/customer")
                                break;
                            default:
                                break;
                        }
                    })
        
            .catch(err => {
                setUsernameMsg("Invalid Credentials")
                console.log(err)
            })
        }).catch(err => {

            setUsernameMsg("Invalid Credentials")
            console.log(err)
        })

    }

       

    return (
        <div style={{ minHeight: "100vh", display: "flex", background: "#f8f9fa", fontFamily: "'Roboto', sans-serif" }}>
            {/* Logo */}
            <div className="logo" >
                <img src="/kotak-logo.png" alt="Maverick Bank"  />
            </div>
            {/* Left Section */}
            <div className="left-section">
                <img src="/login.png" alt="Secure Banking" style={{height: "400px", width: "400px", margin: "20px"}}/>
                <h1>Welcome to Maverick Net Banking</h1>
                <p>Experience secure and convenient banking at your fingertips</p>
            </div>
            {/* Right Section */}
            <div className="right-section">
                <div className="login-box">
                    <h2>Login to Net Banking</h2>
                    <form className="login-form" onSubmit={e => { e.preventDefault(); login(); }}>
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <input
                                type="text"
                                id="username"
                                name="username"
                                className="form-control"
                                placeholder="Enter your username"
                                onChange={e => { setUsername(e.target.value); setUsernameMsg(null); }}
                                required
                            />
                            {usernamemsg === null ? "" : <div className="mb-4">{usernamemsg}</div>}
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                className="form-control"
                                placeholder="Enter your password"
                                onChange={e => { setPassword(e.target.value); setPasswordMsg(null); }}
                                required
                            />
                            {passwordmsg === null ? "" : <div className="mb-4">{passwordmsg}</div>}
                        </div>
                        <button type="submit" className="secure-login-btn">Secure Login</button>
                    </form>
                    
                </div>
            </div>
        </div>
    );
}

export default Login;
