// ------------------------------------------------------------------
// HTTP methods: the verbs of the web
// ------------------------------------------------------------------
// This file is loaded by methods.html. Open that page in a browser
// (with internet) and click each method button to send a real request
// to a test API and watch what comes back.
//
// Each HTTP METHOD (verb) defines a different intended action on a
// resource. The two properties worth remembering are:
//   SAFE: the request does not change the server's data (GET, HEAD).
//   IDEMPOTENT: sending the same request twice has the same effect as
//               once (GET, PUT, DELETE). POST is NOT idempotent —
//               sending it twice could create two resources.

// The base URL of the test API and the result panel element.
const BASE = 'https://jsonplaceholder.typicode.com/posts';
const result = document.getElementById('result');

// A helper that sends a request and prints the method, status, and body.
async function sendRequest(label, method, url, body) {
    result.textContent = `${label}\n\nSending ${method} ${url} ...`;
    // If the URL points at the bare collection (/posts) the response is
    // an ARRAY of many posts; if it points at one resource (/posts/1) it
    // is a single OBJECT. Both are valid — it depends on the endpoint.

    // fetch(url, options) sends the request. The options object lets us
    // set the method, a JSON body, and a Content-Type header so the
    // server knows we're sending JSON.
    const response = await fetch(url, {
        method: method,
        headers: { 'Content-type': 'application/json; charset=UTF-8' },
        body: body ? JSON.stringify(body) : undefined,
    });

    // The server's response is JSON, so we parse it into an object.
    const data = await response.json();

    // Print the result: the method, the HTTP status returned, and the body.
    result.textContent =
        `${label}  →  HTTP ${response.status}\n\n` +
        `${method} ${url}\n` +
        `body sent: ${body ? JSON.stringify(body) : '(none)'}\n\n` +
        `response body:\n${JSON.stringify(data, null, 2)}`;
}

// GET: read a resource. Safe and idempotent. We fetch a SINGLE post
// (/posts/1) so the response is one clean object. Curious? Fetch the
// bare BASE url (no /1) instead and you'll get ALL 100 posts as a
// collection array — that's the difference between a collection
// endpoint and a specific-resource endpoint.
document.getElementById('getBtn').addEventListener('click', () => {
    sendRequest('GET', 'GET', BASE + '/1');
});

// POST: create a resource. NOT safe, NOT idempotent (two POSTs = two
// resources). The server assigns a new id and returns HTTP 201 Created.
document.getElementById('postBtn').addEventListener('click', () => {
    sendRequest('POST', 'POST', BASE, {
        title: 'A brand new post',
        body: 'Created by a POST request.',
        userId: 1,
    });
});

// PUT: replace a resource entirely. Idempotent. Replaces post 1 with
// exactly the payload given.
document.getElementById('putBtn').addEventListener('click', () => {
    sendRequest('PUT', 'PUT', BASE + '/1', {
        id: 1,
        title: 'Replaced wholesale',
        body: 'PUT replaces the whole resource.',
        userId: 1,
    });
});

// PATCH: partially update a resource. NOT idempotent. Only the fields
// in the payload change; the rest of post 1 stays the same.
document.getElementById('patchBtn').addEventListener('click', () => {
    sendRequest('PATCH', 'PATCH', BASE + '/1', {
        title: 'Only this title changed',
    });
});

// DELETE: remove a resource. Idempotent. Returns an empty body and 200.
document.getElementById('deleteBtn').addEventListener('click', () => {
    sendRequest('DELETE', 'DELETE', BASE + '/1');
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   GET    : read          -> safe, idempotent
//   POST   : create        -> unsafe, NOT idempotent (2x POST = 2 items)
//   PUT    : replace whole -> unsafe, idempotent
//   PATCH  : partial update-> unsafe, NOT idempotent
//   DELETE : remove        -> unsafe, idempotent
//
// Watch the HTTP status codes: POST returns 201 Created, the rest of
// these mutations return 200.
