import { useState, useEffect, type FormEvent } from "react";
import axios from "axios";
import styled from "styled-components";
import { useNavigate, Link } from "react-router-dom";
import Logo from "../assets/chatbot-icon.svg";
import { ToastContainer, toast, type ToastOptions } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { registerRoute } from "../utils/APIRoutes";

export default function Register() {

    const appLocalKey = import.meta.env.VITE_APP_LOCALHOST_KEY;

    const navigate = useNavigate();
    const toastOptions: ToastOptions = {
        position: "bottom-right",
        autoClose: 8000,
        pauseOnHover: true,
        draggable: true,
        theme: "dark",
    };
    const [values, setValues] = useState({
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
    });

    useEffect(() => {
        if (localStorage.getItem(appLocalKey)) {
            navigate("/");
        }
    }, []);

    const handleChange = (event: any) => {
        setValues({ ...values, [event.target.name]: event.target.value });
    };

    const handleValidation = () => {
        const { password, confirmPassword, username, email } = values;
        if (password !== confirmPassword) {
            toast.error(
                "Password and confirm password should be same.",
                toastOptions
            );
            return false;
        } else if (username.length < 3) {
            toast.error(
                "Username should be greater than 3 characters.",
                toastOptions
            );
            return false;
        } else if (password.length < 8) {
            toast.error(
                "Password should be equal or greater than 8 characters.",
                toastOptions
            );
            return false;
        } else if (email === "") {
            toast.error("Email is required.", toastOptions);
            return false;
        }

        return true;
    };

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        if (handleValidation()) {
            const { email, username, password } = values;
            try {
                const response = await axios.post(registerRoute, {
                    username,
                    email,
                    password,
                });

                if (response.status === 200) {
                    localStorage.setItem(appLocalKey, JSON.stringify(response.data));
                    navigate("/login");
                }

            } catch (error: any) {
                if (error.response.status === 400) {
                    toast.error(error.response.data, toastOptions);
                }
            }
        }
    };

    return (
        <>
            <FormContainer>
                <form action="" onSubmit={(event: FormEvent) => handleSubmit(event)}>
                    <div className="brand">
                        <img src={Logo} alt="logo" />
                        <h1>ChatBuddy</h1>
                    </div>
                    <input
                        type="text"
                        placeholder="Username"
                        name="username"
                        onChange={(e) => handleChange(e)}
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        name="email"
                        onChange={(e) => handleChange(e)}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        name="password"
                        onChange={(e) => handleChange(e)}
                    />
                    <input
                        type="password"
                        placeholder="Confirm Password"
                        name="confirmPassword"
                        onChange={(e) => handleChange(e)}
                    />
                    <button type="submit">Create User</button>
                    <span>
                        Already have an account ? <Link to="/login">Login.</Link>
                    </span>
                </form>
            </FormContainer>
            <ToastContainer />
        </>
    );
}

const FormContainer = styled.div`
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 1rem;
  align-items: center;
  background-color: #131324;
  .brand {
    display: flex;
    align-items: center;
    gap: 1rem;
    justify-content: center;
    img {
      height: 5rem;
    }
    h1 {
      color: blueviolet;
      text-transform: uppercase;
    }
  }

  form {
    display: flex;
    flex-direction: column;
    gap: 2rem;
    background-color: aquamarine;
    border-radius: 2rem;
    padding: 3rem 5rem;
  }
  input {
    background-color: beige;
    padding: 1rem;
    border: 0.1rem solid #4e0eff;
    border-radius: 0.4rem;
    color: black;
    width: 100%;
    font-size: 1rem;
    &:focus {
      border: 0.1rem solid #997af0;
      outline: none;
      color : black;
    }
  }
  button {
    background-color: #4e0eff;
    color: white;
    padding: 1rem 2rem;
    border: none;
    font-weight: bold;
    cursor: pointer;
    border-radius: 0.4rem;
    font-size: 1rem;
    text-transform: uppercase;
    &:hover {
      background-color: #4e0eff;
    }
  }
  span {
    color: #4e0eff;
    text-transform: uppercase;
    a {
      color: #4e0eff;
      text-decoration: none;
      font-weight: bold;
    }
  }
`;