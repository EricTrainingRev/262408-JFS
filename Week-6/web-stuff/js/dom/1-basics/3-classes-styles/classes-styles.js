// ------------------------------------------------------------------
// Classes & styles: styling elements from JavaScript
// ------------------------------------------------------------------
// This file is loaded by classes-styles.html. Open that page in a
// browser and click the buttons to see the two ways of changing how an
// element looks from JavaScript.
//
// There are two approaches:
//   1. Toggle CSS CLASSES with classList (the preferred way).
//   2. Set INLINE STYLES directly through element.style.
// The class-based approach is usually better because it keeps your
// styling in CSS, where it belongs, and is easy to toggle on and off.

// ------------------------------------------------------------------
// 1. classList — the preferred way to manage classes
// ------------------------------------------------------------------
// classList has add, remove, and toggle methods. This is much cleaner
// than overwriting the whole className string, because it only touches
// the one class you care about and leaves the others alone.

// toggle() adds the class if it's missing, and removes it if it's
// present. Perfect for a light switch.
document.getElementById('toggleClass').addEventListener('click', () => {
    const card = document.getElementById('styleCard');
    card.classList.toggle('highlight');
    console.log('Toggled .highlight. Card has it now?',
        card.classList.contains('highlight'));
});

// contains() checks whether an element currently has a class. It returns
// true or false, which is handy for reading state.

// ------------------------------------------------------------------
// 2. Inline styles — element.style
// ------------------------------------------------------------------
// You can set a style directly with element.style.propertyName. Note the
// property names are camelCase (backgroundColor, not background-color),
// because a hyphen would be read as a minus sign in JavaScript.

// To toggle an inline style, we check the current value and either set
// it or clear it back to the default. The browser reports the color
// "#e8f0fe" as "rgb(232, 240, 254)", so we compare against that.
document.getElementById('changeColor').addEventListener('click', () => {
    const card = document.getElementById('styleCard');
    if (card.style.backgroundColor === 'rgb(232, 240, 254)') {
        // Already colored — clear it back to the default (empty string).
        card.style.backgroundColor = '';
        console.log('Cleared the background color back to default.');
    } else {
        card.style.backgroundColor = '#e8f0fe';
        console.log('Set card.style.backgroundColor = "#e8f0fe".');
    }
});

// ------------------------------------------------------------------
// Quick summary
// ------------------------------------------------------------------
//   classList.add / remove / toggle -> manage classes cleanly
//   classList.contains              -> check if a class is present
//   element.style.propertyName       -> set an inline style (camelCase)
//
// Prefer toggling classes over setting inline styles: it keeps your
// visual rules in CSS and makes on/off states trivial to manage.
