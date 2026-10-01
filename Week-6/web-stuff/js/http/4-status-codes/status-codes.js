// ------------------------------------------------------------------
// HTTP status codes: what did the server say?
// ------------------------------------------------------------------
// This file is loaded by status-codes.html. Open that page in a browser
// (with internet) and click the buttons to send real requests and see
// actual HTTP status codes come back from the PokeAPI.
//
// A STATUS CODE is a three-digit number in every response that tells the
// client how the request went. The first digit is its category:
//   1xx Informational, 2xx Success, 3xx Redirection, 4xx Client Error,
//   5xx Server Error. The cards in the page are the reference; the
//   buttons below demonstrate two of them live.

// The result panel where the live status is printed.
const result = document.getElementById('result');

// A helper that fetches a Pokémon by name and reports the status code
// that came back. We use the PokeAPI here (a real, free REST API).
async function fetchPokemon(name) {
    const url = `https://pokeapi.co/api/v2/pokemon/${name}`;
    result.textContent = `Fetching ${url} ...`;

    // fetch() sends the request. It ALWAYS returns a Response object no
    // matter what status comes back — even a 404 or 500. It only "fails"
    // (rejects) if the network itself breaks.
    const response = await fetch(url);

    // response.status is the raw three-digit status code.
    // response.ok is a convenience boolean: true for 200-299, false for
    // 4xx and 5xx. A 200 gives us 'OK'; a 404 gives us 'Not Found'.
    const meaning = response.status === 200
        ? 'OK'
        : response.status === 404
            ? 'Not Found'
            : '(other)';

    result.textContent =
        `Pokémon: "${name}"\n` +
        `URL: ${url}\n\n` +
        `HTTP status: ${response.status} ${meaning}\n` +
        `response.ok: ${response.ok}\n\n` +
        `Category: ${categoryOf(response.status)}`;
}

// A tiny helper that names the category from the first digit.
function categoryOf(status) {
    const firstDigit = String(status)[0];
    const categories = {
        '1': '1xx Informational',
        '2': '2xx Success',
        '3': '3xx Redirection',
        '4': '4xx Client Error',
        '5': '5xx Server Error',
    };
    return categories[firstDigit] || 'Unknown';
}

// Fetch a real Pokémon -> the server responds 200 OK.
document.getElementById('validBtn').addEventListener('click', () => {
    fetchPokemon('pikachu');
});

// Fetch a Pokémon that doesn't exist -> the server responds 404 Not Found.
document.getElementById('missingBtn').addEventListener('click', () => {
    fetchPokemon('notapokemon');
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   2xx Success   (200 OK, 201 Created)
//   3xx Redirect  (301 Moved, 304 Not Modified)
//   4xx Client    (400 Bad Request, 401 Unauthorized, 403 Forbidden, 404)
//   5xx Server    (500 Internal Error, 503 Unavailable)
//
// Key idea: fetch() succeeds (does not throw) even on 4xx/5xx — check
// response.ok or response.status yourself to handle errors.
