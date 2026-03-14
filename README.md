# Loan Origination System

Spring Boot backend application that simulates a loan origination workflow.

## Features

- Loan submission API
- Agent approval workflow
- Multi-threaded loan processing
- Notification service
- Loan status analytics
- Pagination support

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 / MySQL
- Maven

## APIs

Submit Loan

POST /api/v1/loans

Loan Status Count

GET /api/v1/loans/status-count

Loans by Status

GET /api/v1/loans?status=APPLIED&page=0&size=10

Top Customers

GET /api/v1/customers/top

Agent Decision

PUT /api/v1/agents/{agentId}/loans/{loanId}/decision

## Run Application





## Postman Collection

Create collection called:


Loan Origination System


Add APIs:

1️⃣ Submit Loan

POST /api/v1/loans


Body

{
"customerName": "Rahul",
"customerPhone": "9876543210",
"loanAmount": 50000,
"loanType": "PERSONAL"
}
2️⃣ Loan Status Count

GET /api/v1/loans/status-count

3️⃣ Get Loans by Status

GET /api/v1/loans?status=APPLIED&page=0&size=5

4️⃣ Top Customers

GET /api/v1/customers/top

5️⃣ Agent Decision

PUT /api/v1/agents/{agentId}/loans/{loanId}/decision


Body

{
"decision": "APPROVE"
}