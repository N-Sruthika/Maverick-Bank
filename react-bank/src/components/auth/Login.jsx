import { useState } from "react";
import users from "../../data/users.js";

function Login() {
    const [username, setUsername] = useState(null);
    const [password, setPassword] = useState(null);
    const [usernamemsg, setUsernameMsg] = useState(null);
    const [passwordmsg, setPasswordMsg] = useState(null);
    const [userData, setUserData] = useState(users);

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

        userData.forEach(u => {
            if (u.username === username && u.password === password) {
                alert("Login Successful, your role is " + u.role);
                isCorrect = true;
            }
        });

        if (isCorrect === false) {
            setUsernameMsg("Invalid credentials");
        }
    };

    return (
        <div style={{ backgroundColor: "#f8f9fa", minHeight: "100vh" }}>
            <div className="container-fluid">
                <div className="row mb-4">
                    <div className="col-lg-12">
                        <nav className="navbar" style={{ backgroundColor: "#15207e" }}>
                            <div className="container-fluid">
                                <span className="navbar-brand mb-0 h1 text-white">
                                    Welcome!! To Maverick Net Banking
                                </span>
                            </div>
                        </nav>
                    </div>
                </div>
                <br/><br/>

                <div className="row mt-4">
                    <div className="col-sm-4"></div>
                    <div className="col-sm-4">
                        <div className="card border-primary shadow">
                            <div className="card-header text-white text-center" style={{ backgroundColor: "#15207e" }}>                           
                               <h5>Login</h5>
                            </div>

                            <div className="card-body">
                                {usernamemsg && (
                                    <div className="alert alert-danger">{usernamemsg}</div>
                                )}
                                {passwordmsg && (
                                    <div className="alert alert-danger">{passwordmsg}</div>
                                )}
                                <div className="mb-4">
                                    <label>Username: </label>
                                    <input type="text" className="form-control"  placeholder="Enter your username" 
                                    onChange={$event=>{setUsername($event.target.value);
                                        setUsernameMsg(null)}
                                    }/>
                                </div>
                                <div className="mb-4">
                                    <label>Password: </label>
                                    <input type="password" className="form-control" placeholder="Enter your password" 
                                    onChange={($event)=>{setPassword($event.target.value);setPasswordMsg(null)}}/>
                                </div>

                                

                                <div className="mb-4 text-center" >
                                    <button type="button" className="btn btn-success px-4" style={{ backgroundColor: "#15207e"}} onClick={() => { login(); }} >
                                        Secure Login
                                    </button>
                                </div>
                            </div>

                            <div className="card-footer text-white text-center"
                                style={{ backgroundColor: "#15207e" }}>
                            
                             
                                <a href="#" className="text-white text-decoration-underline">
                                    Reset Password
                                </a>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-4"></div>
                </div>
            </div>
        </div>
    );
}

export default Login;
