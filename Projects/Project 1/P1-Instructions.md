# Project Specification: Bank of CLI

## 1. Overview
Welcome to the **Bank of CLI** project! In this group project, you will work together to build a functional banking application that runs entirely within the terminal. Your goal is to move from a simple user interface down to a persistent database, applying what you've learned about Java, SQL, and Agile development.

## 2. Your Mission (MVP Features)
Your team is responsible for delivering a "Core Ledger" that supports the following operations:

*   **Secure Access:** Users must be able to register and log in using a unique `Account ID` and `PIN`.
*   **Balance Management:** Users can check their current account balance at any time.
*   **The Transaction Engine:**
    *   `Deposit`: Add funds to an account.
    *   `Withdraw`: Remove funds (the system must prevent overdrawing!).
    *   `Transfer`: Move money securely between two different accounts.
*   **Audit Trail:** Users can view their recent transaction history.
*   **System Logging:** The application must maintain a log file to track activity. You are required to use:
    *   `INFO`: To record successful actions (e.g., "User successfully logged in").
    *   `ERROR`: To record failures or security risks (e.g., "Incorrect PIN entered" or "Database connection lost").

## 3. Architecture & Technical Requirements
To build a scalable and maintainable application, you must follow a **Layered Architecture**. Do not skip layers; each has a specific job:

1.  **API Layer (The Interface):** This is what the user sees. It handles all terminal inputs, menu navigation, and printing messages. This layer *only* talks to the Service Layer.
2.  **Business Layer (The Brains):** This is where the banking rules live (e.g., "Can this user afford this withdrawal?"). It receives calls from the API and calls the Repository Layer
3.  **Repository Layer (The Vault):** This layer handles all communication with the SQLite database. It converts SQL rows into Java objects and vice versa. It *only* receives calls from the Business Layer.

### The Tech Stack
*   **Language:** Java (JDK 21+)
*   **Build Tool:** Maven
*   **Database:** SQLite
*   **Testing:** JUnit 5
*   **Version Control:** Git & GitHub

## 4. Quality Standards: The "2-Test Rule"
We don't just want code that works; we want code that is *proven* to work. For **every** single method you write in the Service and Repository layers, you must provide at least two JUnit 5 tests:
1.  **The Positive Test:** Prove the method works when everything goes right (e.g., a successful deposit).
2.  **The Negative Test:** Prove the method handles errors correctly (e.g., attempting to withdraw more money than is available should be rejected gracefully).

## 5. Professional Implementation Guidelines
As you build, keep these three "Gold Standards" in mind:

*   **Atomicity (All or Nothing):** When performing a `Transfer`, the deduction from one account and the addition to another must happen as one single unit. If one part fails, the whole operation must fail so that money never "vanishes."
*   **Predictable Data:** Always save your SQLite database in a consistent location (e.g., a `./data/` folder in your project) so your data doesn't disappear when you restart your IDE.
*   **Smart Error Handling:** 
    *   **User-Friendly Messages:** If a user makes a mistake (like an incorrect PIN), show them a clear, helpful message.
    *   **Security First:** If the database crashes, do **not** show the user a scary technical stack trace. Instead, log the technical error and show the user a polite "Service Unavailable" type message.

## 6. Roadmap
*Note: the weeks referenced are training weeks*
*   **Week 2: The Interface.** Set up your Maven project and build the CLI menus. Focus on how the user will actually interact with your bank.
*   **Week 3: The Logic.** Build the Service layer, enforce the banking rules, and implement your logging system. This is where you will focus heavily on JUnit testing.
*   **Week 4: The Vault.** Build the Repository layer and connect your app to SQLite. Finish with manual end-to-end testing and your final team demo.

**Good luck, and happy coding!**
