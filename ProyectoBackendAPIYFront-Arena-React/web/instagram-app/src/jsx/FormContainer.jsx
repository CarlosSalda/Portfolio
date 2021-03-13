import React, { useState } from 'react';
import {useHistory} from "react-router-dom";
import { login, register } from './API';

const initialQuestion = {header: 'What do you want to do?', rightButton: 'Login', leftButton: 'Register'}
const initialForm = {login: "FormOculto", register: "FormOculto"}

const loginForm = {header: 'Login', rightButton: 'Confirm', leftButton:'Cancel'}
const loginData = {email:'', password: ''}

const registerForm = {header: 'Register', rightButton: 'Confirm', leftButton:'Cancel'}
const registerData = {name: '', email: '', password: '', image: ''}

const FormContainer = () => {
    const history = useHistory();

    const [container, setContainer] = useState(initialQuestion);
    const [formControler, setFormControler] = useState(initialForm);
    const [dataRegister, setDataRegister] = useState(registerData);
    const [dataLogin, setDataLogin] = useState(loginData);
    const [loginError, setLoginError] = useState("ErrorOculto");
    const [registerNameError, setRegisterNameError] = useState("ErrorOculto");
    const [registerPassError, setRegisterPassError] = useState("ErrorOculto");
    const [registerEmailError, setRegisterEmailError] = useState("ErrorOculto");
    const [registerDataError, setRegisterDataError] = useState("ErrorOculto");
    const [registerUsedError, setRegisterUsedError] = useState("ErrorOculto");

    const handleLogin = (event) => {
        event.preventDefault();
        setContainer(prevState => loginForm);
        setFormControler({login: 'FormVisible', register: 'FormOculto'})
    }

    const handleRegister = (event) => {
        event.preventDefault();
        setContainer(registerForm);
        setFormControler({login: 'FormOculto', register: 'FormVisible'})
    }

    const handleLoginChange = (event) => {
        event.preventDefault();
        setDataLogin({
          ...(dataLogin),
          [event.target.name]: event.target.value,
        });
    };

    const handleRegisterChange = (event) => {
        event.preventDefault();
        setDataRegister({
          ...(dataRegister),
          [event.target.name]: event.target.value,
        });
    };

    const handleRegisterError = (data) => {
        var val = true 

        if(!/^[A-Za-z]+$/g.test(data.name)) {
            setRegisterNameError("ErrorVisible")
            val = false
        }else{            
            setRegisterNameError("ErrorOculto")
        }
        
        if(!data.email.includes("@") || !data.email.includes(".com")) {
            setRegisterEmailError("ErrorVisible")
            val = false
        }else{            
            setRegisterEmailError("ErrorOculto")
        }

        if(data.password.length <= 7) {
            setRegisterPassError("ErrorVisible")
            val = false
        }else{            
            setRegisterPassError("ErrorOculto")
        }
       
        if(!data.name.replace(/\s/g, '').length ||  !data.email.replace(/\s/g, '').length || !data.password.replace(/\s/g, '').length) {
            setRegisterDataError("ErrorVisible")
            val = false
        }else{            
            setRegisterDataError("ErrorOculto")
        }
        console.log(val)
        return val
    }

    const handleConfirm = (event) =>{
        event.preventDefault();
        if('Register' === container.header){

            register(dataRegister)
            .then((response) => {
                    localStorage.setItem("token", response.headers.authorization);
                    localStorage.setItem("userId", JSON.stringify(response.headers.userid));
                    history.push("/TimeLine"); console.log(dataRegister); 
                })
            .catch((error) => {
                console.log(handleRegisterError(dataRegister))
                if (handleRegisterError(dataRegister)){
                    setRegisterUsedError("ErrorVisible")
                }
            })
        } else{
            login(dataLogin)
                .then((response) => {
                    localStorage.setItem("token", response.headers.authorization);
                    localStorage.setItem("userId", JSON.stringify(response.headers.userid));
                    history.push("/TimeLine");
                })
                .catch(setLoginError("ErrorVisible"));
        }
    }

    const handleCancel = (event) =>{
        event.preventDefault();
        setContainer(initialQuestion);
        setFormControler(initialForm);
        setDataRegister(registerData);
        setDataLogin(loginData);
        setLoginError("ErrorOculto")
        setRegisterNameError("ErrorOculto");
        setRegisterPassError("ErrorOculto");
        setRegisterEmailError("ErrorOculto");
        setRegisterUsedError("ErrorOculto");
        setRegisterDataError("ErrorOculto");
    }

    return (
        <div className="box Container">
            <form className="Form" onSubmit = {handleConfirm} >
                <div className="FormTitle">{container.header}</div>                
                    <div className = {formControler.login}> <br/>
                        <div className="form-group">
                            <label htmlFor="inputEmail">Email address</label><br/>    
                            <input required className= "form-control" name= "email" type = "email" value ={dataLogin.email}  onChange= {handleLoginChange}/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputPassword">Password</label><br/>
                            <input  required className= "form-control" name= "password"  type = "password" value ={dataLogin.password} onChange= {handleLoginChange}/>
                        </div>
                    </div>    
                    <small id="emailHelp" className={loginError}>Wrong email address or password, try again</small><br/>

                    <div className = {formControler.register}>
                    <small id="usedEmail" className={registerUsedError}>This email already has an user account</small>
                        <div className="form-group">
                            <label htmlFor="name">Name</label><br/>
                            <input required type="text" name="name" value={dataRegister.name} onChange={handleRegisterChange} className="form-control"/>
                        </div>
                        <div id="wrongName" className={registerNameError}>This name is not a correct name, verify it only contains letters</div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label><br/>
                            <input required type="text" name="email" value={dataRegister.email} onChange={handleRegisterChange} className="form-control"/>
                        </div>
                        <small id="wrongEmail" className={registerEmailError}>This email format is not correct, please verify it is like this "example@account.com"</small>
                        <div className="form-group">
                            <label htmlFor="password">Password</label><br/>
                            <input required type="password" name="password" value={dataRegister.password} onChange={handleRegisterChange} className="form-control" />
                        </div>
                        <small id="wrongPassword" className={registerPassError}>Password must have 8 characters at least</small>
                        <div className="form-group">
                            <label htmlFor="image">Profile Image</label><br/>
                            <input type="text" name="image" value={dataRegister.image} onChange={handleRegisterChange} className="form-control" />
                        </div>
                        <small id="emptyData" className={registerDataError}>Only profile image can be empty</small>
                    </div>
            </form>
            <div className = "ButtonLeft"><button className="Button ButtonLeft" type="submit" onClick = {eval('handle'+ container.leftButton)}>{container.leftButton}</button></div>
            <div className = "ButtonRight"><button className="Button ButtonRight" type="submit" onClick = {eval('handle'+ container.rightButton)}>{container.rightButton}</button></div>
        </div>
    );
}

export default FormContainer
