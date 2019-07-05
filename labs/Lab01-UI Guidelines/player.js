// Player using HTML5's Video API
// Author liqisz@cn.ibm.com
// Wait for the DOM to be loaded before initialising the vedio player
document.addEventListener("DOMContentLoaded", function() { initialiseMediaPlayer(); }, false);

// Variables to store handles to various required elements
var videoPlayer;
var playPauseBtn;
var playPauseIcon;
var muteBtn;
var muteIcon;
var progressBar;
var like;
var unlike;
var volume;
var s_like_key;
var s_unlike_key;
var btnLike;
var btnUnlike;

function initialiseMediaPlayer() {
    // Get a handle to the player
    videoPlayer = document.getElementById('media-video');

    // Get handles to each of the buttons and required elements
    playPauseBtn = document.getElementById('play-pause-button');
    playPauseIcon = document.getElementById('play-pause-icon');
    muteBtn = document.getElementById('mute-button');
    muteIcon = document.getElementById('mute-icon');
    progressBar = document.getElementById('progress-bar');
    like = document.getElementById('video-like');
    unlike = document.getElementById('video-unlike');
    volume = document.getElementById('video-volume');
    btnLike = document.getElementById('like-button');
    btnUnlike = document.getElementById('unlike-button');

    // Hide the browser's default controls
    videoPlayer.controls = false;

    // Add a listener for the timeupdate event so we can update the progress bar
    videoPlayer.addEventListener('timeupdate', updateProgressBar, false);

    // Add a listener for the play and pause events so the buttons state can be updated
    videoPlayer.addEventListener('play', function() {
        // Change the button to be a pause button
        changeButtonIcon(playPauseIcon, 'glyphicon-play', 'glyphicon-pause');
    }, false);
    videoPlayer.addEventListener('pause', function() {
        // Change the button to be a play button
        changeButtonIcon(playPauseIcon, 'glyphicon-pause', 'glyphicon-play');
    }, false);

    // need to work on this one more...how to know it's muted?
    videoPlayer.addEventListener('volumechange', function(e) {
        // Update the button to be mute/unmute
        if (videoPlayer.muted) changeButtonIcon(muteIcon, 'glyphicon-headphones', 'glyphicon-volume-off');
        else changeButtonIcon(muteIcon, 'glyphicon-volume-off', 'glyphicon-headphones');
    }, false);
    videoPlayer.addEventListener('ended', function() { this.pause(); }, false);

    // initial web storage
    initialWebStorage();
    volume.innerText = videoPlayer.volume * 10;
}

function initialWebStorage() {
    var srcString = videoPlayer.src;
    if (srcString.replace(/(^s*)|(s*$)/g, "").length == 0) {
        btnLike.disabled = true;
        btnUnlike.disabled = true;
    } else {
        btnLike.disabled = false;
        btnUnlike.disabled = false;
        s_like_key = videoPlayer.src + '_like';
        s_unlike_key = videoPlayer.src + '_unlike'
            // initial web storage
        if ((localStorage.getItem(s_like_key) == undefined) && localStorage.getItem(s_unlike_key) == undefined) {
            localStorage.setItem(s_like_key, 0);
            localStorage.setItem(s_unlike_key, 0)
            like.innerText = localStorage.getItem(s_like_key);
            unlike.innerText = localStorage.getItem(s_unlike_key);
        } else {
            like.innerText = localStorage.getItem(s_like_key);
            unlike.innerText = localStorage.getItem(s_unlike_key);
        }
    }
}

function togglePlayPause() {
    // If the mediaPlayer is currently paused or has ended
    if (videoPlayer.paused || videoPlayer.ended) {
        // Change the button to be a pause button
        changeButtonIcon(playPauseIcon, 'glyphicon-play', 'glyphicon-pause');
        // Play the media
        videoPlayer.play();
    }
    // Otherwise it must currently be playing
    else {
        // Change the button to be a play button
        changeButtonIcon(playPauseIcon, 'glyphicon-pause', 'glyphicon-play');
        // Pause the media
        videoPlayer.pause();
    }
}

// Stop the current media from playing, and return it to the start position
function stopPlayer() {
    videoPlayer.pause();
    videoPlayer.currentTime = 0;
}

// Changes the volume on the media player
function changeVolume(direction) {
    if (direction === '+') videoPlayer.volume += videoPlayer.volume == 1 ? 0 : 0.1;
    else videoPlayer.volume -= (videoPlayer.volume == 0 ? 0 : 0.1);
    videoPlayer.volume = parseFloat(videoPlayer.volume).toFixed(1);
    volume.innerText = videoPlayer.volume * 10;

}

// Toggles the media player's mute and unmute status
function toggleMute() {
    if (videoPlayer.muted) {
        // Change the cutton to be a mute button
        changeButtonIcon(muteIcon, 'glyphicon-headphones', 'glyphicon-volume-off');
        // Unmute the media player
        videoPlayer.muted = false;
    } else {
        // Change the button to be an unmute button
        changeButtonIcon(muteIcon, 'glyphicon-volume-off', 'glyphicon-headphones');
        // Mute the media player
        videoPlayer.muted = true;
    }
}

// Replays the media currently loaded in the player
function replayMedia() {
    resetPlayer();
    videoPlayer.play();
}

// Update the progress bar
function updateProgressBar() {
    // Work out how much of the media has played via the duration and currentTime parameters
    var percentage = Math.floor((100 / videoPlayer.duration) * videoPlayer.currentTime);
    // Update the progress bar's css width
    progressBar.setAttribute("style", "width: " + percentage + "%;");
    progressBar.setAttribute("aria-valuenow", percentage);
    // Update the progress bar's text (for browsers that don't support the progress element)
    progressBar.innerHTML = percentage + '% played';
}

// Updates a button's title, innerHTML and CSS class to a certain value
function changeButtonIcon(btnIcon, oldIcon, newIcon) {
    btnIcon.classList.remove(oldIcon);
    btnIcon.classList.add(newIcon);
}

// Loads a video item into the vedio player
function loadVideo() {
    for (var i = 0; i < arguments.length; i++) {
        var file = arguments[i].split('.');
        var ext = file[file.length - 1];
        // Check if this media can be played
        if (canPlayVideo(ext)) {
            // Reset the player, change the source file and load it
            resetPlayer();
            videoPlayer.src = arguments[i];
            videoPlayer.load();
            changeButtonIcon(playPauseIcon, 'glyphicon-pause', 'glyphicon-play');
            initialWebStorage();
            break;
        }
    }
    videoPlayer.currentTime = 0;
}

// Checks if the browser can play this particular type of file or not
function canPlayVideo(ext) {
    var ableToPlay = videoPlayer.canPlayType('video/' + ext);
    return (ableToPlay == '') ? false : true;
}

// Resets the media player
function resetPlayer() {
    // Reset the progress bar to 0
    progressBar.value = 0;
    // Move the media back to the start
    videoPlayer.currentTime = 0;
    // Ensure that the play pause button is set as 'play'
    changeButtonIcon(playPauseIcon, 'glyphicon-play', 'glyphicon-pause');
}

// Click the like currently video
function likeVideo() {
    var c_number = localStorage.getItem(s_like_key);
    localStorage.setItem(s_like_key, parseInt(c_number) + 1);
    like.innerText = localStorage.getItem(s_like_key);
}

// Click the unlike currently video
function unlikeVideo() {
    var c_number = localStorage.getItem(s_unlike_key);
    localStorage.setItem(s_unlike_key, parseInt(c_number) + 1)
    unlike.innerText = localStorage.getItem(s_unlike_key);
}