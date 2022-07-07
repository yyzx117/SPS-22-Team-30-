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

// SHOW THE SNAPSHOT.
takeSnapShot = function () {
    Webcam.snap(function (data_uri) {
        node = document.getElementById('snapShot')
        node.innerHTML = '<img src="' + data_uri + '" width="70px" height="50px" />';
    });
}
// SHOW THE SNAPSHOT File.
function PreviewImage() {
    var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

    oFReader.onload = function (oFREvent) {
        document.getElementById("uploadPreview").style.display = "inline-block" 
        document.getElementById("uploadPreview").src = oFREvent.target.result;
    };
    
};
function ConvertImage(){
    takeSnapShot.call(node);    
}
