# HTML Forms

## Table of Contents
* [1. High-Level Overview](#1-high-level-overview)
* [2. Structural Framework: Form Elements and Attributes](#2-structural-framework-form-elements-and-attributes)
* [3. Granular Implementation: Input Elements and Input Types](#3-granular-implementation-input-elements-and-input-types)
* [4. Specialized Inputs: Select and Multi-Select](#4-specialized-inputs-select-and-multi-select)
* [5. The Nuance of Reliability: HTML5 Validation](#5-the-nuance-of-reliability-html5-validation)
* [6. The Final Step: Submitting Forms](#6-the-final-step-submitting-forms)

***

## 1. High-Level Overview
In the ecosystem of the web, **HTML Forms** are the primary mechanism for user interaction and data collection. While HTML provides the structure, forms enable the transition from a static document to a dynamic application by allowing users to send information—such as login credentials, search queries, or contact details—to a server for processing.

This module covers the structural elements, the vast array of input types, the mechanism of selection, the built-in validation rules of HTML5, and the process of data submission.

[↑ Back to Table of Contents](#table-of-contents)
***

## 2. Structural Framework: Form Elements and Attributes

Before diving into individual inputs, we must understand the container that orchestrates the data.

### 2.1 The `<form>` Element
The `<form>` element acts as a wrapper for all interactive controls. It defines how and where the collected data is sent.

### 2.2 Essential Form Attributes
| Attribute | Description | Common Values |
| :--- | :--- | :--- |
| `action` | The URL of the server-side script that will process the form data. | `/submit-form`, `https://api.example.com/login` |
| `method` | The HTTP method used to send the data. | `GET`, `POST` |
| `enctype` | Specifies how form-data should be encoded when submitting to the server (crucial for file uploads). | `application/x-www-form-urlencoded`, `multipart/form-data` |
| `target` | Specifies where to display the response after submitting the form. | `_self`, `_blank` |

> [!IMPORTANT]
> **GET vs. POST:** 
> - **`GET`** appends form data to the URL (e.g., `?name=value`). Use this for non-sensitive data like search queries. It is bookmarkable but has character limits.
> - **`POST`** sends data within the HTTP request body. Use this for sensitive data (passwords) or large amounts of data. It is not bookmarkable and is more secure for data transmission.

[↑ Back to Table of Contents](#table-of-contents)
***

*With the container and its movement rules established, let's look at the specific tools we use to capture user input.*

## 3. Granular Implementation: Input Elements and Input Types

The `<input>` element is the most versatile tool in the form toolkit. Its behavior changes drastically based on its `type` attribute.

### 3.1 The Anatomy of an Input
Every input generally requires a `name` attribute. The `name` is the **key** that the server uses to identify the value sent by the user. The `value` is the **data** that gets sent; for text inputs the value is whatever the user types, but for selection controls (like `<option>`) the value is explicitly set and may differ from the text the user sees.

```html
<input type="text" name="username" placeholder="Enter your name">
```

### 3.2 Common Input Types
| Type | Description | Visual/Behavioral Cue |
| :--- | :--- | :--- |
| `text` | A single-line text field. | Standard typing area. |
| `password` | A text field that masks characters. | Characters appear as dots or asterisks. |
| `email` | A field designed for email addresses. | Triggers email-specific keyboards on mobile. |
| `number` | A field for numeric input. | Often includes up/down stepper arrows. |
| `checkbox` | Allows selecting one or more options from a list. | A small square box. |
| `radio` | Allows selecting only one option from a group. | A small circular button. |
| `file` | Allows the user to select a file from their device. | A "Choose File" button. |
| `submit` | A button that triggers the form submission. | A clickable button. |
| `button` | A generic clickable button (requires JS). | A clickable button with no default action. |

> [!NOTE]
> **Radio buttons group by shared `name`.** All radio inputs with the same `name` belong to the same group, and only one can be selected at a time. Give each group a distinct `name` — this is the same `name`-as-key concept from [3.1](#31-the-anatomy-of-an-input).

### 3.3 Additional Input Attributes
Beyond `name` and `value`, a few attributes are common enough to know up front.

| Attribute | Purpose | Example |
| :--- | :--- | :--- |
| `placeholder` | A hint shown inside the field that disappears when the user types. | `<input type="text" placeholder="Enter your name">` |
| `autocomplete` | Lets the browser suggest previously entered values. | `<input type="text" autocomplete="on">` |
| `disabled` | Makes the control uneditable and **excludes it from submission**. | `<input type="text" disabled>` |
| `readonly` | Makes the control uneditable but **still submits its value**. | `<input type="text" value="fixed" readonly>` |

> [!TIP]
> A `placeholder` is a hint that disappears when the user types; it is **not** a substitute for a `<label>`. A placeholder vanishes and provides no accessible name, so always pair a real `<label>` with your inputs.

### 3.4 Labels and Textareas
**`<label>`** provides a text caption for a control. It is essential for accessibility: screen readers announce the label, and clicking the label focuses or toggles its associated control. The `for` attribute links the label to a control's `id`.

```html
<label for="username">Username:</label>
<input type="text" id="username" name="username">
```

**`<textarea>`** is the multi-line sibling of `<input type="text">`. It is used for longer free-form text (comments, messages, bios). Its size is set with the `rows` and `cols` attributes.

```html
<label for="message">Message:</label>
<textarea id="message" name="message" rows="4" cols="40"></textarea>
```

[↑ Back to Table of Contents](#table-of-contents)
***

*While single-line inputs are common, users often need to choose from predefined lists, which brings us to selection elements.*

## 4. Specialized Inputs: Select and Multi-Select

When options are limited and predefined, `<select>` and `<option>` elements provide a cleaner user experience than raw text inputs.

### 4.1 The Standard `<select>` Menu
A dropdown menu that allows users to pick one option.

```html
<label for="cars">Choose a car:</label>
<select name="cars" id="cars">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
</select>
```

> [!NOTE]
> The `value` attribute is what gets **submitted**; the text between the tags is what the user **sees**. Here the server receives `cars=volvo`, not `cars=Volvo`. If `value` is omitted, the visible text is used as the value.

### 4.2 Multi-Select Capabilities
To allow a user to select more than one option, simply add the `multiple` attribute to the `<select>` tag.

```html
<select name="skills" id="skills" multiple>
  <option value="html">HTML</option>
  <option value="css">CSS</option>
  <option value="js">JavaScript</option>
</select>
```

> [!NOTE]
> A multi-select sends **one key with repeated values** — one `name=value` pair per selected option. Selecting HTML and CSS submits `skills=html&skills=css`.

> [!TIP]
> For very long lists of options, a `<select>` element can become cumbersome. Consider using a "datalist" (`<datalist>`) to provide an autocomplete-style experience.

[↑ Back to Table of Contents](#table-of-contents)
***

*Capturing data is useless if it's incorrect. HTML5 introduced powerful ways to ensure data integrity before it ever reaches the server.*

## 5. The Nuance of Reliability: HTML5 Validation

Client-side validation is the first line of defense. It provides immediate feedback to the user, improving UX and reducing unnecessary server requests.

### 5.1 Common Validation Attributes
| Attribute | Purpose | Example |
| :--- | :--- | :--- |
| `required` | Ensures the field is not left empty. | `<input type="text" required>` |
| `minlength` | Sets the minimum number of characters. | `<input type="password" minlength="8">` |
| `maxlength` | Sets the maximum number of characters. | `<input type="text" maxlength="20">` |
| `min` / `max` | Sets range limits for numeric/date inputs. | `<input type="number" min="1" max="10">` |
| `pattern` | Uses a Regular Expression (Regex) to validate input. | `<input type="text" pattern="[A-Za-z]{3}">` |

> [!NOTE]
> **Bypassing validation:** adding the `novalidate` attribute to the `<form>` element (or `formnovalidate` to a submit button) skips HTML5 validation for that submission. This is useful when you want to handle validation yourself with JavaScript.

### 5.2 The `:invalid` and `:valid` Pseudo-classes
You can use CSS to provide visual feedback based on whether the input meets the validation rules.

```css
input:invalid {
  border: 2px solid red;
}

input:valid {
  border: 2px solid green;
}
```

[↑ Back to Table of Contents](#table-of-contents)
***

*Once the data is captured, validated, and checked, the final step is the journey from the browser to the server.*

## 6. The Final Step: Submitting Forms

Submission is the act of packaging the form's data and sending it to the `action` URL defined in the `<form>` element.

### 6.1 The Submission Trigger
The most common trigger is a `<button type="submit">` or an `<input type="submit">`. When clicked, the browser:
1.  Performs **HTML5 validation**.
2.  If valid, gathers all **input values** along with their `name` attributes.
3.  **Encodes** the data based on the `enctype`.
4.  Sends an **HTTP request** (**GET** or **POST**) to the `action` URL.

The result is a series of `name=value` pairs. For a `GET` request these appear in the URL as a **query string**:

```
?username=alice&cars=volvo&skills=html&skills=css
```

For a `POST` request, the same pairs are sent in the request body instead of the URL.

> [!NOTE]
> **Submitting a form navigates the page.** By default the browser sends the request and then loads the `action` URL, refreshing the page. This is why a form submission feels like a "page reload" — and it's the default behavior that JavaScript's `event.preventDefault()` later overrides.

### 6.2 Data Encoding (The `enctype` Nuance)
Choosing the correct `enctype` is critical for successful server-side processing.

| Encoding Type | When to Use |
| :--- | :--- |
| `application/x-www-form-urlencoded` | The **default**. Used for standard text-based forms. |
| `multipart/form-data` | **Required** when your form contains `<input type="file">`. |
| `text/plain` | Rarely used; sends data as plain text (no encoding). |

> [!WARNING]
> **Always use `multipart/form-data` for file uploads.** If you use the default encoding for a file input, the server will only receive the *name* of the file as a string, not the actual file content.

---
