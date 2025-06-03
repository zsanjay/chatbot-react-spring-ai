import { useNavigate } from "react-router-dom";
import { BiPowerOff } from "react-icons/bi";
import styled from "styled-components";
import axios from "axios";
import { logoutRoute } from "../utils/APIRoutes";
import { toast, type ToastOptions } from "react-toastify";

export default function Logout() {

    const navigate = useNavigate();
    const appLocalKey = import.meta.env.VITE_APP_LOCALHOST_KEY;
    const toastOptions: ToastOptions = {
        position: "bottom-right",
        autoClose: 8000,
        pauseOnHover: true,
        draggable: true,
        theme: "dark",
    };

    const handleClick = async () => {
        try {
            const storedUser = localStorage.getItem(appLocalKey) || '';
            const id = await JSON.parse(storedUser)._id;
            const data = await axios.get(`${logoutRoute}/${id}`);
            if (data.status === 200) {
                localStorage.clear();
                navigate("/login");
            }
        } catch (error: any) {
            toast.error(error.response.data, toastOptions);
            setTimeout(() => { navigate("/register"); }, 5000)
        }
    };
    return (
        <Button onClick={handleClick}>
            <BiPowerOff />
        </Button>
    );
}

const Button = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.5rem;
  border-radius: 0.5rem;
  background-color: #9a86f3;
  border: none;
  cursor: pointer;
  svg {
    font-size: 1.3rem;
    color: #ebe7ff;
  }
`;