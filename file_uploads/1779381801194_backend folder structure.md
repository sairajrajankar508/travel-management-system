
# Enhanced Project Development Prompt

## Project Goal
I want to build my **complete Travel Management System project** in **Eclipse IDE**, including **frontend, backend, and database**

---

## 📌 Project Reference



This includes:
- Layered architecture (Controller, Service, Repository, Entity, DTO)
- Role-based modules (Admin, Employee, Manager, Finance)
- MySQL database integration
- Spring Boot backend design

---

## 🎯 Requirements

### 1. Step-by-step Development
- Build the project **from scratch**
- Start with **backend first**, then frontend, then integration
- Follow a **clear and sequential approach**

### 2. Simple & Explainable Code
- Code should be **easy to understand**
- Avoid unnecessary complexity
- Must be easy to **explain during viva/presentation**

### 3. Strict Scope Control
- Include only:
  - Core required features
  - Important functionalities from the `.md` file
- ❌ Do NOT add extra or advanced features
- Keep it **minimal but complete**

### 4. Explanation Focus
- For each part, explain:
  - What it does
  - Why it is needed
  - How it fits in the flow (Controller → Service → Repository → DB)

### 5. Project Workflow Clarity
Must clearly understand:
- Request lifecycle (DRAFT → SUBMITTED → APPROVED → etc.)
- Role-based access (RBAC)
- API flow
- Database interaction

---

## 🚀 Starting Task
First step:
👉 Provide a **clean and simplified backend folder structure**

- Based on the reference `.md` file
- Use simple naming
- Follow industry standards
- Add 1–2 line explanation for each folder

---

## ⚠️ Important Notes
- Using **Eclipse + Spring Boot + MySQL**
- Do NOT skip steps
- Do NOT overload with theory
- Focus on **practical and understandable implementation**


## Eclipse backend spring boot maven java project folder structure

travel-management-system/               # Root Maven Project
│
├── src/main/java/                      # Java Source Folder
│   └── com.travel/                     # Base Package
│
│       ├── config/                     # Security & Global Configurations
│       │   ├── SecurityConfig.java     # Security & JWT Configuration
│       │   └── CorsConfig.java         # Frontend-Backend Connection
│
│       ├── controller/                 # REST API Controllers
│       │   ├── AuthController.java         # Login & Authentication APIs
│       │   ├── AdminController.java        # User & Policy Management APIs
│       │   ├── EmployeeController.java     # Request & Expense APIs
│       │   ├── ManagerController.java      # Approval & Comment APIs
│       │   ├── FinanceController.java      # Reimbursement APIs
│       │   ├── ExpenseController.java      # Expense APIs
│       │   ├── TravelRequestController.java# Travel Request APIs
│       │   └── UserController.java         # User APIs
│
│       ├── service/                    # Business Logic Layer
│       │   ├── AdminService.java           # User & Role Logic
│       │   ├── EmployeeService.java        # Request & Expense Logic
│       │   ├── ManagerService.java         # Approval & Review Logic
│       │   ├── FinanceService.java         # Reimbursement Logic
│       │   ├── PolicyService.java          # Travel Policy Logic
│       │   ├── ExpenseService.java         # Expense Management Logic
│       │   ├── TravelRequestService.java   # Travel Request Logic
│       │   └── UserService.java            # User Management Logic
│
│       ├── entity/                     # JPA Entity Classes
│       │   ├── User.java                   # User Entity
│       │   ├── TravelRequest.java          # Travel Request Entity
│       │   ├── TravelPolicy.java           # Travel Policy Entity
│       │   ├── Expense.java                # Expense Entity
│       │   └── AuditLog.java               # Audit & Activity Logs
│
│       ├── repository/                 # Database Access Layer
│       │   ├── UserRepository.java
│       │   ├── RequestRepository.java
│       │   ├── ExpenseRepository.java
│       │   └── PolicyRepository.java
│
│       ├── dto/                        # Data Transfer Objects
│       │   ├── UserDTO.java
│       │   ├── TravelRequestDTO.java
│       │   ├── ExpenseDTO.java
│       │   └── AuthResponseDTO.java
│
│       ├── exception/                  # Global Exception Handling
│       │   ├── GlobalExceptionHandler.java
│       │   └── ResourceNotFoundException.java
│
│       └── TravelApplication.java      # Main Spring Boot Application
│
├── src/main/resources/                 # Resources Folder
│   └── application.properties          # MySQL & Server Configuration
│
├── src/test/java/                      # Unit Testing Folder
│
├── pom.xml                             # Maven Dependencies
│
└── README.md                           # Project Documentation