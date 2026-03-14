# 🚀 Loan Origination System

A **Spring Boot backend application** that simulates a **Loan Origination System (LOS)** used in banking systems to manage loan applications, approvals, and analytics.

This system allows users to:

* Submit loan applications
* Track loan status via notification
* Loan Status Monitoring
* Approve/Reject loans via agents
* Retrieve loan analytics
* Fetch top customers
* Use pagination for loan records

---

# 🧰 Tech Stack

| Technology      | Usage                        |
| --------------- | ---------------------------- |
| Java 17         | Core Programming Language    |
| Spring Boot 3   | Backend Framework            |
| Spring Data JPA | ORM for database interaction |
| MySQL / H2      | Database                     |
| Maven           | Build tool                   |
| JUnit 5         | Unit Testing                 |
| Mockito         | Mocking framework            |
| Git             | Version Control              |

---

# ✨ Features

✅ Submit loan application
✅ Agent approval workflow
✅ Loan status tracking
✅ Pagination support for loan listing
✅ Top customer analytics
✅ Unit testing with Mockito
✅ Clean layered architecture (Controller → Service → Repository)

---

# 📂 Project Structure

```
loan-origination-system
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
│
├── src/test/java
│   └── unit tests
│
├── pom.xml
└── README.md
```

---

# ⚙️ Prerequisites

Before running the project make sure you have installed:

* Java 17+
* Maven
* MySQL (optional if using H2)
* Git
* IntelliJ IDEA / Eclipse

---

# 📥 Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/loan-origination-system.git
```

Navigate to the project folder

```bash
cd loan-origination-system
```

---
# ▶️ Run the Application
## 🗄️ Database Setup

## Option 1 — MySQL (Recommended)

### Step 1: Create Database

Login to MySQL and run:

```sql
CREATE DATABASE loan_system;
```

---

### Step 2: Configure Database Credentials

Open:

```
src/main/resources/application.properties
```

Update values:

```
spring.datasource.url=jdbc:mysql://localhost:3306/loan_system
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Option 2 — H2 Database (In Memory)

If using H2:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

---

### Build the Project

```bash
mvn clean install
```

### Start Spring Boot Application

```bash
mvn spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

# 📡 API Endpoints

## 1️⃣ Submit Loan Application

**POST**

```
/api/v1/loans
```

### Request Body

```json
{
  "customerName": "Rahul Sharma",
  "customerPhone": "9876543210",
  "loanAmount": 50000,
  "loanType": "PERSONAL"
}
```

---

## 2️⃣ Get Loans by Status (Pagination)

**GET**

```
/api/v1/loans?status=APPLIED&page=0&size=5
```

### Example

```
/api/v1/loans?status=APPROVED&page=0&size=10
```

---

## 3️⃣ Get Loan Status Count

**GET**

```
/api/v1/loans/status-count
```

Response example:

```json
{
  "APPLIED": 5,
  "APPROVED": 3,
  "REJECTED": 1
}
```

---

## 4️⃣ Get Top Customers

**GET**

```
/api/v1/customers/top
```

---

## 5️⃣ Agent Decision on Loan

**PUT**

```
/api/v1/agents/{agentId}/loans/{loanId}/decision
```

Example:

```
/api/v1/agents/1/loans/4/decision
```

### Request Body

```json
{
  "decision": "APPROVE"
}
```

or

```json
{
  "decision": "REJECT"
}
```

---
## 🧪 Running Unit Tests

Run all test cases:

```bash
mvn test
```

Example output:

```
Tests run: 7
Failures: 0
Errors: 0
BUILD SUCCESS
```

---

## 🧪 Example Test Cases

### Test Loan Creation

Validates:

- DTO → Entity conversion
- Loan default status set to **APPLIED**
- Repository `save()` invocation

---

### Test Loan Filtering by Status

Validates:

- Pagination support
- Service → Repository interaction
- Correct loans returned for requested status

---

## 🧪 Agent Service Tests

### Test Agent Approve Loan

Validates:

- Agent is correctly assigned to the loan
- Loan status changes from **UNDER_REVIEW → APPROVED_BY_AGENT**
- Service logic updates entity state correctly

---

### Test Agent Reject Loan

Validates:

- Loan status changes from **UNDER_REVIEW → REJECTED_BY_AGENT**
- Agent decision is processed correctly

---

### Test Agent Decision Invalid Status

Validates:

- Agent cannot approve or reject a loan that is **not in UNDER_REVIEW**
- Runtime exception is thrown when decision is invalid

---

## 🧪 Loan Processing Service Tests

### Test System Auto Approval

Validates:

- Loan is processed by system rules
- Loan status changes to **APPROVED_BY_SYSTEM**
- Notification service sends customer SMS

---

### Test System Auto Rejection

Validates:

- Loan is rejected automatically when rules fail
- Loan status changes to **REJECTED_BY_SYSTEM**

---

### Test Loan Sent for Manual Review

Validates:

- Loan status changes to **UNDER_REVIEW**
- Agent is assigned automatically
- Notification is sent to the assigned agent

---

## ✅ What These Tests Ensure

These tests ensure:

- Service layer business logic works correctly
- Repository interactions are properly mocked
- Loan lifecycle transitions are validated
- Agent decision flow is tested
- Notification service integration is verified

---

## 📊 Test Coverage

Core services covered:

- LoanService
- Agent decision logic
- Loan processing engine
- Notification interactions

Coverage includes:

- Business rules
- Status transitions
- Repository interaction
- Exception handling
---

# 🧑‍💻 Author

**Vivek Rajput**


