// ------------------------------------------------------------------
// HTTP headers: metadata for requests & responses
// ------------------------------------------------------------------
// This file is loaded by headers.html. Open that page in a browser
// (with internet) and click the button to fetch a real Pokémon and
// print every response header the PokeAPI sends back.
//
// A HEADER is a key-value pair (like "Content-Type: application/json")
// carried with a request or a response. Request headers tell the server
// about the client and its request; response headers describe the
// response. The tables on the page list the common ones; this script
// shows you the REAL ones coming from a real server.

const result = document.getElementById('result');

// The fetch button: get a Pokémon, then list its response headers.
document.getElementById('fetchBtn').addEventListener('click', async () => {
    result.textContent = 'Fetching https://pokeapi.co/api/v2/pokemon/pikachu ...';

    const response = await fetch('https://pokeapi.co/api/v2/pokemon/pikachu');

    // We don't need the body here — just the headers. Clear the panel
    // and rebuild it with one line per header.
    result.innerHTML = '';

    // A status line showing which response this is.
    const statusLine = document.createElement('div');
    statusLine.textContent = `HTTP ${response.status} — response headers:`;
    statusLine.style.marginBottom = '6px';
    statusLine.style.fontWeight = '700';
    result.appendChild(statusLine);

    // response.headers is a Headers object. forEach visits every
    // key/value pair it contains — the REAL headers the server sent.
    response.headers.forEach((value, name) => {
        const line = document.createElement('div');
        line.className = 'hdr';

        const key = document.createElement('b');
        key.textContent = name + ': ';
        const val = document.createTextNode(value);

        line.appendChild(key);
        line.appendChild(val);
        result.appendChild(line);
    });

    // Pull out one specific header on its own so the point lands.
    const contentType = response.headers.get('Content-Type');
    const note = document.createElement('div');
    note.style.marginTop = '8px';
    note.style.color = '#8ab4f8';
    note.textContent =
        `Note: response.headers.get('Content-Type') = "${contentType}"`;
    result.appendChild(note);
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   Headers are key:value pairs of metadata on requests and responses.
//   Request  headers tell the server about the client (Host, User-Agent,
//             Accept, Authorization, Content-Type).
//   Response headers describe the response (Server, Content-Type,
//             Content-Length, Set-Cookie, Cache-Control).
//
//   In JS: response.headers is a Headers object.
//     headers.forEach((value, name) => ...)  -> visit every header
//     headers.get('Content-Type')            -> read one header
