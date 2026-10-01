// ------------------------------------------------------------------
// Request headers: sending metadata
// ------------------------------------------------------------------
// This file is loaded by request-headers.html. It lets you add your own
// request headers and then prove they get to the server: we fetch
// httpbin.org/anything with those headers, and that endpoint echoes back
// the exact headers it received. Whatever you added should come back to
// you in the response — that's the proof.
//
// REQUEST HEADERS are key:value pairs the client sends with a request to
// describe it to the server (Host, User-Agent, Accept, Authorization,
// Content-Type, and your own custom ones like X-API-Key).

// Collect the headers the user has added as an array of {name, value}.
const addedHeaders = [];
const nameInput = document.getElementById('nameInput');
const valueInput = document.getElementById('valueInput');
const added = document.getElementById('added');
const result = document.getElementById('result');

// Re-render the list of added headers (with a remove button each).
function renderAdded() {
    added.textContent = '';
    addedHeaders.forEach((hdr, i) => {
        const pill = document.createElement('span');
        pill.className = 'hdr';

        const key = document.createElement('b');
        key.textContent = hdr.name + ': ';
        const val = document.createTextNode(hdr.value);

        // A small × button that removes this header from the list.
        const remove = document.createElement('button');
        remove.textContent = '×';
        remove.setAttribute('aria-label', 'remove header');
        remove.addEventListener('click', () => {
            addedHeaders.splice(i, 1);
            renderAdded();
        });

        pill.appendChild(key);
        pill.appendChild(val);
        pill.appendChild(remove);
        added.appendChild(pill);
    });
}

// The Add button: read the two inputs and push a header onto the list.
document.getElementById('addBtn').addEventListener('click', () => {
    const name = nameInput.value.trim();
    const value = valueInput.value.trim();
    // A header needs both a name and a value to be meaningful.
    if (!name || !value) {
        alert('Enter both a header name and a value.');
        return;
    }
    addedHeaders.push({ name, value });
    renderAdded();
    nameInput.value = '';
    valueInput.value = '';
    nameInput.focus();
});

// Preset buttons: fill the name/value inputs with a ready-made header.
document.querySelectorAll('button[data-preset]').forEach(btn => {
    btn.addEventListener('click', () => {
        nameInput.value = btn.dataset.preset;
        valueInput.value = btn.dataset.value;
    });
});

// ------------------------------------------------------------------
// The Send button: make the real fetch with our custom headers
// ------------------------------------------------------------------
document.getElementById('sendBtn').addEventListener('click', async () => {
    if (addedHeaders.length === 0) {
        result.textContent = 'Add at least one header before sending.';
        return;
    }

    // Build a headers object from the collected list. This is the `headers`
    // key of the fetch options object — the piece that sends the metadata.
    const headers = {};
    addedHeaders.forEach(h => { headers[h.name] = h.value; });

    result.textContent = 'Sending request to https://httpbin.org/anything ...';

    try {
        // fetch the URL with our custom request headers attached. httpbin
        // will echo back in the JSON body every header it actually got.
        const response = await fetch('https://httpbin.org/anything', {
            headers: headers,
        });
        const data = await response.json();
        const echoed = data.headers; // { Header-Name: value, ... }

        // Print which of OUR headers made the round trip.
        result.innerHTML = 'HTTP ' + response.status + ' — the server saw these request headers:\n\n';
        addedHeaders.forEach(h => {
            // Did this header come back in the echo?
            const back = Object.keys(echoed).some(k =>
                k.toLowerCase() === h.name.toLowerCase());
            result.innerHTML +=
                `<span class="mine">${back ? '✓' : '✗'} ${h.name}: ${h.value}</span>\n`;
        });

        // Then show every header the server received, so you can compare.
        result.innerHTML += '\nAll headers the server received:\n';
        for (const [name, value] of Object.entries(echoed)) {
            const isMine = addedHeaders.some(h =>
                h.name.toLowerCase() === name.toLowerCase());
            // Highlight our additions; dim the browser-managed ones.
            result.innerHTML +=
                `<span class="${isMine ? 'mine' : 'default'}">  ${name}: ${value}</span>\n`;
        }

        // Call out the browser-managed headers you can't override.
        result.innerHTML += '\nHost, User-Agent are set by the browser —\nyour request did not (and cannot) replace them.';
    } catch (err) {
        result.innerHTML = `Request failed: ${err.message}`;
    }
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   Request headers are key:value metadata sent WITH a request:
//     Host, User-Agent (set automatically by the browser)
//     Accept, Authorization, Content-Type, + your own custom ones
//
//   Send them via the fetch options object:
//     fetch(url, { headers: { 'X-API-Key': '...' } })
//
//   To confirm they arrive, echo them back from a server (as httpbin
//   does) and compare what you sent with what came back.
//
//   Forbidden headers (Host, User-Agent, Cookie, Content-Length, Referer)
//   are controlled by the browser and silently ignore your scripts.
