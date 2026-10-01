// ------------------------------------------------------------------
// Promises & async/await: handling the wait
// ------------------------------------------------------------------
// This file is loaded by promises-async-await.html. Networking is
// ASYNCHRONOUS: it takes time, and JavaScript must not freeze the page
// while it waits. A Promise is a placeholder for a value that hasn't
// arrived yet. It starts "pending", then settles either "fulfilled"
// (got a value) or "rejected" (hit an error). async/await is cleaner
// syntax for the exact same promises.
//
// Every step is appended to an on-page log so the ordering is visible.

// The log panel and a tiny helper that appends a line, colored by type.
const log = document.getElementById('log');
function write(text, cls = '') {
    const div = document.createElement('div');
    if (cls) div.className = cls;
    div.textContent = text;
    log.appendChild(div);
    // Keep the newest line in view.
    log.scrollTop = log.scrollHeight;
}

// ------------------------------------------------------------------
// 1a. A fulfilled promise with an artificial delay
// ------------------------------------------------------------------
document.getElementById('resolveBtn').addEventListener('click', () => {
    write('--- resolve demo ---', 'io');

    // A Promise takes a function (the "executor") with resolve and reject.
    // Calling resolve() marks it fulfilled; calling reject() marks it
    // rejected. Here we simulate real network latency with a setTimeout.
    const myPromise = new Promise((resolve, reject) => {
        write('Created the promise (state: pending, waiting...)', 'pending');
        setTimeout(() => {
            resolve('Data received!');
        }, 800); // pretend an 800ms network round-trip
    });

    // .then() runs the moment the promise fulfills; .catch() would run on
    // rejection. This code reads "in order" but actually fires later.
    myPromise
        .then(data => {
            write(`Promise fulfilled — .then() got: "${data}"`, 'ok');
        })
        .catch(err => {
            write(`Promise rejected — .catch() got: ${err}`, 'err');
        });

    write('Scheduling done. The .then() above fires AFTER the delay.', 'pending');
});

// ------------------------------------------------------------------
// 1b. A rejected promise, handled by .catch()
// ------------------------------------------------------------------
document.getElementById('rejectBtn').addEventListener('click', () => {
    write('--- reject demo ---', 'io');

    const failing = new Promise((resolve, reject) => {
        write('Created the promise (state: pending, waiting...)', 'pending');
        // Simulate a request that fails (e.g. the network is down).
        setTimeout(() => {
            reject(new Error('Network request failed'));
        }, 600);
    });

    // The chain continues smoothly past the failure — .catch() turns the
    // error into an opportunity to recover instead of crashing the page.
    failing
        .then(data => {
            write(`This never runs: got ${data}`, 'ok');
        })
        .catch(err => {
            write(`Promise rejected — .catch() handled it: ${err.message}`, 'err');
        });

    write('Scheduling done. The .catch() above fires AFTER the delay.', 'pending');
});

// ------------------------------------------------------------------
// 2. async/await — the same idea, cleaner syntax
// ------------------------------------------------------------------
document.getElementById('awaitBtn').addEventListener('click', async () => {
    write('--- async/await demo ---', 'io');

    // Marking the function `async` means it always returns a Promise and
    // inside it we can use `await`. `await` PAUSES the function until a
    // promise settles — so the lines below read like normal synchronous
    // code, even though they happen over time.
    function wait(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    // await is wrapped in try/catch because a rejected promise behaves
    // like a thrown error. Without the try/catch you'd get an
    // "Uncaught (in promise)" message in the console.
    try {
        write('About to await a 700ms delay...', 'pending');
        await wait(700);
        write('awaited delay finished — continuing as if nothing waited.', 'ok');

        write('About to await the value...', 'pending');
        const value = await Promise.resolve('Hello from await');
        write(`awaited value: "${value}"`, 'ok');
    } catch (err) {
        write(`Caught an error: ${err.message}`, 'err');
    }
});

// ------------------------------------------------------------------
// 3a. Real HTTP request written with a .then() chain
// ------------------------------------------------------------------
async function fetchWithThen() {
    write('--- fetch with .then() ---', 'io');

    // fetch returns a Promise. We chain two .then() calls: the first
    // parses the body to JSON, the second logs it. A single .catch() at
    // the end catches errors from either step.
    fetch('https://jsonplaceholder.typicode.com/posts/1')
        .then(response => response.json())      // parse body
        .then(post => {
            write(`Fetched with .then(): post #${post.id} = "${post.title}"`, 'ok');
        })
        .catch(err => {
            write(`Fetch .then() chain failed: ${err.message}`, 'err');
        });
}
document.getElementById('fetchThenBtn').addEventListener('click', fetchWithThen);

// ------------------------------------------------------------------
// 3b. The SAME request, written with async/await
// ------------------------------------------------------------------
async function fetchWithAwait() {
    write('--- fetch with await ---', 'io');

    // The same two steps as above, but with await they read like a
    // straight line. `await fetch(...)` gives us the Response; `await
    // response.json()` parses it. Everything is in one try/catch.
    try {
        write('await fetch(...) → sending request...', 'pending');

        // The Response object. Note: fetch resolves even on HTTP errors
        // like 404 — checking response.ok is how you tell success apart.
        const response = await fetch('https://jsonplaceholder.typicode.com/posts/1');

        // Check the request actually succeeded before trusting the body.
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // Parse the JSON text into an object.
        const post = await response.json();
        write(`Fetched with await: post #${post.id} by user ${post.userId}`, 'ok');
    } catch (err) {
        write(`Fetch with await failed: ${err.message}`, 'err');
    }
}
document.getElementById('fetchAwaitBtn').addEventListener('click', fetchWithAwait);

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   Promise states: pending -> fulfilled  (resolve)  OR  rejected (reject)
//   .then(value => ...)   runs on fulfillment
//   .catch(err => ...)    runs on rejection (handles the error)
//
//   `async function` always returns a Promise.
//   `await somePromise` pauses the function until the promise settles.
//   Always wrap await calls in try/catch — a rejected promise acts like
//   a thrown error and would otherwise be "Uncaught (in promise)".
//
//   With fetch, download the Response first (await fetch), check
//   response.ok, then parse (await response.json()) — all inside try/catch.
