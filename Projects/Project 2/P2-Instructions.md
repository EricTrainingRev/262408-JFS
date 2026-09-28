# Project Specification: Bank of CLI Part 2

## 1. Overview
Welcome to **Bank of CLI Part 2**! In the previous project, you built the "engine" of the bank in a terminal. Now, it's time to build the "dashboard."

In this project, your mission is to build a modern, professional **Single Page Application (SPA)**. You won't be building the actual backend logic yet—instead, you will focus on creating a high-quality Web UI that is "API-ready." This means you will design the interface and simulate data responses so that when the real backend arrives in the next phase, your UI is ready to plug in and go.

## 2. Your Mission (The Frontend Experience)
Your team is responsible for delivering a responsive, interactive web dashboard that supports the following user flows:

*   **Secure Access:** A polished Login and Registration portal.
    * *Goal:* Handle both successful logins and "error" states (like invalid credentials) using simulated responses.
*   **The Dashboard:** A central hub where users can view their current balance and a list of their most recent transactions.
*   **The Transaction Center:** Interactive forms that allow users to perform:
    * `Deposit`
    * `Withdraw`
    * `Transfer`
    * *Requirement:* Forms must include client-side validation (e.g., preventing users from entering negative numbers or leaving fields empty).
*   **Professional UX (User Experience):**
    * **Loading States:** Example: Show spinners or "skeleton" loaders when a user performs an action to simulate waiting for a server.
    * **Feedback System:** Example: Use "Toast" notifications or pop-up modals to confirm successful actions or alert users to errors.

## 3. Architecture & Technical Requirements
To ensure your UI can easily connect to the real backend later, you must follow a **Service-Based Architecture**. Do not hardcode data directly into your UI components!

1.  **Component Layer (The Visuals):** These are your UI building blocks (Buttons, Inputs, Cards). They are responsible for looking good and being reusable. **Rule:** Components should *never* fetch data themselves; they should ask a Service for it.
2.  **Service Layer (The Mock Engine):** This is the "brain" of your frontend. It handles all data logic. For now, instead of calling a real server, these services will return hardcoded data.
3.  **Contract Layer (The Blueprint):** You will document exactly what the data "looks like" (the JSON structure). This ensures that when the backend team builds the real API, it matches what you've already built.

### The Tech Stack
*   **Framework:** **React** or **Angular** (as assigned to your team).
*   **Language:** **TypeScript** (to ensure your data models are predictable and error-free).
*   **Styling:** **CSS3** (using tools like Tailwind, Bootstrap, or standard CSS) to create a responsive design that works on desktop and mobile.
*   **Data Simulation:** **JSON** used by your Services to mimic real API responses.
*   **Version Control:** Git & GitHub.

## 4. Quality Standards
*   **Reusability:** Build components that can be used in multiple places (e.g., a single `Button` component used for both "Login" and "Transfer").
*   **Type Safety:** Use TypeScript interfaces for everything (Users, Accounts, Transactions) to help prevent bugs.
*   **Responsiveness:** Your application must look professional on all screen sizes, from large monitors to mobile phones.
*   **The "Contract" Rule:** Your mocked data must strictly follow the JSON structure you define in your documentation.

## 5. Roadmap
*Note: the weeks referenced are training weeks*

*   **Week 6: Plan** create wireframes, decide on your core style themes and motifs, start figuring out how you will implement your services, determine API contracts
*   **Week 7: Build** create your web UI with your SPA tool, incorporate the styling, logic, and API contracts in your app

**Good luck, and happy coding!**
