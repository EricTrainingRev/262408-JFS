# HTTP (Hypertext Transfer Protocol)

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. HTTP Methods](#2-http-methods)
* [3. HTTP Lifecycle](#3-http-lifecycle)
* [4. HTTP Status Codes](#4-http-status-codes)
* [5. HTTP Headers](#5-http-headers)
* [6. HTTP Cookies](#6-http-cookies)

***

## 1. High-Level Overview

**HTTP (Hypertext Transfer Protocol)** is the foundation of data exchange on the World Wide Web. It is an **application-layer protocol** that enables communication between clients (typically web browsers) and servers. HTTP operates on a **request-response model**, where a client sends a request for a resource, and the server returns a response containing the requested data or an error message.

HTTP is **stateless**, meaning each request is treated as an independent transaction with no inherent knowledge of previous requests. To manage state (like user sessions), additional mechanisms like **Cookies** or **Tokens** are employed.

[↑ Back to Table of Contents](#table-of-contents)

***

*Now that we understand the fundamental nature of HTTP, let's look at the specific actions a client can perform using HTTP Methods.*

## 2. HTTP Methods

HTTP methods (often called **Verbs**) define the specific action intended to be performed on a resource.

| Method | Type | Description | Idempotent? |
| :--- | :--- | :--- | :--- |
| **GET** | Safe | Retrieves a representation of a specified resource. | Yes |
| **POST** | Unsafe | Submits data to be processed to a specified resource, often resulting in a change in state or side effects. | No |
| **PUT** | Unsafe | Replaces all current representations of the target resource with the request payload. | Yes |
| **PATCH** | Unsafe | Applies partial modifications to a resource. | No |
| **DELETE** | Unsafe | Deletes the specified resource. | Yes |
| **HEAD** | Safe | Identical to `GET`, but transfers no response body. Used to check resource metadata. | Yes |
| **OPTIONS** | Safe | Describes the communication options for the target resource (often used in CORS). | Yes |

> [!IMPORTANT]
> **Idempotency** refers to the property where making the same request multiple times has the same effect as making it once. For example, `GET` is idempotent because reading a resource doesn't change it. `POST` is **not** idempotent because sending the same `POST` request twice might create two separate resources.

[↑ Back to Table of Contents](#table-of-contents)

***

*With the methods defined, we can now examine the chronological flow of how a single interaction occurs through the HTTP Lifecycle.*

## 3. HTTP Lifecycle

The **HTTP Lifecycle** describes the step-by-step process of a client-server interaction.

1.  **DNS Lookup:** The client (browser) resolves the human-readable domain name (e.g., `example.com`) into an **IP Address** via a DNS server.
2.  **TCP Handshake:** The client establishes a connection with the server using the **TCP (Transmission Control Protocol)** three-way handshake (SYN → SYN-ACK → ACK).
3.  **TLS Handshake (HTTPS only):** If using HTTPS, an additional handshake occurs to negotiate encryption keys and establish a secure connection.
4.  **The Request:** The client sends an **HTTP Request Message** containing the method, URL, headers, and an optional body.
5.  **Server Processing:** The server receives the request, interprets it, interacts with databases or files, and prepares a response.
6.  **The Response:** The server sends an **HTTP Response Message** containing the status code, headers, and the resource body (HTML, JSON, Image, etc.).
7.  **Connection Closure/Reuse:** Depending on the headers (like `Connection: keep-alive`), the TCP connection is either closed or kept open for subsequent requests.

> [!NOTE]
> **Acronym Glossary for the Lifecycle:** A quick reference for the acronyms appearing in the steps above.
> *   **DNS (Domain Name System):** A decentralized system that translates human-readable domain names like `example.com` into the numerical IP addresses computers use to locate each other.
> *   **IP (Internet Protocol):** The set of rules that addresses and routes data packets between devices on a network.
> *   **TCP (Transmission Control Protocol):** A reliable transport protocol that establishes a connection between client and server and guarantees ordered, error-checked delivery of data.
> *   **SYN (Synchronize):** A packet sent during the TCP handshake to initiate a connection and synchronize sequence numbers.
> *   **ACK (Acknowledgment):** A packet sent during the TCP handshake to confirm receipt of a preceding packet.
> *   **SYN-ACK:** A combined packet the server sends to acknowledge the client's SYN while synchronizing its own side of the connection, forming the middle step of the three-way handshake.
> *   **TLS (Transport Layer Security):** The cryptographic protocol that encrypts data in transit, providing privacy and integrity for HTTPS connections.
> *   **HTTPS (Hypertext Transfer Protocol Secure):** Encrypted HTTP — HTTP traffic carried over a TLS-secured connection.
> *   **URL (Uniform Resource Locator):** The web address that identifies a specific resource and where it can be found on a network.
> *   **HTTP (Hypertext Transfer Protocol):** The application-layer request-response protocol that defines how clients and servers exchange messages, as introduced in the High-Level Overview.

[↑ Back to Table of Contents](#table-of-contents)

***

*To understand if a request was successful or failed, we must look at the standardized language of HTTP Status Codes.*

## 4. HTTP Status Codes

**Status Codes** are three-digit integers returned by a server to indicate the outcome of an HTTP request. They are categorized by their first digit.

| Range | Category | Meaning |
| :--- | :--- | :--- |
| **1xx** | **Informational** | Request received, continuing process. |
| **2xx** | **Success** | The action was successfully received, understood, and accepted. |
| **3xx** | **Redirection** | Further action needs to be taken to complete the request. |
| **4xx** | **Client Error** | The request contains bad syntax or cannot be fulfilled by the server. |
| **5xx** | **Server Error** | The server failed to fulfill an apparently valid request. |

### Common Status Codes

| Code | Name | Description |
| :--- | :--- | :--- |
| **200** | `OK` | The request succeeded. |
| **201** | `Created` | The request succeeded and a new resource was created (common with `POST`). |
| **301** | `Moved Permanently` | The URL of the requested resource has been changed permanently. |
| **304** | `Not Modified` | Used for caching; tells the client the resource hasn't changed. |
| **400** | `Bad Request` | The server cannot process the request due to a client error. |
| **401** | `Unauthorized` | Authentication is required and has failed or has not yet been provided. |
| **403** | `Forbidden` | The server understood the request but refuses to authorize it. |
| **404** | `Not Found` | The requested resource could not be found on the server. |
| **500** | `Internal Server Error` | A generic error message when the server encounters an unexpected condition. |
| **503** | `Service Unavailable` | The server is currently unable to handle the request (e.g., maintenance/overload). |

[↑ Back to Table of Contents](#table-of-contents)

***

*Status codes provide the "what," but HTTP Headers provide the "how" and "extra details" for both the request and the response.*

## 5. HTTP Headers

**HTTP Headers** allow the client and server to pass additional metadata about the request or response. They consist of a case-insensitive name followed by a colon and its value.

### Request Headers
Sent by the client to provide context about the request.

*   **`Host`**: The domain name of the server (e.g., `Host: example.com`).
*   **`User-Agent`**: Information about the client software (browser, OS, etc.).
*   **`Accept`**: Tells the server what content types the client can handle (e.g., `text/html`, `application/json`).
*   **`Authorization`**: Carries credentials for authenticating the client.
*   **`Content-Type`**: Describes the media type of the resource in the request body.

### Response Headers
Sent by the server to provide metadata about the response or the server itself.

*   **`Server`**: Information about the software used by the origin server.
*   **`Content-Type`**: The media type of the returned resource (e.g., `application/json; charset=utf-8`).
*   **`Content-Length`**: The size of the response body in bytes.
*   **`Set-Cookie`**: Used by the server to send a cookie to the client.
*   **`Cache-Control`**: Directives for caching mechanisms in both browsers and intermediate proxies.

[↑ Back to Table of Contents](#table-of-contents)

***

*Since HTTP is stateless, we need a way to "remember" users between requests. This is achieved through HTTP Cookies.*

## 6. HTTP Cookies

**Cookies** are small pieces of data sent from a server and stored on the client's device. They are the primary mechanism used to implement **state** in the otherwise stateless HTTP protocol.

### How Cookies Work
1.  **Setting a Cookie:** The server includes a `Set-Cookie` header in its response.
2.  **Storage:** The browser stores this cookie locally.
3.  **Sending a Cookie:** For every subsequent request to that same domain, the browser automatically includes the stored cookie in the `Cookie` request header.

### Common Cookie Attributes
To secure and control cookies, several attributes can be appended to the `Set-Cookie` header:

| Attribute | Purpose |
| :--- | :--- |
| **`Expires` / `Max-Age`** | Defines the lifespan of the cookie. Without this, it is a "Session Cookie" (deleted when the browser closes). |
| **`Domain`** | Specifies which hosts are allowed to receive the cookie. |
| **`Path`** | Specifies the URL path that must exist in the requested URL for the cookie to be sent. |
| **`Secure`** | Ensures the cookie is **only** sent over encrypted (HTTPS) connections. |
| **`HttpOnly`** | Prevents client-side scripts (like JavaScript) from accessing the cookie, providing protection against **XSS (Cross-Site Scripting)** attacks. |
| **`SameSite`** | Controls whether cookies are sent with cross-site requests, providing protection against **CSRF (Cross-Site Request Forgery)**. Values: `Strict`, `Lax`, `None`. |

> [!TIP]
> **Security Best Practice:** Always use `HttpOnly` and `Secure` flags for sensitive cookies (like session IDs) to minimize the risk of theft via script injection or network sniffing.

[↑ Back to Table of Contents](#table-of-contents)
