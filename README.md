# 🧠 ChatBot - React + Spring Boot + PostgreSQL + AI

This is a full-stack AI-powered chatbot application built using:

🔧 ReactJS for the frontend

☕ Spring Boot (Java) for the backend

🐘 PostgreSQL as the database

🤖 Optional integration with AI (OpenAI/GPT-like models)


# 🚀 Features

Live AI-powered chat interface

Stores chat messages in PostgreSQL

JSON-based API communication between frontend and backend

Scalable architecture using Spring Boot services and DTOs

React hooks and Axios for efficient frontend interactions


# 🛠️ Technologies Used

| Layer       | Stack                        |
| ----------- | ---------------------------- |
| Frontend    | ReactJS, Axios, CSS          |
| Backend     | Spring Boot, JPA, Lombok     |
| AI          | Spring AI                    |
| Database    | PostgreSQL                   |
| Build Tools | Maven, npm                   |


# Backend Setup (Spring Boot)

✅ Prerequisites

Java 17+
Maven
PostgreSQL running locally or on docker


# 📄 application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/chatbot
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# OpenAI Key
spring.ai.openai.api-key=${OPEN_AI_KEY}
```

# Frontend Setup (React)

✅ Prerequisites

Node.js
npm or yarn

🔧 Install & Run

```bash
cd frontend
npm install
npm start
```

# API Communication

Ensure axios points to the backend URL:

```js
const BASE_URL = "http://localhost:8080/api/chat";
```

# Screenshots

## 1. Login Page

<img width="1189" alt="Screenshot 2025-06-03 at 7 49 24 PM" src="https://github.com/user-attachments/assets/1deb719c-8961-4439-aa74-5c763203a8b7" />

## 2. Registration Page

<img width="1189" alt="Screenshot 2025-06-03 at 7 49 37 PM" src="https://github.com/user-attachments/assets/0353a644-6d8c-4786-a81f-c082ec40c91d" />

## 3. Chat Page

<img width="1580" alt="Screenshot 2025-06-03 at 7 56 21 PM" src="https://github.com/user-attachments/assets/ed951875-b16d-4428-8165-c1e4bd244df3" />


# 🧩 Future Enhancements

User authentication (JWT)

Chat history with pagination

Voice input

Multi-language support

# 👨‍💻 Author

Developed by Sanjay Mehta

Feel free to contribute or fork the repo.








