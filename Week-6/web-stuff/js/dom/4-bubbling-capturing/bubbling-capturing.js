// ------------------------------------------------------------------
// Bubbling & capturing: how events travel the tree
// ------------------------------------------------------------------
// This file is loaded by bubbling-capturing.html. Open that page in a
// browser and click the nested boxes — the log panel shows the exact
// order events fire in.
//
// When an event fires on an element, it doesn't just trigger there. It
// travels through the DOM tree in two phases:
//   CAPTURING (trickle down): window -> ... -> target
//   BUBBLING  (bubble up):    target -> ... -> window
// By default, addEventListener listens during the BUBBLING phase. To
// listen during CAPTURING, pass { capture: true } as the third argument.

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
// 1. BUBBLING & CAPTURING
// ------------------------------------------------------------------
// The three nested boxes. We attach BOTH a capturing and a bubbling
// listener to each, so clicking the innermost box shows the full
// journey: capturing fires outer -> middle -> inner, then bubbling
// fires inner -> middle -> outer.
const boxes = ['outer', 'middle', 'inner'];

for (const id of boxes) {
    const box = document.getElementById(id);

    // CAPTURING listener (third argument { capture: true }).
    box.addEventListener('click', () => {
        log(`CAPTURE: ${id}`);
    }, { capture: true });

    // BUBBLING listener (default).
    box.addEventListener('click', () => {
        log(`bubble: ${id}`);
    });
}

// ------------------------------------------------------------------
// 2. STOPPING THE FLOW
// ------------------------------------------------------------------
// Sometimes you want to stop an event from traveling further.

// stopPropagation() stops the event from bubbling (or capturing) any
// further up the tree. Here we stop the click at the button, so the
// document-level listener below never fires for this button.
document.getElementById('stopBtn').addEventListener('click', (e) => {
    e.stopPropagation();
    log('stopPropagation: click stopped at the button');
});

// A document-level click listener. It fires for most clicks (because
// they bubble up to the document) but NOT for the stop button, whose
// click was stopped.
document.addEventListener('click', () => {
    log('document: a click bubbled all the way up here');
});

// preventDefault() stops the BROWSER'S DEFAULT behavior, not the event
// flow. Here it stops a link from navigating to its href.
document.getElementById('preventLink').addEventListener('click', (e) => {
    e.preventDefault();
    log('preventDefault: link navigation blocked');
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   addEventListener(event, fn)          -> listen (bubbling by default)
//   addEventListener(event, fn, {capture:true}) -> listen during capturing
//   e.stopPropagation()                  -> stop the event travelling up/down
//   e.preventDefault()                   -> stop the browser's default action
//
// Bubbling is why EVENT DELEGATION works: put one listener on a parent
// and it catches events from all its children as they bubble up.
