import React, { useEffect, useState, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { io, Socket } from "socket.io-client";
import styled from "styled-components";
import { allUsersRoute, host } from "../utils/APIRoutes";
import ChatContainer from "../components/ChatContainer";
import Contacts from "../components/Contacts";
import Welcome from "../components/Welcome";

interface User {
    _id: string;
    name: string;
    isAvatarImageSet: boolean;
}


export default function Chat() {

  const navigate = useNavigate();
  const socket =  useRef<Socket | null>(null);
  const [contacts, setContacts] = useState([]);
  const [currentChat, setCurrentChat] = useState<string>();
  const [currentUser, setCurrentUser] = useState<User>();

  const appLocalKey =  import.meta.env.VITE_APP_LOCALHOST_KEY;

  useEffect(() => {
    const loadUser =async () => {
        const stored = localStorage.getItem(appLocalKey);
        if (!stored) {
            navigate("/login");
          } else {
            const user = await JSON.parse(stored);
            setCurrentUser(user);
          }
    }
    loadUser();
  }, []);

  useEffect(() => {
    if (currentUser) {
      socket.current = io(host);
      socket.current?.emit("add-user", currentUser._id);
    }
  }, [currentUser]);

  useEffect(() => {
    const setAvatarImage = async () => {
    if (currentUser) {
      if (currentUser.isAvatarImageSet) {
        const data = await axios.get(`${allUsersRoute}/${currentUser._id}`);
        setContacts(data.data);
      } else {
        navigate("/setAvatar");
      }
    }
    }
    setAvatarImage();
  }, [currentUser]);

  const handleChatChange = (chat : string) => {
    setCurrentChat(chat);
  };

  return (
    <>
      <Container>
        <div className="container">
          <Contacts contacts={contacts} changeChat={handleChatChange} />
          {currentChat === undefined ? (
            <Welcome />
          ) : (
            <ChatContainer currentChat={currentChat} socket={socket} />
          )}
        </div>
      </Container>
    </>
  );
}

const Container = styled.div`
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 1rem;
  align-items: center;
  background-color: #131324;
  .container {
    height: 85vh;
    width: 85vw;
    background-color: #00000076;
    display: grid;
    grid-template-columns: 25% 75%;
    @media screen and (min-width: 720px) and (max-width: 1080px) {
      grid-template-columns: 35% 65%;
    }
  }
`;