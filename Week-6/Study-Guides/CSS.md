# CSS (Cascading Style Sheets)

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. Structural Framework: Stylesheet Types](#2-structural-framework-stylesheet-types)
* [3. Targeting Elements: Selectors](#3-targeting-elements-selectors)
    * [3.1 Basic Selectors](#31-basic-selectors)
    * [3.2 Combinators & Sibling Selectors](#32-combinators--sibling-selectors)
    * [3.3 Advanced Selectors](#33-advanced-selectors)
* [4. The Logic of Style: Cascade & Specificity](#4-the-logic-of-style-cascade--specificity)
    * [4.1 The Cascading Nature of CSS](#41-the-cascading-nature-of-css)
    * [4.2 Specificity](#42-specificity)
* [5. The Fundamental Unit: The Box Model](#5-the-fundamental-unit-the-box-model)
* [6. Granular Implementation: Properties & Variables](#6-granular-implementation-properties--variables)
    * [6.1 Core CSS Properties](#61-core-css-properties)
    * [6.2 CSS Variables (Custom Properties)](#62-css-variables-custom-properties)
    * [6.3 Positioning](#63-positioning)
* [7. Modern Layout Systems](#7-modern-layout-systems)
    * [7.1 Flexbox](#71-flexbox)
    * [7.2 CSS Grid](#72-css-grid)
* [8. Adaptive Design: Responsive Web Design](#8-adaptive-design-responsive-web-design)

***

## 1. High-Level Overview

**CSS (Cascading Style Sheets)** is the language used to describe the presentation of a document written in a markup language like HTML. While HTML provides the **structure** (the bones), CSS provides the **style** (the skin, clothes, and layout). 

CSS allows developers to separate content from presentation, enabling:
- **Consistency:** Apply the same styles across an entire website.
- **Efficiency:** Change the look of a thousand pages by editing a single stylesheet.
- **Adaptability:** Create layouts that work on everything from tiny smartphones to massive monitors.

[↑ Back to Table of Contents](#table-of-contents)

***

*Now that we know what CSS is, let's look at how we actually connect it to our HTML documents.*

## 2. Structural Framework: Stylesheet Types

There are three primary ways to apply CSS to an HTML document. Choosing the right method depends on the scale and scope of the styles you need.

| Type | Implementation | Best Use Case | Pros | Cons |
| :--- | :--- | :--- | :--- | :--- |
| **Inline Styles** | Directly in the HTML element via the `style` attribute. | Quick fixes or highly specific dynamic changes. | High specificity; no extra files. | Hard to maintain; bloats HTML; violates separation of concerns. |
| **Internal Styles** | Inside a `<style>` block within the HTML `<head>`. | Single-page websites or unique page-specific styles. | No extra HTTP request; easy for small projects. | Not reusable across pages; increases file size. |
| **External Styles** | In a separate `.css` file linked via `<link>`. | **Standard for all production websites.** | Maximum reusability; cleaner HTML; cached by browsers. | Requires an extra HTTP request. |

**Example: External Stylesheet Link**
```html
<head>
  <link rel="stylesheet" href="styles.css">
</head>
```

[↑ Back to Table of Contents](#table-of-contents)

***

*With the stylesheets connected, we need a way to tell CSS exactly which HTML elements we want to style. This is where Selectors come in.*

## 3. Targeting Elements: Selectors

**Selectors** are the patterns used to "select" the HTML elements you want to style.

### 3.1 Basic Selectors

Most styling begins with the most fundamental ways to target elements.

| Selector | Syntax | Description | Example |
| :--- | :--- | :--- | :--- |
| **Element** | `tag` | Targets all elements of a specific HTML type. | `p { color: blue; }` |
| **Class** | `.className` | Targets all elements with a specific `class` attribute. | `.btn { padding: 10px; }` |
| **ID** | `#idName` | Targets a **single, unique** element with a specific `id`. | `#main-header { font-size: 2em; }` |

**Class vs. ID**
The fundamental difference is **reusability**. A **Class** can be used on as many elements as you like (e.g., every button on your site). An **ID** should be used exactly **once** per page (e.g., the unique navigation bar).

[↑ Back to Table of Contents](#table-of-contents)

### 3.2 Combinators & Sibling Selectors

Sometimes, you want to style an element based on its relationship to another element in the HTML tree.

*   **Descendant Selector (`space`)**: Targets an element inside another element.
    *   `div p` targets all `<p>` elements that are anywhere inside a `<div>`.
*   **Child Selector (`>`)**: Targets only the *immediate* children of an element.
    *   `div > p` targets only `<p>` elements that are direct children of a `<div>`.
*   **Adjacent Sibling Selector (`+`)**: Targets an element that is directly preceded by another.
    *   `h1 + p` targets only the first `<p>` that appears immediately after an `<h1>`.
*   **General Sibling Selector (`~`)**: Targets all siblings that follow a specific element.
    *   `h1 ~ p` targets all `<p>` elements that share the same parent as an `<h1>` and come after it.

[↑ Back to Table of Contents](#table-of-contents)

### 3.3 Advanced Selectors

Modern CSS provides powerful "pseudo-class" selectors to target elements based on their state or position.

| Selector | Description |
| :--- | :--- |
| **`:hover`** | Targets an element when the user mouses over it. |
| **`:nth-child(n)`** | Targets the nth child of a parent (e.g., `:nth-child(2)` selects the second child). |
| **`:not(selector)`** | Targets everything *except* the specified selector. |
| **`:first-child` / `:last-child`** | Targets the very first or very last child in a container. |

[↑ Back to Table of Contents](#table-of-contents)

***

*With the ability to target elements established, a question arises: what happens when two different rules target the same element? The answer lies in the Cascade.*

## 4. The Logic of Style: Cascade & Specificity

CSS is "Cascading" because when multiple rules apply to the same element, the browser must follow a specific set of rules to decide which one "wins."

### 4.1 The Cascading Nature of CSS

The Cascade resolves conflicts using three main criteria, in order of priority:

1.  **Importance:** Rules marked with `!important` override everything else.
2.  **Specificity:** The more specific the selector, the higher its priority.
3.  **Source Order:** If importance and specificity are equal, the rule that appears **last** in the CSS file wins.

> [!WARNING]
> **`!important` is a red flag, not a tool of first resort.** It sits at the top of the Cascade, but reaching for it usually signals a specificity problem elsewhere. Overusing `!important` makes stylesheets impossible to override and debug. Prefer writing more specific selectors; reserve `!important` for the rare case where you genuinely cannot change the source or ordering of a rule (e.g., overriding a third-party library).

### 4.2 Specificity

**Specificity** is the mathematical weight used by the browser to decide which CSS rule "wins" when multiple rules target the same element. It is calculated using a three-column scoring system: **ID**, **CLASS**, and **TYPE**.

#### The Specificity Scoring System

When comparing two selectors, the browser compares these columns from **left to right**. The first column that has a higher value determines the winner, regardless of the values in the subsequent columns.

| Category | Column | What it counts | Examples |
| :--- | :--- | :--- | :--- |
| **ID** | **1st** | ID selectors | `#header`, `#login-btn` |
| **CLASS** | **2nd** | Classes, Attributes, and Pseudo-classes | `.btn`, `[type="text"]`, `:hover`, `:nth-child(2)` |
| **TYPE** | **3rd** | Elements and Pseudo-elements | `div`, `h1`, `p`, `::before`, `::placeholder` |

> [!IMPORTANT]
> **The "Left-to-Right" Rule:** An ID selector (e.g., `1-0-0`) will **always** beat any number of classes (e.g., `0-99-99`). The weight of the leftmost column is absolute.

#### Comparison Examples

| Selector | Score (ID-CLASS-TYPE) | Explanation |
| :--- | :--- | :--- |
| `p` | `0-0-1` | One element type. |
| `div p` | `0-0-2` | Two element types. |
| `.card` | `0-1-0` | One class. |
| `div .card` | `0-1-1` | One class + one element. |
| `[type="text"]` | `0-1-0` | Attribute selectors count as **Classes**. |
| `#main-nav .link` | `1-1-0` | One ID + one class. |
| `#main-nav p:hover` | `1-1-1` | One ID + one pseudo-class + one element. |

#### Complex Selectors & Exceptions

*   **Combinators:** Operators like `+`, `>`, `~`, and ` ` (space) help target specific elements but **add zero weight** to the specificity score.
*   **The Universal Selector (`*`) and `:where()`:** These have a specificity of `0-0-0`. They are useful for resetting styles without affecting the weight of other rules.
*   **The `:is()`, `:has()`, and `:not()` Rule:** These pseudo-classes themselves add **no weight**. However, the selectors *inside* their parentheses **do** count. 
    *   *Example:* `:is(p, #id)` has a specificity of `1-0-0` because it inherits the weight of the most specific selector in its list.
*   **Pseudo-elements:** Selectors like `::before` and `::after` count as **Type** (`0-0-1`).

#### Resolving Ties (When Scores are Equal)

If two selectors have the exact same score (e.g., both are `0-1-1`), the browser uses two tie-breaking rules:

1.  **Scoping Proximity:** (Advanced) The selector closer to the element in the DOM tree wins.
2.  **Source Order:** The rule that appears **last** in the CSS file wins.

> [!TIP]
> **Keep it Low:** To make your CSS maintainable, aim for the lowest specificity possible. Use classes instead of IDs, and avoid deeply nested selectors (e.g., `body div main ul li a`) which create "specificity bloat."

[↑ Back to Table of Contents](#table-of-contents)

***

*Now that we understand how rules are applied, let's look at the fundamental building block of every single element on a webpage: the Box Model.*

## 5. The Fundamental Unit: The Box Model

In CSS, every single element is treated as a **rectangular box**. The **Box Model** describes the layers of space that make up this box.

Understanding the Box Model is essential for controlling the size, spacing, and alignment of your elements.

| Layer | Name | Description |
| :--- | :--- | :--- |
| **Layer 1 (Innermost)** | **Content** | The actual text, image, or video inside the element. |
| **Layer 2** | **Padding** | Transparent space *inside* the element, between the content and the border. |
| **Layer 3** | **Border** | A line that wraps around the padding and content. |
| **Layer 4 (Outermost)**| **Margin** | Transparent space *outside* the element, used to create distance between this element and others. |

Think of the layers as concentric boxes stacked from inside to outside:

```text
+--------------------------------------------------------------+
|                             Margin                           |
|   +------------------------------------------------------+   |
|   |                         Border                       |   |
|   |   +----------------------------------------------+   |   |
|   |   |                     Padding                  |   |   |
|   |   |   +--------------------------------------+   |   |   |
|   |   |   |                 Content              |   |   |   |
|   |   |   +--------------------------------------+   |   |   |
|   |   +----------------------------------------------+   |   |
|   +------------------------------------------------------+   |
+--------------------------------------------------------------+
```

Each layer wraps the one before it: content sits at the center, padding surrounds it, the border marks the edge, and margin pushes the whole box away from its neighbors.

### The `box-sizing` Property

By default, adding padding or borders to an element makes the element **larger** than the width/height you set. This is often frustrating.

*   **`content-box` (Default):** Width = Content only. (Total width = Width + Padding + Border).
*   **`border-box` (Recommended):** Width = Content + Padding + Border. This makes sizing much more intuitive.

```css
/* Use this globally to make layout math easier */
* {
  box-sizing: border-box;
}
```

[↑ Back to Table of Contents](#table-of-contents)

***

*With the "box" established, we can now start filling it with content and color using properties and variables.*

## 6. Granular Implementation: Properties & Variables

### 6.1 Core CSS Properties

Properties are the individual instructions you give to a selector.

*   **Typography:** `font-family`, `font-size`, `font-weight`, `text-align`, `line-height`.
*   **Color & Background:** `color`, `background-color`, `background-image`, `opacity`.
*   **Sizing & Spacing:** `width`, `height`, `padding`, `margin`, `border`.
*   **Visual Effects:** `box-shadow`, `border-radius`, `opacity`.

A few properties are rarely useful in isolation, so here is a minimal rule that combines a font, a color, and spacing into one visible result:

```css
/* Typography, color, and spacing working together */
.card {
  font-family: Arial, sans-serif;  /* readable, widely available font */
  font-size: 16px;
  line-height: 1.5;                /* comfortable line spacing */
  color: #222;                     /* near-black body text */
  background-color: #f8f8f8;       /* subtle light background */
  padding: 16px;                   /* breathing room inside the box */
  border-radius: 8px;
}
```

### 6.2 CSS Variables (Custom Properties)

**CSS Variables** allow you to store specific values (like a brand color) in one place and reuse them throughout your entire stylesheet. This makes large-scale styling much easier to maintain.

**How to use them:**

1.  **Declare** them in a scope (usually `:root` for global access) using the `--` prefix.
2.  **Use** them with the `var()` function.

```css
:root {
  --primary-color: #3498db;
  --spacing-unit: 16px;
}

.button {
  background-color: var(--primary-color);
  padding: var(--spacing-unit);
}
```

### 6.3 Positioning

The box model and layout systems control how elements *flow*, but **`position`** controls where an element is *placed* — including lifting it out of the normal flow entirely. The `position` property has four commonly used values:

| Value | Behavior |
| :--- | :--- |
| **`static`** | The default. The element sits in the normal page flow. |
| **`relative`** | Positions the element relative to *its normal spot*. You offset it with `top`/`right`/`bottom`/`left`, and the original space it occupied is preserved. |
| **`absolute`** | Removes the element from the flow and positions it relative to its nearest **positioned** ancestor (an ancestor with `position` set to anything other than `static`). If none exists, it positions relative to the page. |
| **`fixed`** | Removes the element from the flow and positions it relative to the **viewport** (the visible browser window). It stays put when the page scrolls. |

> [!NOTE]
> **`relative` + `absolute` is the classic pair.** To pin an element to a corner of a container, give the container `position: relative;` (so it becomes the "positioned ancestor") and the child `position: absolute;` — the child then positions itself relative to that container rather than the whole page.

> [!TIP]
> **How to center a `<div>`.** For *block* elements, the horizontal centering is one line: `margin: 0 auto;` on the element you want centered (it works only when a `width` is set, otherwise the element is already full-width). To center something both horizontally and vertically, flexbox is the modern way — put `display: flex; align-items: center; justify-content: center;` on the *parent* container, and its child is centered on both axes. The `margin: 0 auto;` trick is not the same as flexbox centered alignment, and the two are frequently conflated.

[↑ Back to Table of Contents](#table-of-contents)

***

*Styling individual elements is one thing, but how do we organize them into complex, beautiful layouts? We use modern layout systems.*

## 7. Modern Layout Systems

Before modern CSS, layouts were built using messy `float` and `table` hacks. Today, we have two powerful, dedicated systems: **Flexbox** and **Grid**.

### 7.1 Flexbox (Flexible Box Layout)

**Flexbox** is a **one-dimensional** layout system. It is designed to lay out items in a single direction—either a row or a column. It is perfect for components like navigation bars, centering items, or distributing space evenly.

*   **Key Concept:** You apply `display: flex;` to a **container** to turn its children into "flex items."
*   **Main Axis vs. Cross Axis:** Flexbox operates on two axes. You can align items along the main axis (`justify-content`) or the cross axis (`align-items`).

### 7.2 CSS Grid

**CSS Grid** is a **two-dimensional** layout system. It allows you to work with both **rows and columns** simultaneously. It is the best tool for building the "macro" layout of an entire webpage.

*   **Key Concept:** You apply `display: grid;` to a container and define your tracks using `grid-template-columns` and `grid-template-rows`.
*   **The Grid Area:** You can name specific areas of your grid and place elements into them with ease.

**Flexbox vs. Grid**

| Feature | Flexbox | CSS Grid |
| :--- | :--- | :--- |
| **Dimension** | 1D (Row **OR** Column) | 2D (Row **AND** Column) |
| **Best For** | Aligning items in a line (e.g., a menu). | Complex, full-page layouts. |
| **Approach** | Content-first (items dictate space). | Layout-first (you define the structure). |

[↑ Back to Table of Contents](#table-of-contents)

***

*Finally, all these layout and styling techniques must work together to ensure your website looks great on every device, from a mobile phone to a desktop.*

## 8. Adaptive Design: Responsive Web Design

**Responsive Web Design (RWD)** is the practice of creating websites that "respond" to the user's screen size, orientation, and platform.

The core pillars of RWD are:

1.  **Fluid Grids:** Using relative units (like `%`, `vw`, `vh`) instead of fixed units (like `px`) so elements resize proportionally.
2.  **Flexible Images:** Ensuring images don't overflow their containers (e.g., `max-width: 100%;`).
3.  **Media Queries:** The most critical tool. Media queries allow you to apply different CSS rules based on the device characteristics (usually width).

### Choosing Units

When deciding between fixed and relative units, prefer relative ones for anything that should adapt to the screen. Two of the most useful relative units are:

*   **`em`:** Relative to the **parent element's** font size. Good for sizing things in relation to their context.
*   **`rem`:** Relative to the **root element's** (the `<html>` tag's) font size. This is the modern **default for font sizing** because it responds to the user's browser font settings and keeps sizing predictable regardless of nesting.

As a rule of thumb: use `rem` (or `%`) for fonts and spacing you want to scale consistently, `px` for fine details like borders, and `%`/`vw`/`vh` for fluid layout sizes.

**Example: A Simple Media Query**
```css
/* Default mobile styles (Mobile First) */
.container {
  display: flex;
  flex-direction: column;
}

/* Desktop styles (When screen is wider than 768px) */
@media (min-width: 768px) {
  .container {
    flex-direction: row;
  }
}
```

[↑ Back to Table of Contents](#table-of-contents)
