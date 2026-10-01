// ------------------------------------------------------------------
// Selecting elements: finding nodes in the DOM
// ------------------------------------------------------------------
// This file is loaded by selecting.html. Open that page in a browser,
// click the buttons, and watch the console (F12) — each button uses a
// different method to find elements on the page.
//
// The DOM is the browser's tree representation of your HTML. Before you
// can change anything, you must first FIND it. JavaScript offers five
// main ways to grab elements, and they differ in what they return.

// ------------------------------------------------------------------
// 1. getElementById
// ------------------------------------------------------------------
// Returns a SINGLE element. IDs must be unique on a page, so this is
// the fastest and most direct way to grab one specific element.
document.getElementById('selectById').addEventListener('click', () => {
    const container = document.getElementById('main-container');
    console.log('getElementById ->', container);
});

// ------------------------------------------------------------------
// 2. getElementsByClassName
// ------------------------------------------------------------------
// Returns a LIVE HTMLCollection of every element with that class.
// "Live" means the collection updates automatically if the DOM changes.
// Access items by index.
document.getElementById('selectByClass').addEventListener('click', () => {
    const descriptions = document.getElementsByClassName('description');
    console.log('getElementsByClassName ->', descriptions);
    console.log('first description text:', descriptions[0].textContent);
});

// ------------------------------------------------------------------
// 3. getElementsByTagName
// ------------------------------------------------------------------
// Returns a LIVE HTMLCollection of every element with that tag name
// (e.g. all <p> tags). Also accessed by index.
document.getElementById('selectByTag').addEventListener('click', () => {
    const paragraphs = document.getElementsByTagName('p');
    console.log('getElementsByTagName ->', paragraphs);
    console.log('number of <p> elements:', paragraphs.length);
});

// ------------------------------------------------------------------
// 4. querySelector
// ------------------------------------------------------------------
// Returns the FIRST element matching a CSS selector. Because it accepts
// any CSS selector ('.class', '#id', 'div > p', etc.) it is very
// flexible, but it is slower than the getElement* methods.
document.getElementById('selectQuery').addEventListener('click', () => {
    const firstDesc = document.querySelector('.description');
    console.log('querySelector(".description") ->', firstDesc);
});

// ------------------------------------------------------------------
// 5. querySelectorAll
// ------------------------------------------------------------------
// Returns a STATIC NodeList of every element matching a CSS selector.
// Unlike the HTMLCollections above, this is a SNAPSHOT — it does NOT
// update if the DOM changes after you grab it.
document.getElementById('selectQueryAll').addEventListener('click', () => {
    const allItems = document.querySelectorAll('.item');
    console.log('querySelectorAll(".item") ->', allItems);
    console.log('number of .item elements:', allItems.length);
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   getElementById        -> single element, fastest
//   getElementsByClassName-> live HTMLCollection
//   getElementsByTagName  -> live HTMLCollection
//   querySelector         -> first match, any CSS selector
//   querySelectorAll      -> static NodeList of all matches
//
// The key distinction to remember: getElement* methods return LIVE
// collections, while querySelectorAll returns a STATIC snapshot.
