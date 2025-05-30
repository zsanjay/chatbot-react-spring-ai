import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import ChatContainer from "../components/ChatContainer";
import Contacts from "../components/Contacts";
import Welcome from "../components/Welcome";

interface User {
    _id: string;
    name: string;
    email : string;
    isAvatarImageSet: boolean;
    avatarImage : string;
    messages : any;
}


export default function Chat() {

  const navigate = useNavigate();
  const [contacts, setContacts] = useState([]);
  const [currentChat, setCurrentChat] = useState<string>();
  const [currentUser, setCurrentUser] = useState<User>();

  const appLocalKey =  import.meta.env.VITE_APP_LOCALHOST_KEY;

  useEffect(() => {
    const loadUser = async () => {
        const stored = localStorage.getItem(appLocalKey);
        if (!stored) {
            navigate("/login");
          } else {
            const user = await JSON.parse(stored);
            setCurrentUser(user);
            console.log("current user from chat = " + currentUser);
          }
    }
    loadUser();
  }, []);

  const handleChatChange = (chat : string) => {
    setCurrentChat(chat);
  };

  return (
    <>
      <Container>
        <div className="container">
          <Contacts contacts={contacts} changeChat={handleChatChange} />
          {currentUser === undefined ? (
            <Welcome />
          ) : (
            <ChatContainer currentChat={currentUser}/>
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