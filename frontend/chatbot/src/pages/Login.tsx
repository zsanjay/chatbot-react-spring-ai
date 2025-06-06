import { useState, useEffect, type ChangeEvent, type FormEvent } from "react";
import axios from "axios";
import styled from "styled-components";
import { useNavigate, Link } from "react-router-dom";
import Logo from "../assets/chatbot-icon.svg";
import { ToastContainer, toast, type ToastOptions } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { loginRoute } from "../utils/APIRoutes";

export default function Login() {
    const navigate = useNavigate();
    const [values, setValues] = useState({ username: "", password: "" });
    const toastOptions: ToastOptions = {
        position: "bottom-right",
        autoClose: 8000,
        pauseOnHover: true,
        draggable: true,
        theme: "dark",
    };

    const appLocalKey = import.meta.env.VITE_APP_LOCALHOST_KEY;

    useEffect(() => {
        if (localStorage.getItem(appLocalKey)) {
            navigate("/");
        }
    }, []);

    const handleChange = (event: any) => {
        setValues({ ...values, [event?.target?.name]: event.target.value });
    };

    const validateForm = () => {
        const { username, password } = values;
        if (username === "") {
            toast.error("Email and Password is required.", toastOptions);
            return false;
        } else if (password === "") {
            toast.error("Email and Password is required.", toastOptions);
            return false;
        }
        return true;
    };

    const handleSubmit = async (event: FormEvent) => {
        event.preventDefault();
        if (validateForm()) {
            const { username, password } = values;
            try {
                const response = await axios.post(loginRoute, {
                    username,
                    password,
                });

                if (response.status === 200) {
                    localStorage.setItem(
                        appLocalKey,
                        JSON.stringify(response.data)
                    );
                }
                navigate("/");

            } catch (error: any) {
                if (error.response.status === 401) {
                    toast.error(error.response.data, toastOptions);
                }
                setTimeout(() => {
                    navigate("/register");
                }, 5000)
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
                        onChange={(e: ChangeEvent) => handleChange(e)}
                        min="3"
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        name="password"
                        onChange={(e) => handleChange(e)}
                    />
                    <button type="submit">Log In</button>
                    <span>
                        Don't have an account ? <Link to="/register">Create One.</Link>
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
    padding: 5rem;
  }
  input {
    background-color: antiquewhite;
    padding: 1rem;
    border: 0.1rem solid #4e0eff;
    border-radius: 0.4rem;
    color: black;
    width: 100%;
    font-size: 1rem;
    &:focus {
      border: 0.1rem solid #997af0;
      outline: none;
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