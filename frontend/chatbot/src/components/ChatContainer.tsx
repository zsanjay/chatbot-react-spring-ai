import { useState, useEffect, useRef } from "react";
import styled from "styled-components";
import ChatInput from "../components/ChatInput";
import Logout from "./Logout";
import { v4 as uuidv4 } from "uuid";
import axios from "axios";
import { chatRoute } from "../utils/APIRoutes";

interface Message {
    message : string,
    response : string
}

interface ChatType {
    currentChat : any
}

export default function ChatContainer({ currentChat } : ChatType) {

  const [messages, setMessages] = useState<Message[]>([]);
  const scrollRef = useRef<HTMLDivElement | null>(null);

  const appLocalKey =  import.meta.env.VITE_APP_LOCALHOST_KEY;

  useEffect(() => {
    const loadChat = async () => {
        const stored = localStorage.getItem(appLocalKey) || '';
        const data = await JSON.parse(stored);
        const response = await axios.get(`${chatRoute}/${data._id}`); 
        setMessages(response.data);
      }

      loadChat();
  }, [currentChat]);

  useEffect(() => {
    const stored = localStorage.getItem(appLocalKey) || '';
    const getCurrentChat = async () => {
      if (currentChat) {
        await JSON.parse(stored)._id;
      }
    };
    getCurrentChat();
  }, [currentChat]);

  const handleSendMsg = async (msg : string) => {

    const stored = localStorage.getItem(appLocalKey) || '';
    const data = await JSON.parse(stored);

    const response = await axios.post(chatRoute, {
      userId: data._id,
      message: msg,
    });

    const msgs = [...messages];
    msgs.push(response.data);
    setMessages(msgs);
  };

  useEffect(() => {
    scrollRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  return (
    <Container>
      <div className="chat-header">
        <div className="user-details">
          <div className="avatar">
            <img
              src={`data:image/svg+xml;base64,${currentChat.avatarImage}`}
              alt=""
            />
          </div>
          <div className="username">
            <h3>{currentChat.username}</h3>
          </div>
        </div>
        <Logout />
      </div>
      <div className="chat-messages">
        {messages.map((message) => {
          return (
            <div ref={scrollRef} key={uuidv4()}>
              <div
                className="message sent"
              >
                <div className="content ">
                  <p>{message.message}</p>
                </div>
              </div>
              <div
                className="message received"
              >
                <div className="content ">
                  <p>{message.response}</p>
                </div>
              </div>
            </div>
          );
        })}
      </div>
      <ChatInput handleSendMsg={handleSendMsg} />
    </Container>
  );
}

const Container = styled.div`
  display: grid;
  grid-template-rows: 10% 80% 10%;
  gap: 0.1rem;
  overflow: hidden;
  @media screen and (min-width: 720px) and (max-width: 1080px) {
    grid-template-rows: 15% 70% 15%;
  }
  .chat-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 2rem;
    .user-details {
      display: flex;
      align-items: center;
      gap: 1rem;
      .avatar {
        img {
          height: 3rem;
        }
      }
      .username {
        h3 {
          color: white;
        }
      }
    }
  }
  .chat-messages {
    padding: 1rem 2rem;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    overflow: auto;
    &::-webkit-scrollbar {
      width: 0.2rem;
      &-thumb {
        background-color: #ffffff39;
        width: 0.1rem;
        border-radius: 1rem;
      }
    }
    .message {
      display: flex;
      align-items: center;
      .content {
        max-width: 40%;
        overflow-wrap: break-word;
        padding: 1rem;
        font-size: 1.1rem;
        border-radius: 1rem;
        color: #d1d1d1;
        @media screen and (min-width: 720px) and (max-width: 1080px) {
          max-width: 70%;
        }
      }
    }
    .sent {
      justify-content: flex-end;
      .content {
        background-color: #4f04ff21;
      }
    }
    .received {
      justify-content: flex-start;
      .content {
        background-color: #9900ff20;
      }
    }
  }
`;