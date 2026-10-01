// ------------------------------------------------------------------
// Events: common events & the event object
// ------------------------------------------------------------------
// This file is loaded by events.html. Open that page in a browser and
// interact with it — everything you do is logged to the panel on the
// page, so you can watch events fire in real time.
//
// An EVENT is a signal that something happened (a click, a keypress, a
// form submit). An EVENT LISTENER is a function that "listens" for that
// signal and runs in response. This example covers:
//   1. Common events (click, dblclick, mouseenter, input, keydown, submit)
//   2. The event object (e.target, e.type, e.key)

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
// 1. COMMON EVENTS
// ------------------------------------------------------------------
// The modern way to attach a listener is addEventListener(event, fn).
// The function runs every time the event fires.

// CLICK: fires when an element is clicked.
document.getElementById('clickBtn').addEventListener('click', () => {
    log('click fired on the button');
});

// DBLCLICK: fires on a double-click.
document.getElementById('clickBtn').addEventListener('dblclick', () => {
    log('dblclick fired on the button');
});

// MOUSEENTER / MOUSELEAVE: fire when the pointer enters / leaves.
document.getElementById('clickBtn').addEventListener('mouseenter', () => {
    log('mouseenter: pointer entered the button');
});
document.getElementById('clickBtn').addEventListener('mouseleave', () => {
    log('mouseleave: pointer left the button');
});

// INPUT: fires on every keystroke in a text field. The event object
// (e) carries details — here we read the current value of the field.
const nameInput = document.getElementById('nameInput');
nameInput.addEventListener('input', (e) => {
    log(`input: field is now "${e.target.value}"`);
});

// KEYDOWN: fires when a key is pressed. e.key tells us which key.
nameInput.addEventListener('keydown', (e) => {
    log(`keydown: pressed "${e.key}"`);
});

// SUBMIT: fires when a form is submitted. We call preventDefault() to
// stop the browser from reloading the page (the default behavior).
document.getElementById('demoForm').addEventListener('submit', (e) => {
    e.preventDefault(); // stop the page reload
    log('submit: form submitted (page reload prevented)');
});

// ------------------------------------------------------------------
// 2. THE EVENT OBJECT
// ------------------------------------------------------------------
// When an event fires, the browser passes an EVENT OBJECT to the
// listener. It carries useful metadata:
//   e.target        -> the element that actually triggered the event
//   e.currentTarget -> the element the listener is attached to
//   e.type          -> the name of the event (e.g. "click")
//   e.key           -> (keyboard events) the key that was pressed
// We used e.target and e.key above. Here's e.type in action:
document.getElementById('clickBtn').addEventListener('click', (e) => {
    log(`event object: type="${e.type}", target=${e.target.tagName}`);
});

// ------------------------------------------------------------------
// 3. SINGLE vs DOUBLE CLICK
// ------------------------------------------------------------------
// A double-click always fires two click events FIRST, then a dblclick.
// So you can't tell a single click from a double click by listening to
// click and dblclick separately — the click handler runs twice before
// dblclick ever fires.
//
// The fix is a TIMER (debounce). On each click we clear any pending
// timer and start a new one. If a second click arrives within ~250ms,
// it cancels the first timer and we treat it as a double click. Only
// if no second click comes does the timer fire and report a single
// click. The trade-off: a single click feels slightly delayed.
let clickTimer = null;

document.getElementById('clickTimerBtn').addEventListener('click', () => {
    // Cancel any pending "single click" from a previous click.
    clearTimeout(clickTimer);
    // If a second click comes within 250ms, this timer is cancelled
    // and the "single click" branch below never runs.
    clickTimer = setTimeout(() => {
        log('single click (after 250ms delay)');
    }, 250);
});

document.getElementById('clickTimerBtn').addEventListener('dblclick', () => {
    // Cancel the pending single click — this was a double click.
    clearTimeout(clickTimer);
    log('double click');
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   addEventListener(event, fn) -> attach a listener (runs on every fire)
//   Common events: click, dblclick, mouseenter/mouseleave, input,
//                  keydown, submit, change, focus, blur
//   e.target / e.currentTarget / e.type / e.key -> event object metadata
//   e.preventDefault()          -> stop the browser's default action
//
// Single vs double click: a double-click fires two clicks first, so
// disambiguate with a timer (debounce) — wait ~250ms before treating a
// click as a single click, and cancel it if a second click arrives.
