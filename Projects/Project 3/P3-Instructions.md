# Project Specification: Bank of CLI Part 3

## 1. Overview
Welcome to the grand finale: **Bank of CLI Part 3**! 

In the first two phases, you built the "Engine" (the logic) and the "Dashboard" (the UI). Now, it is time to bring them together. Your mission is to bridge the gap between the frontend and the backend, transforming your previous projects into a single, cohesive, and secure **Full Stack Web Application**.

This phase is about moving from "simulated" to "real." You will replace mock data with real HTTP calls, swap simple authentication for professional-grade security, and finally see your application running as a unified system.

## 2. Your Mission (The Full-Stack Integration)
Your team is responsible for completing the "Full-Stack Lifecycle." The core goal is to ensure that a user can interact with the web interface and have those actions reflected in a live database via a web server.

* **The Great Migration:** Convert your existing Java CLI logic into a **Spring Boot** REST API.
* **Secure Access (JWT):** Implement professional, stateless authentication. Your UI must handle logging in, securely storing a **JSON Web Token (JWT)**, and sending that token back to the server to prove who the user is.
* **The Live Connection:** Replace all the "Mock Services" from Part 2 with real HTTP requests. Your UI should now "talk" to your Spring Boot server to fetch balances, perform transfers, view history, etc.
* **End-to-End Flow:** A user must be able to complete a full journey—Register $\rightarrow$ Login $\rightarrow$ Check Balance $\rightarrow$ Perform Transaction $\rightarrow$ View History—all through the browser, with data persisting in your database.

## 3. Architecture & Technical Requirements
To succeed, you must maintain a strictly decoupled architecture. The frontend and backend should be treated as two separate entities that communicate over a network.

1.  **Client Tier (The Browser):** Your React or Angular application. It handles the user's clicks and displays data. It communicates **only** via HTTP to the API.
2.  **Network Tier (The Bridge):** The communication happens via **RESTful API** calls using **JSON** as the language. Authentication is handled via **JWT** headers.
3.  **Server Tier (The API):** Your **Spring Boot** application. It listens for HTTP requests, enforces banking rules, and manages security.
4.  **Data Tier (The Vault):** Your **SQLite** (or other) database. It stores the truth about every account and transaction.

### The Tech Stack
* **Frontend:** React or Angular (TypeScript)
* **Backend:** Spring Boot (Java)
* **Security:** JWT (JSON Web Tokens)
* **Communication:** REST / JSON
* **Database:** SQLite or PostgreSQL

## 4. Quality Standards
* **API Contract Adherence:** Your Spring Boot server must return exactly the JSON structure that your frontend expects.
* **Secure Communication:** Ensure that "protected" actions (like withdrawing money) cannot be performed without a valid, unexpired JWT.
* **End-to-End Reliability:** A successful transaction must be "atomic"—if the money is taken from one account, it *must* arrive in the other, or the whole operation must fail.

## 5. Roadmap
*Note: the weeks referenced are training weeks*

* **Week 8: The Migration.** Transform your Java logic into a Spring Boot REST API. Implement JWT authentication and get your "Local Full-Stack" working (where your UI talks to your local Spring server).
* **Week 9: Integration & Stretch.** Finalize the end-to-end user flow and polish the UI. 
    * *Stretch Goal:* If your team finishes early, try "Containerizing" your app with **Docker** or deploying it to the cloud (**AWS**)!

**Good luck—it's time to bring the Bank of CLI to life!**
