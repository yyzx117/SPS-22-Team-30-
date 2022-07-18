
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
            '<img src="' + data_uri + '" id="take" width=40% height=40% />';
        localStorage.setItem("snap_upload", data_uri);
        localStorage.setItem("ptype", "snap");
    });

}

// SHOW THE Upload File.
function PreviewImage() {
    var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

    oFReader.onload = function (e) {
        document.getElementById("snapShot").style.display = "none";
        document.getElementById("uploadPreview").style.display = "inline-block";
        document.getElementById("uploadPreview").innerHTML = '<img src="'+e.target.result+'" id="filePic" height=30% width=30%>';
        localStorage.setItem("file_upload", e.target.result);
        localStorage.setItem("ptype", "upload");
    };
    
}

//JUMP TO PAGE 1
function jumpToPage1() {
    window.location.href="index.html";
}

//JUMP TO PAGE 2
function jumpToPage2() {
    window.location.href="page_2.html";
}

//JUMP TO PAGE 2
function jumpTolearnASL() {
    window.location.href="learnASL.html";
}


function download() {
    var pdf = new jsPDF();
    var src = $('#content').html();
    
    specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector
        '#editor': function (element, renderer) {
            // true = "handled elsewhere, bypass text extraction"
            return true
        }
    };
    margins = {
        top: 80,
        bottom: 60,
        left: 40,
        width: 522
    };
    // all coords and widths are in jsPDF instance's declared units
    // 'inches' in this case
    pdf.fromHTML(
        src, // HTML string or DOM elem ref.
        margins.left, // x coord
        margins.top, { // y coord
            'width': margins.width, // max width of content on PDF
            'elementHandlers': specialElementHandlers
        },

        function (dispose) {
            // dispose: object with X, Y of the last line add to the PDF 
            //          this allow the insertion of new lines after html
            pdf.save('ASL.pdf');
        }, margins
    );        
};


//Store Upload File
function ConvertImage(){
    
    if(localStorage.getItem('ptype') == "upload"){
        imgData = localStorage.getItem('file_upload');
        fileImg = document.getElementById('filepic');
        fileImg.src = imgData;
        console.log('Convert UploadFile')
    }
    if(localStorage.getItem('ptype') == "snap"){
        imgData = localStorage.getItem('snap_upload');
        fileImg = document.getElementById('filepic');
        fileImg.src = imgData;
        console.log('Convert Snapshop')
    }
}
async function runModel() {
    const modelpic = document.getElementById('filepic');
    const responseFromServer = await fetch('/model');
    const textFromResponse = await responseFromServer.text();

    const lettertext = document.getElementById('letter-container');
    lettertext.innerText = textFromResponse;
}

// convert = document.getElementById('convertPic');
// convert.innerHTML = '<img src="' + localStorage.getItem('snap'); + '" width="70px" height="50px" />';
// console.log(convertSnap);