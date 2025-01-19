<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact With Educare Intern Technology</title>
    <style>

        /* General Body and Header Styling */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #ffffff;
}

/* Header Styling */
header {
    background-color: #be2bbe;
    color: white;
    padding: 10px 20px;
}

.navbar-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
}

.company-info {
    display: flex;
    align-items: center;
    gap: 10px;
}

.logo img {
    height: 50px;
    border-radius: 50px;
}

/* Navigation Links */
.nav-links {
    display: flex;
    gap: 20px;
    align-items: center;
}

.nav-links a {
    color: white;
    text-decoration: none;
    font-size: 1rem;
}

.nav-links a:hover {
    font-weight: bold;
}

/* Hamburger Menu */
.hamburger {
    display: none;
    font-size: 1.5rem;
    background: none;
    border: none;
    color: white;
    cursor: pointer;
}


footer {
    background-color: #be2bbe;
    color: white;
    text-align: center;
    padding: 10px 0;
    /* margin-top: 20px; */
}
/* Mobile View */
@media (max-width: 768px) {
    .nav-links {
        display: none;
        flex-direction: column;
        background-color: #590b6e;
        position: absolute;
        top: 60px;
        right: -31px;
        left: -21px;
        padding: 10px 20px;
        gap: 15px;
        z-index: 1000;
    }

    .nav-links.active {
        display: flex;
    }

    .hamburger {
        display: block;
    }
}

/* Contact Form Section */
#contact-form {
    max-width: 600px;
    margin: 30px auto;
    padding: 20px;
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h2 {
    text-align: center;
    margin-top: 20px;
    color: #be2bbe;
}

.form-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.form-group {
    display: flex;
    flex-direction: column;
}

label {
    font-weight: bold;
    margin-bottom: 5px;
}

input,
textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 1rem;
    transition: border-color 0.3s ease-in-out;
}

input:focus,
textarea:focus {
    border-color: #be2bbe;
    outline: none;
}

.btn-submit {
    background-color: #be2bbe;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    cursor: pointer;
    text-align: center;
    transition: background-color 0.3s ease-in-out;
}

.btn-submit:hover {
    background-color: #a522a5;
}

#location {
    text-align: center;
    margin: 20px 0;
    padding: 20px;
}

.map-container {
    max-width: 100%;
    margin: 0 auto;
    border: 1px solid #ddd;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.map-container iframe {
    width: 100%;
    height: 450px;
    border: none;
}


/* Responsive Contact Form */
@media (max-width: 480px) {
    #contact-form {
        padding: 15px;
    }

    input,
    textarea {
        font-size: 0.9rem;
    }

    .btn-submit {
        font-size: 0.9rem;
    }
}

    </style>
</head>
<body>
    <header>
        <div class="navbar-container">
            <div class="company-info">
                <div class="logo">
                    <img src="LOGO-2.jpg" alt="Educare Logo">
                </div>
            </div>
            <button class="hamburger" aria-label="Toggle navigation">
                &#9776;
            </button>
            <nav class="nav-links">
                <a href="/">Home</a>
                <a href="#contact-form">Contact</a>
                <a href="#location">Location</a>

            </nav>
        </div>
    </header>

    <!-- Contact Form Section -->
    <section id="contact-form">
        <h2>Contact Us</h2>
        <c:if test="${not empty success}">
            <p style="color: green; text-align: center;">${success}</p>
        </c:if>

        <c:if test="${not empty error}">
            <h4 style="color: rgb(255, 0, 0);">${error}</h4>
           </c:if>
        <form action="/send-contact-message" method="POST" class="form-container">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="subject">Subject:</label>
                <input type="text" id="subject" name="subject" placeholder="Enter the subject" required>
            </div>
            <div class="form-group">
                <label for="message">Message:</label>
                <textarea id="message" name="message" placeholder="Write your message here" rows="5" required></textarea>
            </div>
            <button type="submit" class="btn-submit">Submit</button>
        </form>
    </section>


    <section id="location">
        <h2>Our Location</h2>
        <div class="map-container">

            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3665.4560736245808!2d87.84657048001452!3d23.262872324874575!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39f849e96cf0a651%3A0xfb2d7458b3b23406!2sEducare%20Skill%20Academy%20-%20Bardhaman!5e0!3m2!1sen!2sin!4v1737014807409!5m2!1sen!2sin" 
            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">
        </iframe>
        </div>
    </section>


    
    <footer>        
        <p>&copy; 2025 Educare Intern Technology. All rights reserved.</p>
    </footer>
    <script>
    // Toggle visibility of the navigation links
        document.querySelector('.hamburger').addEventListener('click', () => {
            const navLinks = document.querySelector('.nav-links');
            navLinks.classList.toggle('active');
        });

        
document.addEventListener('DOMContentLoaded', function() {
    // Handle all types of refresh
    window.addEventListener('beforeunload', function(e) {
        localStorage.setItem('refreshTarget', '/');
    });

    // Check if we're coming from a refresh
    if (performance.navigation.type === 1) {
        window.location.href = localStorage.getItem('refreshTarget') || '/';
    }

    // Handle F5 and Ctrl+R
    document.addEventListener('keydown', function(e) {
        if (e.key === 'F5' || (e.ctrlKey && e.key === 'r')) {
            e.preventDefault();
            window.location.href = '/';
        }
    });
});
        </script>

</body>
</html>
