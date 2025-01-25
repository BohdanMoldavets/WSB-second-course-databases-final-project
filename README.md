# Accounting CRUD System
### Final project for the lesson ‘Databases’
#### University ‘WSB Merito Poznan’

---

## 🧩 Project Description
This project was developed as part of a university course on databases.
It features a database for managing employees and their accounting details, as well as a web application with CRUD functionality.
The system allows users to manage departments, employees, salaries and dates. It also provides user authentication and role-based access control.

---

## 🚀 Features

- Add, view, edit, and delete employee records.
- Manage company departments.
- Update salary information for employees.
- Track employment start dates and payment schedules.
- User authentication and access control management.

---

## 🧰 Tech stack used in this project
- **Java 21**
- **Spring Boot** (Core, DI, Annotations)
- **Hibernate** (ORM)
- **PostgreSQL** (Database)
- **Lombok** (for reducing boilerplate code)
- **Maven** (Build tool)
- **Interface:** Web application

---

## 🔑 Key Features
- **Data Integrity:** Enforced through foreign keys and cascade operations.
- **Security:** User authentication and role-based access via Spring Security.
- **Flexibility:** The `currency` field defaults to PLN but supports other currencies.
- **User-Friendly:** Intuitive web interface for managing data.

## 📂 Project Structure
```
src/main/java
├── com.moldavets.finalproject
│   ├── dao                // Database interaction layer (CRUD operations, using Spring JPA repositories)
│   ├── entity             // JPA Entity classes representing database tables
│   ├── rest               // REST controllers handling HTTP requests
│   ├── security           // Spring Security configuration (authentication and authorization)
│   └── service            // Service layer for business logic
│       └── Impl               // Implementations of service interfaces
│   
src/main/resources
└── templates              // Thymeleaf templates for web views
    ├── datestamps         // HTML templates for employment and payment dates management
    ├── departments        // HTML templates for department management
    ├── employees          // HTML templates for employee management
    └── salaries           // HTML templates for salary management

```
---

## ⚙️ Installation and Setup

**1. Clone the Repository**
```
git clone https://github.com/BohdanMoldavets/WSB-second-course-databases-final-project.git
cd WSB-second-course-databases-final-project
```

**2. Database Configuration**

+ Create a PostgreSQL database and run the following script:

```
\src\main\resources\data.sql
```

+ Update the database connection settings in the application.properties file:
```
\src\main\resources\application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/... #DB name
spring.datasource.username= #DB user
spring.datasource.password= #DB password
```

**3. Install Dependencies**

```
cd WSB-second-course-databases-final-project
.\mvnw clean install
```

**4. Start the Application**

```
.\mvnw spring-boot:run
```

## 🛑 FAQ
### Q: Why can’t I add an employee?:
 + **A:** To add an employee, you must first create a department. An employee cannot be hired without being assigned to a specific department.

# Contact

+ Email: [steamdlmb@gmail.com](mailto:steamdlmb@gmail.com)
+ [Telegram](https://telegram.me/moldavets)
+ [Linkedin](https://www.linkedin.com/in/bohdan-moldavets/)


