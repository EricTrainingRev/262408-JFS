// ------------------------------------------------------------------
// Event delegation: one listener for many elements
// ------------------------------------------------------------------
// This file is loaded by event-delegation.html. Open that page in a
// browser and click the list items, then add new ones — every item
// responds, even the ones added after the page loaded.
//
// The naive approach would be to attach a click listener to every <li>.
// That is slow and memory-heavy, and it FAILS for items added later
// (they have no listener). Event delegation solves all of this with a
// single listener on the parent.

// A tiny helper that appends a line to the on-page log panel.
function log(message) {
    const panel = document.getElementById('log');
    const line = document.createElement('div');
    line.textContent = message;
    panel.appendChild(line);
    // Keep the newest line in view.
    panel.scrollTop = panel.scrollHeight;
}

// ------------------------------------------------------------------
// 1. THE DELEGATED LISTENER
// ------------------------------------------------------------------
// We attach ONE click listener to the <ul>, not to each <li>. Because
// clicks BUBBLE up the tree, every item's click reaches the parent.
//
// The event object's e.target tells us which element was ACTUALLY
// clicked. We use e.target.closest('li') to find the nearest <li>
// ancestor. This is more robust than checking e.target.tagName === 'LI':
// it still works if the click lands on a child INSIDE the <li> (like a
// <span> or <b>), and it returns null when the click is on the list
// background, which we can ignore.
const list = document.getElementById('itemList');

list.addEventListener('click', (e) => {
    // Find the nearest <li> ancestor of whatever was clicked.
    const item = e.target.closest('li');
    if (item) {
        log(`You clicked: "${item.textContent}"`);
    } else {
        log('clicked the list background (not an item)');
    }
});

// ------------------------------------------------------------------
// 2. ADDING ITEMS DYNAMICALLY
// ------------------------------------------------------------------
// This is where delegation shines. We add a new <li> with NO listener
// of its own — yet it still responds to clicks, because the parent's
// listener catches the bubbled click. With per-item listeners, this
// new item would be dead on arrival.
document.getElementById('addItem').addEventListener('click', () => {
    const newItem = document.createElement('li');
    newItem.textContent = 'Item ' + (list.children.length + 1);
    list.appendChild(newItem);
    log(`Added "${newItem.textContent}" — it already responds to clicks.`);
});

// ------------------------------------------------------------------
// WHY NOT PER-ITEM LISTENERS?
// ------------------------------------------------------------------
// If we had done this instead:
//   document.querySelectorAll('li').forEach(li =>
//       li.addEventListener('click', handler));
// then:
//   1. We'd hold one listener per item in memory (wasteful for big lists).
//   2. Any item added AFTER that line runs would have NO listener, so
//      clicking it would do nothing.
// Delegation fixes both: one listener, and it works for future items.

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   Put ONE listener on a common parent instead of one per child.
//   Use e.target.closest('li') to find which item was actually clicked
//   (more robust than comparing e.target.tagName, and it handles clicks
//   on children inside the item).
//
// Benefits:
//   - Memory efficient (one listener, not N).
//   - Works automatically for dynamically added elements.
//   - Centralizes logic in one place.
