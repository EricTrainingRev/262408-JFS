# P1 Presentation Requirements

## General Requirements

- All team members must participate in the presentation.
- Presentations should be approximately 20 minutes in length.
- Focus on demonstrating the product from a user and stakeholder perspective.
- Do **not** perform a code walkthrough unless specifically requested by the instructor.

---

# Presentation Agenda

## 1. Introduction (2-3 Minutes)

### Team Introduction
Briefly introduce:
- Team name
- Team members
- Individual contributions or areas of ownership

### Product Introduction
Provide a high-level overview of your application.

At a minimum, explain:
- What the Bank of CLI is
- What business needs it addresses
- Who the intended users are

### Presentation Overview
Briefly explain what the audience will see:
- Product demonstration
- Discussion of project outcomes
- Team retrospective

---

## 2. Product Walkthrough (12-15 Minutes)

Treat this section like a stakeholder demo. Show the functionality your team delivered and explain how it satisfies the project requirements.

### Secure Access

Demonstrate:
- User registration
- User login
- Successful authentication
- Failed authentication attempt (incorrect PIN)

Discuss:
- How account security is enforced
- User-friendly handling of login failures

**Project Requirements Covered**
- Secure Access
- Logging (ERROR events for failed login attempts)

---

### Balance Management

Demonstrate:
- Viewing the current account balance

Discuss:
- How users can monitor available funds

**Project Requirements Covered**
- Balance Management

---

### Deposit Transaction

Demonstrate:
- Depositing funds into an account
- Updated balance after deposit

Discuss:
- How deposits are recorded

**Project Requirements Covered**
- Deposit
- Logging (INFO events)

---

### Withdrawal Transaction

Demonstrate:
- Successful withdrawal
- Attempted withdrawal that exceeds available funds

Discuss:
- Overdraft protection
- User-facing error messages

**Project Requirements Covered**
- Withdraw
- Smart Error Handling
- Logging (INFO and ERROR events)

---

### Transfer Transaction

Demonstrate:
- Transfering funds between two accounts
- Updated balances for both accounts

Discuss:
- How transfers are processed safely
- Why atomic transactions are important

**Project Requirements Covered**
- Transfer
- Atomicity ("All or Nothing" transactions)

---

### Transaction History

Demonstrate:
- Viewing recent account activity

Discuss:
- How users can audit account activity

**Project Requirements Covered**
- Audit Trail

---

### Data Persistence

Demonstrate:
- Closing the application
- Restarting the application
- Verifying previously saved account data remains available

Discuss:
- SQLite integration
- Consistent database storage

**Project Requirements Covered**
- Repository Layer
- Predictable Data
- SQLite Database

---

### System Logging

Demonstrate:
- Examples of INFO logs
- Examples of ERROR logs

Example Events:
- Successful login
- Successful deposit
- Failed login attempt
- Failed withdrawal attempt

Discuss:
- Why logging is important for troubleshooting and auditing

**Project Requirements Covered**
- System Logging
- INFO Logging
- ERROR Logging

---

### Tests

Demonstrate: 
- Run your tests
    - have a pre-made test report ready as a fall-back in case the live run goes wrong (screenshot is fine)

Discuss:
- What was your experience integrating tests into your workflow?
    - was it useful?
    - was it an inconvenience?
    - as they exist, do you think the tests will help with refactoring the app in the future when migrating from a CLI app to a Spring app?

**Project Requirement Covered**
- 2 Test Rule

---

## 3. Mini Retrospective (3-5 Minutes)

As a team, reflect on the project experience.

### What Went Well

Examples:
- Strong team communication
- Effective Git workflow
- Clear division of responsibilities
- Successful testing strategy

---

### What Could Be Improved

Examples:
- Earlier integration testing
- More frequent code reviews
- Better sprint planning
- Increased test coverage

---

### If We Had Another Sprint

Discuss future enhancements you would like to implement.

Examples:
- Savings and checking account types
- PIN encryption
- Account lockout after repeated failed login attempts
- Scheduled transfers
- Improved user interface
- Administrative reporting tools
- Expanded transaction history filtering

---

## Presentation Tips

### Focus on the User Experience
When demonstrating features, explain:
- What the user is doing
- What the system is doing
- Why the feature is valuable

### Avoid Code Walkthroughs
Instead of discussing implementation details, demonstrate the completed functionality and business value.

### Share Participation
Ensure each team member presents at least one section of the product walkthrough and contributes to the retrospective discussion.

### Prepare your Speech
This is a product presentation, not an oral exam. Write down what you want to say as part of your presentation: this can help to keep your presentation in time and calm your nerves if you don't like public speaking

### Prepare Visual Aids
A slide deck is not just nice to look at: it acts as a guide to help keep the presentation on track while helping the audience keep track of where you are in the presentation. Make sure not to read off your slides.