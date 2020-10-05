"use strict";

let modal = document.getElementById('calendar');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

