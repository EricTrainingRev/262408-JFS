# JavaScript DOM Manipulation

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. DOM Structure](#2-dom-structure)
* [3. Selecting Elements from the DOM](#3-selecting-elements-from-the-dom)
* [4. DOM Manipulation](#4-dom-manipulation)
* [5. Traversing the DOM](#5-traversing-the-dom)
* [6. Events and Listeners](#6-events-and-listeners)
* [7. Bubbling & Capturing](#7-bubbling--capturing)

***

## 1. High-Level Overview

The **Document Object Model (DOM)** is a programming interface for web documents. It represents the page so that programs (like JavaScript) can change the document structure, style, and content. This module provides a deep dive into how the DOM works, how to navigate its hierarchy, and how to react to user interactions through events.

[↑ Back to Table of Contents](#table-of-contents)
***

*Now that we understand the "What" of the DOM, let's look at its internal architecture: the structure it creates.*

## 2. DOM Structure

The DOM represents an HTML document as a **Tree Structure**. Every element, attribute, and piece of text in your HTML becomes a **Node** in this tree.

### The Node Hierarchy
In the DOM, everything is a **Node**, but not all nodes are **Elements**.

### Visualizing the Tree
If we have the following HTML:
```html
<div id="parent">
  <!-- A comment -->
  <p class="text">Hello <span>World</span></p>
</div>
```

The resulting tree looks like this:
```text
Document
└── div#parent (Element Node)
     ├── #comment (Comment Node)
     └── p.text (Element Node)
          ├── "Hello " (Text Node)
          └── span (Element Node)
               └── "World" (Text Node)
```

| Node Type | Description | Example |
| :--- | :--- | :--- |
| **Document Node** | The root of the entire DOM tree. | `document` |
| **Element Node** | Represents an HTML tag. | `<div>`, `<h1>`, `<p>` |
| **Text Node** | The actual text content inside an element. | `"Hello World"` |
| **Attribute Node** | Represents the attributes of an element. | `class="container"`, `id="main"` |
| **Comment Node** | Represents HTML comments. | `<!-- This is a comment -->` |
*With the structure understood, the next logical step is learning how to actually target and grab these nodes to work with them.*

## 3. Selecting Elements from the DOM

To change something, you must first find it. JavaScript provides several methods to locate nodes within the tree.

### Selection Methods Comparison

To see these in action, consider this HTML:
```html
<div id="main-container" class="wrapper">
  <h1 class="title">Title</h1>
  <p class="description">First description</p>
  <p class="description">Second description</p>
  <ul class="list">
    <li class="item">Item 1</li>
    <li class="item">Item 2</li>
  </ul>
</div>
```

```javascript
// 1. By ID (returns one element)
const container = document.getElementById('main-container');

// 2. By Class (returns a LIVE HTMLCollection)
const descriptions = document.getElementsByClassName('description');
console.log(descriptions[0].textContent); // "First description"

// 3. By Tag Name (returns a LIVE HTMLCollection)
const paragraphs = document.getElementsByTagName('p');

// 4. querySelector (returns the FIRST match)
const firstDesc = document.querySelector('.description'); 

// 5. querySelectorAll (returns a STATIC NodeList)
const allItems = document.querySelectorAll('.item');
```

| Method | Selects... | Return Type | Speed/Performance |
| :--- | :--- | :--- | :--- |
| **`getElementById()`** | A single element by its unique ID. | Single Element | 🚀 Fastest |
| **`getElementsByClassName()`** | All elements with a specific class. | `HTMLCollection` (Live) | 🏎️ Very Fast |
| **`getElementsByTagName()`** | All elements of a specific tag type. | `HTMLCollection` (Live) | 🏎️ Very Fast |
| **`querySelector()`** | The **first** element matching a CSS selector. | Single Element | 🐢 Slower |
| **`querySelectorAll()`** | **All** elements matching a CSS selector. | `NodeList` (Static) | 🐢 Slower |

### Live vs. Static Collections
One of the most common pitfalls in DOM manipulation is the difference between these two collection types:

1.  **`HTMLCollection` (Live):** If you select all `.item` elements and then add a new `.item` to the page via JavaScript, the collection **automatically updates** to include the new one.
2.  **`NodeList` (Static):** When you use `querySelectorAll`, you get a snapshot. If the DOM changes later, the list you are currently holding **remains the same**.

**`HTMLCollection` vs. `NodeList`**
`HTMLCollection` is strictly for element nodes and is "live." `NodeList` can contain any type of node (text, comments, etc.) and is usually "static" (unless created by `childNodes`).

[↑ Back to Table of Contents](#table-of-contents)
***

*Once you have selected your elements, you can begin to transform the page by modifying their properties.*

## 4. DOM Manipulation

Manipulation involves changing the content, attributes, or style of the nodes you have selected.

### Core Manipulation Tasks

#### 1. Changing Content
There are three primary ways to change the text or HTML inside an element. Choosing the wrong one can lead to **Security Vulnerabilities (XSS)**.

| Property | Description | Security Risk |
| :--- | :--- | :--- |
| **`textContent`** | Sets/gets the raw text content. Ignores HTML tags. | ✅ **Safe** |
| **`innerText`** | Similar to `textContent`, but respects CSS styling (e.g., won't show hidden text). | ✅ **Safe** |
| **`innerHTML`** | Parses the string as HTML. Can create new elements. | ⚠️ **Unsafe** (XSS risk) |

> [!WARNING]
> **XSS Alert:** Never use `innerHTML` with data that comes from a user (like a username or a comment). A malicious user could inject `<script>` tags that execute in other users' browsers. Always prefer `textContent`.

#### 2. Modifying Attributes and Styles
You can interact with an element's properties directly or through dedicated methods.

*   **Attributes:** Use `element.setAttribute('name', 'value')` and `element.getAttribute('name')`.
*   **Classes:** Use `element.classList.add('name')`, `element.classList.remove('name')`, and `element.classList.toggle('name')`. This is preferred over overwriting `className`.
*   **Inline Styles:** Use `element.style.propertyName = 'value'` (e.g., `element.style.backgroundColor = 'red'`).

#### 3. Creating and Removing Elements
To build new parts of the UI:
1.  **Create:** `const newDiv = document.createElement('div');`
2.  **Configure:** `newDiv.textContent = 'I am new!';`
3.  **Inject:** `parentElement.appendChild(newDiv);` or `parentElement.prepend(newDiv);`
4.  **Remove:** `element.remove();`

[↑ Back to Table of Contents](#table-of-contents)
***

*Manipulation is easier when you know how to move through the tree to find related elements.*

## 5. Traversing the DOM

Traversing is the act of moving from one node to another based on their relationship in the tree (Parent, Child, or Sibling).

### Traversal Directions

| Relationship | Property / Method | Description |
| :--- | :--- | :--- |
| **Upwards** | `parentElement` | Moves to the immediate parent. |
| | `closest(selector)` | Moves up the tree to the nearest ancestor matching the selector. |
| **Downwards** | `children` | Returns a live `HTMLCollection` of child **elements**. |
| | `firstElementChild` / `lastElementChild` | Targets the first or last child element. |
| **Sideways** | `nextElementSibling` | Moves to the next sibling element at the same level. |
| | `previousElementSibling` | Moves to the previous sibling element at the same level. |

> [!TIP]
> Always prefer the "**Element**" versions (e.g., `children` vs `childNodes`, `nextElementSibling` vs `nextSibling`). The standard versions include text nodes (whitespace/line breaks), which often cause bugs when you only want to target actual HTML tags.

[↑ Back to Table of Contents](#table-of-contents)
***

*The DOM is static until you add interactivity. That interactivity is driven by Events.*

## 6. Events and Listeners

Events are signals that something has happened in the browser (a click, a keypress, a scroll). **Event Listeners** are the functions that "listen" for these signals and execute code in response.

### The `addEventListener` Pattern

The modern and preferred way to handle events is using the `addEventListener` method.

```javascript
const btn = document.querySelector('#myButton');

function handleClick(event) {
    console.log('Button was clicked!', event.target);
}

// Syntax: element.addEventListener(event, function, options)
btn.addEventListener('click', handleClick);
```

### Common Event Types
*   **Mouse Events:** `click`, `dblclick`, `mouseenter`, `mouseleave`, `mousemove`.
*   **Keyboard Events:** `keydown`, `keyup`, `keypress`.
*   **Form Events:** `submit`, `change`, `input`, `focus`, `blur`.
*   **Window Events:** `load`, `resize`, `scroll`.

### The Event Object (`e`)
When an event occurs, the browser automatically passes an **Event Object** to your handler function. This object contains vital metadata:
*   `e.target`: The element that actually triggered the event.
*   `e.currentTarget`: The element that the listener is attached to.
*   `e.type`: The name of the event (e.g., 'click').
*   `e.key`: (For keyboard events) The specific key pressed.

[↑ Back to Table of Contents](#table-of-contents)
***

*Understanding how to listen for events is only half the battle; you must also understand how events move through the tree.*

## 7. Bubbling & Capturing

When an event happens on an element, it doesn't just trigger on that one element. It travels through the DOM tree in a specific pattern.

### The Two Phases

1.  **Capturing Phase (Trickle Down):** The event starts at the `window` and moves down through the ancestors until it reaches the target element.
2.  **Target Phase:** The event reaches the target element.
3.  **Bubbling Phase (Bubble Up):** The event "bubbles" back up from the target element through its ancestors to the `window`.

**By default, `addEventListener` triggers during the Bubbling phase.**

### Event Delegation: A Powerful Pattern

Instead of adding a listener to every single child element (which is slow and memory-intensive), you can add **one** listener to a common parent. Because events **bubble up**, the parent will catch the event as it passes through.

**Example: Managing a list of items**
```javascript
const list = document.querySelector('#itemList');

// Instead of adding listeners to every <li>, add one to the <ul>
list.addEventListener('click', (e) => {
    // Check if the clicked thing was actually an <li>
    if (e.target.tagName === 'LI') {
        console.log('You clicked item:', e.target.textContent);
    }
});
```

**Pros of Event Delegation:**
*   **Memory Efficiency:** Fewer listeners in memory.
*   **Dynamic Elements:** Works automatically for new elements added to the list after the listener was set.

### Stopping the Flow

Sometimes you want to prevent an event from bubbling up to parents.
*   `e.stopPropagation()`: Stops the event from moving further up (or down) the tree.
*   `e.preventDefault()`: Prevents the **browser's default behavior** (e.g., stops a link from navigating or a form from submitting).

[↑ Back to Table of Contents](#table-of-contents)
