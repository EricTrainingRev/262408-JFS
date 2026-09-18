# AWS Fundamentals: Introduction & Identity Access Management

## Table of Contents

* [1. High-Level Overview: What is AWS?](#1-high-level-overview-what-is-aws)
* [2. AWS Core Concepts: Infrastructure & Models](#2-aws-core-concepts-infrastructure--models)
* [3. Identity and Access Management (IAM)](#3-identity-and-access-management-iam)
    * [3.1 The Four Pillars: Users, Groups, Roles, and Policies](#31-the-four-pillars-users-groups-roles-and-policies)
    * [3.2 IAM Comparison: Users vs. Roles](#32-iam-comparison-users-vs-roles)
* [4. The Golden Rule: Principle of Least Privilege (PoLP)](#4-the-golden-rule-principle-of-least-privilege-polp)
* [5. Summary Checklist](#5-summary-checklist)

***

## 1. High-Level Overview: What is AWS?

**Amazon Web Services (AWS)** is the world's most comprehensive and broadly adopted cloud platform, offering over 200 fully-featured services from data centers globally.

In traditional on-premises computing, organizations must purchase, house, and maintain physical servers. **Cloud Computing** shifts this model, allowing you to rent computing power, storage, and databases on an as-needed basis. This moves capital expenditure (CapEx) to operational expenditure (OpEx).

### Key Benefits of AWS
*   **Agility:** Spin up resources in minutes rather than weeks.
*   **Scalability:** Automatically grow or shrink resources based on demand (Elasticity).
*   **Cost Savings:** Pay only for what you use (Pay-as-you-go).
*   **Global Reach:** Deploy applications in multiple regions around the world with a few clicks.

[↑ Back to Table of Contents](#table-of-contents)
***

*To understand how AWS operates at scale, we must look at the underlying architectural building blocks.*

## 2. AWS Core Concepts: Infrastructure & Models

AWS operates on a massive, global scale, organized into specific logical and physical structures.

### Global Infrastructure
1.  **Regions:** Physical locations around the world where AWS clusters data centers. Each Region is geographically isolated.
2.  **Availability Zones (AZs):** One or more discrete data centers within a Region. AZs are connected via low-latency links and are designed for fault tolerance.
3.  **Edge Locations:** Points of presence used by services like CloudFront (CDN) to cache content closer to end-users.

### Cloud Deployment Models

| Model | Description | Best Use Case |
| :--- | :--- | :--- |
| **Public Cloud** | Everything is hosted on the cloud provider's infrastructure. | Startups, web applications, rapid scaling. |
| **Private Cloud** | Cloud resources used exclusively by one organization (on-prem). | Highly regulated industries (Gov, Banking). |
| **Hybrid Cloud** | A mix of on-premises and public cloud resources. | Legacy migrations, data sovereignty requirements. |

[↑ Back to Table of Contents](#table-of-contents)
***

*While the infrastructure provides the "muscle," security provides the "brain." In AWS, security is centered around Identity and Access Management (IAM).*

## 3. Identity and Access Management (IAM)

**IAM** is a web service that helps you securely control access to AWS resources. It controls **who** (authentication) can do **what** (authorization) to **which** resources.

### 3.1 The Four Pillars: Users, Groups, Roles, and Policies

To manage access, IAM utilizes four primary constructs:

1.  **IAM Users:** A permanent identity representing a person or application. Each user has unique credentials (password or access keys).
2.  **IAM Groups:** A collection of users. Instead of assigning permissions to 50 users individually, you assign them to a "Developers" group.
3.  **IAM Roles:** A temporary identity. Unlike users, roles do not have permanent credentials. They are "assumed" by services (like an EC2 instance) or federated users to perform specific tasks for a limited time.
4.  **IAM Policies:** The actual JSON documents that define permissions. They specify the **Effect** (Allow/Deny), the **Action** (e.g., `s3:GetObject`), and the **Resource** (e.g., a specific S3 bucket).

### 3.2 IAM Comparison: Users vs. Roles

| Feature | **IAM User** | **IAM Role** |
| :--- | :--- | :--- |
| **Credentials** | Permanent (Password/Access Keys). | Temporary (STS Tokens). |
| **Assignment** | Assigned to a specific person/app. | "Assumed" by a service or user. |
| **Use Case** | Daily human login or long-lived app access. | Giving an EC2 instance permission to access S3. |

[↑ Back to Table of Contents](#table-of-contents)
***

*Mastering these identities is only half the battle; the real security challenge lies in how strictly you apply their permissions.*

## 4. The Golden Rule: Principle of Least Privilege (PoLP)

The **Principle of Least Privilege (PoLP)** is the cornerstone of cloud security. It dictates that every identity (User, Group, or Role) must be granted the **absolute minimum** permissions necessary to perform its specific job, and nothing more.

### Why PoLP Matters
In a cloud environment, a single compromised credential with `AdministratorAccess` can lead to a total account takeover. By enforcing PoLP, you limit the "Blast Radius" of a security breach.

### Example: The Wrong Way vs. The Right Way

**❌ The "Lazy" Way (Too Much Privilege):**
Giving a web server `AdministratorAccess` just so it can write files to an S3 bucket.
*Result:* If the web server is hacked, the attacker can delete your entire AWS account.

**✅ The "PoLP" Way (Least Privilege):**
Giving the web server a Role with a policy that *only* allows `s3:PutObject` on one specific bucket.
*Result:* If the web server is hacked, the attacker is trapped within that single bucket.

**Example: A Secure IAM Policy (JSON)**
This policy follows PoLP by restricting access to a single specific bucket.

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": "s3:PutObject",
            "Resource": "arn:aws:s3:::my-app-uploads-bucket/*"
        }
    ]
}
```

[↑ Back to Table of Contents](#table-of-contents)
