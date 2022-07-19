window.document.onkeydown = function (e) {
    if (!e) {
        e = event;
    }
    if (e.keyCode == 27) {
        lightbox_close();
    }
}

function lightbox_open_hey() {
    var lightBoxVideo = document.getElementById("heyVideo");
    window.scrollTo(0, 0);
    document.getElementById('hey_light').style.display = 'block';
    document.getElementById('hey_fade').style.display = 'block';
    lightBoxVideo.play();
}

function lightbox_close_hey() {
    var lightBoxVideo = document.getElementById("heyVideo");
    document.getElementById('hey_light').style.display = 'none';
    document.getElementById('hey_fade').style.display = 'none';
    lightBoxVideo.pause();
}

//JUMP TO PAGE 1
function jumpToPage1() {
    window.location.href="index.html";
}