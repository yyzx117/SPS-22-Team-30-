
function sendRandomLetters() {
    const letters =
        ['A', 'B', 'C', 'D', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    // Pick a random letter.
    const letter = letters[Math.floor(Math.random() * letters.length)];

    // Add it to the page.
    const letterContainer = document.getElementById('letter-container');
    letterContainer.innerText = letter;
    console.log(letter);
}