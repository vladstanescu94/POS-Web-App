let hamburger = document.querySelector('.hamburger');
let hamburgerLines = document.querySelectorAll('.hamburger__line');

hamburger.addEventListener('click', function () {
    for (let i = 0; i < hamburgerLines.length; i++) {
       hamburgerLines[i].classList.toggle('change'); 
    }
});