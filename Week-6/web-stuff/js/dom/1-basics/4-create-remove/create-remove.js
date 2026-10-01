// ------------------------------------------------------------------
// Creating & removing elements: building UI with JavaScript
// ------------------------------------------------------------------
// This file is loaded by create-remove.html. Open that page in a browser
// and click the buttons to add and remove boxes.
//
// JavaScript can build new parts of the UI on the fly. The recipe is
// always the same:
//   1. CREATE the element with document.createElement.
//   2. CONFIGURE it (set text, classes, styles).
//   3. INJECT it into the page with appendChild or prepend.
// To remove an element, call element.remove().

// ------------------------------------------------------------------
// 1. CREATE + APPEND
// ------------------------------------------------------------------
// document.createElement('div') makes an empty <div> that exists only in
// memory — it is NOT on the page yet. We configure it, then appendChild
// adds it to the END of the container.
document.getElementById('createElement').addEventListener('click', () => {
    const container = document.getElementById('boxContainer');

    // Step 1: create the element.
    const box = document.createElement('div');

    // Step 2: configure it (text, styles, classes).
    box.textContent = 'A new box (appended)';
    box.style.border = '1px solid #1a73e8';
    box.style.padding = '6px';
    box.style.margin = '4px 0';

    // Step 3: inject it at the end of the container.
    container.appendChild(box);
    console.log('Created a <div> and appended it to the container.');
});

// ------------------------------------------------------------------
// 2. PREPEND
// ------------------------------------------------------------------
// prepend is the same as appendChild, except it adds the element to the
// BEGINNING of the container instead of the end.
document.getElementById('prependElement').addEventListener('click', () => {
    const container = document.getElementById('boxContainer');

    const box = document.createElement('div');
    box.textContent = 'A new box (prepended)';
    box.style.border = '1px solid #f9ab00';
    box.style.padding = '6px';
    box.style.margin = '4px 0';

    container.prepend(box);
    console.log('Created a <div> and prepended it to the container.');
});

// ------------------------------------------------------------------
// 3. REMOVE
// ------------------------------------------------------------------
// element.remove() deletes the element from the page entirely. Here we
// find the first <div> inside the container and remove it.
document.getElementById('removeFirstBox').addEventListener('click', () => {
    const container = document.getElementById('boxContainer');
    const firstBox = container.querySelector('div');
    if (firstBox) {
        firstBox.remove();
        console.log('Removed the first box.');
    } else {
        console.log('No boxes to remove.');
    }
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   document.createElement('tag') -> make a new element (in memory)
//   parent.appendChild(el)        -> add it to the END of a parent
//   parent.prepend(el)             -> add it to the BEGINNING of a parent
//   element.remove()               -> delete the element from the page
//
// The create -> configure -> inject pattern is how dynamic UIs (to-do
// lists, chat messages, search results) are built from JavaScript.
