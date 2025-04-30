import { useState, useEffect } from "react";
import "./Beneficiary.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
function Beneficiary() {
    const [beneficiary, setBeneficiary] = useState("");
    const [accountNumber, setAccountNumber] = useState("");
    const [ifscCode, setIfscCode] = useState("");
    const [bankName, setBankName] = useState("");
    const [name, setName] = useState("");
    const [showForm, setShowForm] = useState(false);
    const { customerId } = useParams();

    const addBeneficiary = async ($e) => {
        console.log(accountNumber, ifscCode, bankName, name);

        console.log(accountNumber)
        console.log(ifscCode)
        console.log(bankName)
        console.log(name)

        let obj = {
            "accountNumber": accountNumber,
            "ifsc": ifscCode,
            "bankName": bankName,
            "name": name
        }
        let response = await axios.post(`http://localhost:8081/api/beneficiary/add/${customerId}`, obj);
        alert("Beneficiary added successfully")
        setAccountNumber("");
        setIfscCode("");
        setBankName("");
        setName("");
        getAllBeneficiary()

        console.log(response)
        setShowForm(false)

    }
    const getAllBeneficiary = async () => {
        try {
            let response = await axios.get("http://localhost:8081/api/beneficiaries/customer/" + customerId)
            console.log(response.data)
            setBeneficiary(response.data)

        } catch (error) {
            console.log(error)

        }

    }

    const deleteBeneficiary = async (id) => {
        try {
            let response = await axios.delete(`http://localhost:8081/api/beneficiary/delete/${id}`)
            console.log(response.data)
            let temp = [...beneficiary]
            temp = temp.filter(b => b.id !== id)
            setBeneficiary(temp)
            alert("Beneficiary deleted successfully")
        } catch (error) {
            console.log(error)
        }

    }
    const updateBeneficiary = async (id) => {
        try {
            let response = await axios.put(`http://localhost:8081/api/beneficiary/update/${id}`, {
                accountNumber, ifscCode, bankName, name


            });
            let sObj = beneficiary.filter(b => b.id === id)
            sObj.accountNumber = accountNumber
            sObj.ifscCode = ifscCode;
            sObj.bankName = bankName
            sObj.name = name
            let temp = [...beneficiary.filter(b => b.id !== id)]
            temp.push(sObj)
            setBeneficiary(temp)
        }
        catch (error) {
            console.log(error)
        }
    }
    const handleUpdateClick = (beneficiary) => {
        setAccountNumber(beneficiary.accountNumber);
        setIfscCode(beneficiary.ifsc);
        setBankName(beneficiary.bankName);
        setName(beneficiary.name);
    };

    useEffect(() => {
        //addBeneficiary()
        getAllBeneficiary()
        // deleteBeneficiary()
    }, [])


    return (
        <div>
            <div className="container-fluid">
                <div className="row">
                    {/* Sidebar */}
                    <div className="col-md-2 sidebar" style={{ backgroundColor: "#00509e" }}>
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
                                <Link to={`/beneficiary/${localStorage.getItem('customerId')}`} className="nav-link">Beneficiary</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/service-request">Service Request</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/profile">Profile</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="#">Logout</Link>
                            </li>
                        </ul>
                    </div>

                    {/* Main Content */}
                    <div className="col-md-10 p-4">
                        <h1 className="mb-4">Beneficiary</h1>
                        <button className="btn btn-success "
                            onClick={() => setShowForm(showForm ? null : "form")}>Add Beneficiary</button><br />

                        {/* Add your main content here */}
                        {showForm == false ? "" :

                            <form onSubmit={addBeneficiary}>
                                <div className="form-group">
                                    <label>Beneficiary Name</label>
                                    <input type="text" className="form-control" value={name}
                                        onChange={($e) => { setName($e.target.value) }} placeholder="Enter name" required />
                                </div>

                                <div className="form-group">
                                    <label>Account Number</label>
                                    <input type="text" className="form-control" value={accountNumber}
                                        onChange={($e) => { setAccountNumber($e.target.value) }}
                                        placeholder="Enter account number" required />
                                </div>



                                <div className="form-group">
                                    <label>IFSC Code</label>
                                    <input type="text" className="form-control" value={ifscCode}
                                        onChange={($e) => { setIfscCode($e.target.value) }}
                                        placeholder="Enter IFSC Code" required />
                                </div>
                                <div className="form-group">
                                    <label>Bank Name</label>
                                    <input type="text" className="form-control" value={bankName}
                                        onChange={($e) => { setBankName($e.target.value) }}
                                        placeholder="Enter bank name" required />
                                </div>

                                <button type="submit" className="btn btn-success mt-3">Add Beneficiary</button>

                            </form>


                        }<br />

                        <div className="row mt-4">
                            <h5>Beneficiary Details</h5>
                            <div className="card">
                                <table className="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Id</th>
                                            <th scope="col">Account Number</th>
                                            <th scope="col">Bank Name</th>
                                            <th scope="col">IFSC Code</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {beneficiary.sort((a, b) => a.id - b.id).map((b, index) => (
                                            <tr key={index}>
                                                <th scope="row">{index + 1}</th>
                                                <td>{b.accountNumber}</td>
                                                <td>{b.bankName}</td>
                                                <td>{b.ifsc}</td>
                                                <td>{b.name}</td>
                                                <td>
                                                    <button className="btn btn-danger" onClick={() => deleteBeneficiary(b.id)}>Delete</button>&nbsp;
                                                    <button className="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target={`#update-${b.id}`}>Update</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Modals for updating beneficiaries */}
            {beneficiary.map((b) => (
                <div key={b.id} className="modal fade" id={`update-${b.id}`} tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Update Beneficiary Record</h5>
                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div className="modal-body">
                                <form onSubmit={($e) => updateBeneficiary(b.id)}>
                                    <div className="mb-3">
                                        <label htmlFor="accountNumber" className="form-label">Account Number</label>
                                        <input type="text" className="form-control" id="accountNumber" value={accountNumber}
                                            onChange={($e) => setAccountNumber($e.target.value)} placeholder="Enter Account Number" required />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="ifscCode" className="form-label">IFSC Code</label>
                                        <input type="text" className="form-control" id="ifscCode" value={ifscCode}
                                            onChange={($e) => setIfscCode($e.target.value)} placeholder="Enter IFSC Code" required />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="bankName" className="form-label">Bank Name</label>
                                        <input type="text" className="form-control" id="bankName" value={bankName}
                                            onChange={($e) => setBankName($e.target.value)} placeholder="Enter Bank Name" required />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="name" className="form-label">Beneficiary Name</label>
                                        <input type="text" className="form-control" id="name" value={name}
                                            onChange={($e) => setName($e.target.value)} placeholder="Enter Name" required />
                                    </div>
                                    <button type="submit" className="btn btn-success">Update</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default Beneficiary;