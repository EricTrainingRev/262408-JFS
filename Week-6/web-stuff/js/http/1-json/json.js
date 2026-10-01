// ------------------------------------------------------------------
// JSON: the language of APIs
// ------------------------------------------------------------------
// This file is loaded by json.html. It demonstrates the two operations
// that every web API relies on:
//
//   DESERIALIZE (JSON.parse):      JSON string  ->  JavaScript object
//     This is what response.json() does for you when a fetch comes back.
//   SERIALIZE   (JSON.stringify):  JavaScript object  ->  JSON string
//     This is what you use to package a fetch() request body.
//
// JSON is just text. On the wire a server sends your data as a string;
// you convert it to an object to work with it, and convert it back to a
// string when you send it out again.

// The two result panels and the JSON textarea.
const input = document.getElementById('input');
const parseResult = document.getElementById('parseResult');
const strResult = document.getElementById('strResult');

// ------------------------------------------------------------------
// 1. DESERIALIZATION: JSON.parse (string -> object)
// ------------------------------------------------------------------
document.getElementById('parseBtn').addEventListener('click', () => {
    // JSON.parse is wrapped in try/catch because it THROWS an error when
    // the text is not valid JSON (missing a comma, a stray quote, etc).
    // Without the try/catch the whole page would stop when that happens.
    try {
        // Turn the text in the textarea into a real object.
        const parsed = JSON.parse(input.value);

        // We now have a genuine object: we can read its properties and
        // check its type. typeof says 'object', and JSON.stringify with a
        // 2-space indent re-renders it prettily for display.
        parseResult.textContent = '✓ Valid JSON — parsed successfully!\n\n';
        parseResult.innerHTML +=
            `<span class="err">typeof result: ${typeof parsed}</span>\n\n` +
            'result.name   = ' + parsed.name + '\n' +
            'result.type   = ' + parsed.type + '\n' +
            'result.moves[0] = ' + parsed.moves[0] + '\n\n' +
            'Pretty-printed object:\n' + JSON.stringify(parsed, null, 2);
    } catch (err) {
        // Invalid JSON: show the helpful message the browser gives us.
        parseResult.innerHTML =
            '✗ That is NOT valid JSON.\n\n' +
            `<span class="err">${err.message}</span>\n\n` +
            'Fix the text (for example: are all the quotes and commas\n' +
            'in place?) and click Parse again.';
    }
});

// ------------------------------------------------------------------
// 2. SERIALIZATION: JSON.stringify (object -> string)
// ------------------------------------------------------------------
document.getElementById('stringifyBtn').addEventListener('click', () => {
    // This is a real JavaScript object living in our program's memory.
    const pokemon = {
        name: 'Squirtle',
        type: 'water',
        pokedex: 7,
    };

    // JSON.stringify converts it into a JSON string. With no arguments it
    // produces the compact one-line version that actually goes over the
    // wire. The ', null, 2' arguments add pretty indentation for humans.
    const oneLine = JSON.stringify(pokemon);
    const pretty = JSON.stringify(pokemon, null, 2);

    strResult.textContent =
        `Original object (in memory):\n` +
        JSON.stringify(pokemon, null, 2) + '\n\n' +
        `JSON.stringify(pokemon) — the compact version sent to a server:\n` +
        oneLine + '\n\n' +
        `JSON.stringify(pokemon, null, 2) — same string, human-readable:\n` +
        pretty + '\n\n' +
        `typeof serialized result: ${typeof oneLine}`;

    // The key idea: both outputs are STRINGS (typeof === 'string'), even
    // though the source was an object. That is exactly the text you put
    // into fetch(url, { body: oneLine }).
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   JSON.parse(jsonString)       -> object    (deserialize / read)
//       Throws on invalid JSON, so wrap it in try/catch.
//       response.json() in fetch calls this for you.
//
//   JSON.stringify(obj)          -> string    (serialize / send)
//       The optional ', null, 2' adds pretty indentation.
//       Use it to read the body of a POST/PUT/PATCH request.
//
//   The two are exact opposites: parse turns text into an object,
//   stringify turns an object back into text.
