    // JavaScript to toggle navigation visibility
    document.querySelector('.hamburger').addEventListener('click', () => {
        const navLinks = document.querySelector('.nav-links');
        navLinks.classList.toggle('active');
    });
    const carousel = document.querySelector('.carousel');
const leftBtn = document.querySelector('.left-btn');
const rightBtn = document.querySelector('.right-btn');

let currentIndex = 0;

function updateCarousel() {
    const cardWidth = carousel.querySelector('.card').offsetWidth;
    const gap = 20; // Gap between cards
    const offset = (cardWidth + gap) * currentIndex * -1;
    carousel.style.transform = `translateX(${offset}px)`;
}

rightBtn.addEventListener('click', () => {
    const visibleCards = 3; // Number of visible cards
    const totalCards = carousel.children.length;

    if (currentIndex < totalCards - visibleCards) {
        currentIndex++;
        updateCarousel();
    }
});

leftBtn.addEventListener('click', () => {
    if (currentIndex > 0) {
        currentIndex--;
        updateCarousel();
    }
});

