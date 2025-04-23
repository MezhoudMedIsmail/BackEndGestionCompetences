# 🔧 BackEndGestionCompetences

A Java Spring Boot backend powering the **competency and talent management system** for **La Poste Tunisienne**. This RESTful API handles authentication, user and theme management, question/response flows, and feedback submission, forming the backend layer of a complete HR evaluation platform.

## 🎯 Project Purpose

Designed for La Poste Tunisienne's internal HR system, this backend exposes secure endpoints to support recruitment, evaluation, and skill mapping operations in collaboration with the Angular frontend.

## 🧰 Technologies Used

- **Java 11+**
- **Spring Boot**
- **Spring MVC**
- **Spring Security (JWT Authentication)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **RESTful API**
- **Maven**

## 📁 Folder Structure (Controllers)

```
src/main/java/org/example/backendamine/controller/
├── AuthenticationController.java   # Handles user login & JWT
├── FeedbackController.java         # Feedback from evaluations
├── QuestionController.java         # Evaluation questions
├── ReponseController.java          # User responses to questions
├── ThemeController.java            # Thematic categorization
├── UserController.java             # User registration & management
```

Each controller maps to service layers that interact with the data layer using JPA repositories.

## ⚙️ Setup Instructions

### Prerequisites

- Java 11+
- Maven
- MySQL or other RDBMS

### Installation

1. **Clone the repository**

```bash
git clone https://github.com/MezhoudMedIsmail/BackEndGestionCompetences.git
cd BackEndGestionCompetences
```

2. **Set up MySQL Database**

Create a database named `gestion_competences` and update `application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_competences
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the app**

```bash
mvn spring-boot:run
```

Server will start on `http://localhost:8080`.

---

## 🔐 Authentication

This project uses **JWT (JSON Web Token)** based security to protect API endpoints. Tokens are passed in the `Authorization` header like so:

```
Authorization: Bearer <your_token>
```

## 📬 Main API Modules

| Endpoint Base        | Purpose                        |
|----------------------|--------------------------------|
| `/api/auth`          | Authentication & login         |
| `/api/users`         | User CRUD                      |
| `/api/questions`     | Manage evaluation questions    |
| `/api/reponses`      | Handle user responses          |
| `/api/themes`        | Organize by theme              |
| `/api/feedback`      | Submit feedback                |

## 📌 Future Enhancements

- Add role-based access control (RBAC)
- Swagger API documentation
- Evaluation scoring logic

## 🤝 Contributing

Feel free to fork this repo and contribute with pull requests. For big changes, please open an issue first.


---

Built with ⚙️ for La Poste Tunisienne by [MezhoudMedIsmail](https://github.com/MezhoudMedIsmail)
