    // JavaScript to toggle navigation visibility
// JavaScript to toggle navigation visibility
// JavaScript to toggle navigation visibility
// JavaScript to toggle navigation visibility
document.querySelector('.hamburger').addEventListener('click', () => {
    const navLinks = document.querySelector('.nav-links');
    const hamburger = document.querySelector('.hamburger');
    navLinks.classList.toggle('active');
    hamburger.classList.toggle('active');
});

// JavaScript to handle responsive navigation
window.addEventListener('resize', () => {
    const navLinks = document.querySelector('.nav-links');
    const hamburger = document.querySelector('.hamburger');
    if (window.innerWidth > 768) {
        navLinks.classList.remove('active');
        hamburger.classList.remove('active');
    }
});

// JavaScript to handle responsive navigation


// JavaScript to handle responsive navigation
// window.addEventListener('resize', () => {
//     const navLinks = document.querySelector('.nav-links');
//     if (window.innerWidth > 768) {
//         navLinks.classList.remove('active');
//     }
// });



//     const carousel = document.querySelector('.carousel');
// const leftBtn = document.querySelector('.left-btn');
// const rightBtn = document.querySelector('.right-btn');

// let currentIndex = 0;

// function updateCarousel() {
//     const cardWidth = carousel.querySelector('.card').offsetWidth;
//     const gap = 20; // Gap between cards
//     const visibleWidth = carousel.offsetWidth; // Visible area width
//     const totalCards = carousel.children.length;

//     // Ensure the offset doesn't exceed the total width of cards
//     const maxOffset = (totalCards * (cardWidth + gap)) - visibleWidth;
//     const offset = Math.min((cardWidth + gap) * currentIndex * -1, maxOffset);

//     carousel.style.transform = `translateX(${offset}px)`;
// }


// rightBtn.addEventListener('click', () => {
//     const visibleCards = 3; // Number of visible cards
//     const totalCards = carousel.children.length;

//     if (currentIndex < totalCards - visibleCards) {
//         currentIndex++;
//         updateCarousel();
//     }
// });

// leftBtn.addEventListener('click', () => {
//     if (currentIndex > 0) {
//         currentIndex--;
//         updateCarousel();
//     }
// });


