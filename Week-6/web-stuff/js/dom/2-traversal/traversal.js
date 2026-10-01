// ------------------------------------------------------------------
// Traversing the DOM: moving through the tree
// ------------------------------------------------------------------
// This file is loaded by traversal.html. Open that page in a browser and
// use the buttons to move a "current" highlight through the nested
// structure, watching the status bar update.
//
// Traversal means moving from one element to a RELATED one based on
// their position in the DOM tree. There are three directions:
//   UP      -> parentElement, closest()
//   DOWN    -> children, firstElementChild, lastElementChild
//   SIDEWAYS-> nextElementSibling, previousElementSibling
//
// We keep a variable holding the element we are currently "visiting",
// and each button moves that highlight to a related element.

// The element we are currently visiting. We start on the first child.
let current = document.getElementById('firstChild');

// A helper that updates the highlight and the status bar. It removes the
// .current class from the old element, adds it to the new one, and shows
// a short description of where we are.
function moveTo(element, label) {
    if (!element) {
        // If there is no element in that direction, we stay put.
        console.log('No element in that direction — staying here.');
        return;
    }
    // Remove the highlight from the previous element.
    current.classList.remove('current');
    // Move to the new element and highlight it.
    current = element;
    current.classList.add('current');
    // Show where we are now. The id may be empty (e.g. <body>), so we
    // fall back to the tag name for a readable label.
    const name = current.id || current.tagName.toLowerCase();
    const description = `${label} -> ${name}`;
    document.getElementById('status').textContent = description;
    console.log('Moved to:', description);
}

// ------------------------------------------------------------------
// 1. UP: parentElement
// ------------------------------------------------------------------
// parentElement returns the immediate parent of an element. Moving up
// from a paragraph takes you to the card that contains it.
document.getElementById('toParent').addEventListener('click', () => {
    moveTo(current.parentElement, 'parentElement');
});

// ------------------------------------------------------------------
// 2. DOWN: firstElementChild / lastElementChild
// ------------------------------------------------------------------
// firstElementChild returns the FIRST child element; lastElementChild
// returns the LAST one. (The "Element" versions skip text nodes, which
// is what we want — see the summary below.)
document.getElementById('toFirstChild').addEventListener('click', () => {
    moveTo(current.firstElementChild, 'firstElementChild');
});

document.getElementById('toLastChild').addEventListener('click', () => {
    moveTo(current.lastElementChild, 'lastElementChild');
});

// ------------------------------------------------------------------
// 3. SIDEWAYS: nextElementSibling / previousElementSibling
// ------------------------------------------------------------------
// nextElementSibling returns the next sibling at the same level;
// previousElementSibling returns the one before it. Moving sideways
// from the first paragraph takes you to the middle one, then the last.
document.getElementById('toNext').addEventListener('click', () => {
    moveTo(current.nextElementSibling, 'nextElementSibling');
});

document.getElementById('toPrev').addEventListener('click', () => {
    moveTo(current.previousElementSibling, 'previousElementSibling');
});

// ------------------------------------------------------------------
// 4. UP: closest()
// ------------------------------------------------------------------
// closest(selector) walks UP the tree from the current element and
// returns the NEAREST ancestor that matches the selector. Here we look
// for the nearest ancestor that is a <div> with class "card".
document.getElementById('toClosest').addEventListener('click', () => {
    moveTo(current.closest('.card'), 'closest(".card")');
});

// ------------------------------------------------------------------
// 5. RESET
// ------------------------------------------------------------------
// Jump back to the starting element.
document.getElementById('reset').addEventListener('click', () => {
    moveTo(document.getElementById('firstChild'), 'Reset to firstChild');
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   UP      : parentElement, closest(selector)
//   DOWN    : children, firstElementChild, lastElementChild
//   SIDEWAYS: nextElementSibling, previousElementSibling
//
// Prefer the "Element" versions (firstElementChild, nextElementSibling)
// over the plain ones (firstChild, nextSibling). The plain versions
// include TEXT nodes — whitespace and line breaks between tags — which
// often cause bugs when you only want actual HTML elements.
