// ------------------------------------------------------------------
// Changing content: textContent vs innerHTML
// ------------------------------------------------------------------
// This file is loaded by content.html. Open that page in a browser and
// click the buttons to see the two ways of changing an element's
// content, plus the difference between live and static collections.
//
// The DOM is the browser's tree representation of your HTML. Once you
// have selected an element, the next step is usually changing what's
// inside it. There are two main ways to do that, and they behave very
// differently.

// ------------------------------------------------------------------
// 1. textContent — SAFE
// ------------------------------------------------------------------
// textContent sets RAW TEXT. Any HTML tags in the string are shown
// literally, not parsed into elements. This is SAFE: nothing can be
// injected or executed.
document.getElementById('setText').addEventListener('click', () => {
    const target = document.getElementById('contentTarget');
    target.textContent = 'This is <b>plain text</b>, not HTML.';
    console.log('textContent set. The <b> tags show as literal text.');
});

// ------------------------------------------------------------------
// 2. innerHTML — POWERFUL BUT RISKY
// ------------------------------------------------------------------
// innerHTML parses the string as HTML, so tags become real elements.
// This is powerful, but it is a SECURITY RISK: if the string ever comes
// from a user (a username, a comment), they could inject a <script> tag
// that runs in other people's browsers. This is called an XSS attack.
// Prefer textContent unless you truly need to build HTML.
document.getElementById('setHtml').addEventListener('click', () => {
    const target = document.getElementById('contentTarget');
    target.innerHTML = 'This is <b>real HTML</b> — the bold tag works.';
    console.log('innerHTML set. The <b> tag became a real bold element.');
});

// ------------------------------------------------------------------
// 3. LIVE vs STATIC COLLECTIONS
// ------------------------------------------------------------------
// This is a classic pitfall. We grab the .item elements with BOTH a live
// HTMLCollection and a static NodeList, then add a new item. The live
// collection updates automatically; the static snapshot does not.

// Grab the live collection and the static list up front.
const liveItems = document.getElementsByClassName('item');
const staticItems = document.querySelectorAll('.item');

document.getElementById('addItem').addEventListener('click', () => {
    const list = document.querySelector('.list');
    const newItem = document.createElement('li');
    newItem.className = 'item';
    newItem.textContent = 'Item ' + (list.children.length + 1);
    list.appendChild(newItem);

    console.log('Added a new <li>.');
    console.log('LIVE HTMLCollection length:', liveItems.length);   // updates
    console.log('STATIC NodeList length:', staticItems.length);     // stays the same
});

document.getElementById('removeItem').addEventListener('click', () => {
    const list = document.querySelector('.list');
    if (list.lastElementChild) {
        list.lastElementChild.remove();
        console.log('Removed the last <li>.');
        console.log('LIVE HTMLCollection length:', liveItems.length);
        console.log('STATIC NodeList length:', staticItems.length);
    }
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   textContent -> raw text, safe, nothing is parsed
//   innerHTML   -> parses HTML, powerful but an XSS risk with user data
//   HTMLCollection (getElement*) -> LIVE, updates with the DOM
//   NodeList (querySelectorAll)  -> STATIC, a snapshot that does not update
