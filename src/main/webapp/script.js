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
        document.getElementById("uploadPreview").style.display = "none";
        document.getElementById("snapShot").style.display = "inline-block";
        document.getElementById('snapShot').innerHTML = 
            '<img src="' + data_uri + '" width=40% height=40% />';
            
    });
    localStorage.setItem('snap', data_uri);

}

// SHOW THE Upload File.
function PreviewImage() {
    var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

    oFReader.onload = function (e) {
        /*document.getElementById("uploadPreview").style.display = "inline-block" 
        document.getElementById("uploadPreview").src = oFREvent.target.result;*/

        document.getElementById("snapShot").style.display = "none";
        document.getElementById("uploadPreview").style.display = "inline-block";
        document.getElementById("uploadPreview").innerHTML = '<img src="'+e.target.result+'" height=30% width=30%>';
    };
}

function ConvertImage(){
    
    convert = document.getElementById('convertPic');
    convert.innerHTML = '<img src="' + localStorage.getItem('snap'); + '" width="70px" height="50px" />';
    console.log(convertSnap);
}
