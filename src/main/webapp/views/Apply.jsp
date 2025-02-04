<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apply On Educare Intern Technology</title>
    <link rel="icon" type="image/x-icon" href="image_web/SearchLogo.png">
<style>
    body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background: url('image_web/back.jpg') no-repeat center center fixed;
    background-size: cover;
    color: #333;

}

/* Header Styling */
header {
    background:  linear-gradient(to right, #ab16c2, #3916c3);
    color: white;
    padding: 15px 20px;
    position: sticky;
    top: 0;
    z-index: 1000;
}

.navbar-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.company-info {
    display: flex;
    align-items: center;
    gap: 10px;
}

.logo img {
    height: 40px;
    width: 150px;
    border-radius: 25px;
}


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
/* Form Container Styling */
.form-container {
    max-width: 400px;
    margin: 50px auto;
    background-color: rgb(255 255 255 / 20%);
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(175, 54, 186, 0.737);
    padding: 20px;
    text-align: center;
}

.form-container h2 {
    margin-bottom: 20px;
    /* color: #444; */
    background: linear-gradient(to right, #ab16c2, #3916c3);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

.form-container h4 {
    margin-bottom: 10px;
    font-size: 1rem;
}

.form-group {
    margin-bottom: 20px;
    text-align: left;
    /* margin: 20px; */
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
}

.form-group input,
.form-group select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 1rem;
}

.form-group button {
    width: 100%;
    padding: 10px;
    background:  linear-gradient(to right, #ab16c2, #3916c3);
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin: 10px;
}

.form-group button:hover {
    background:  linear-gradient(to right, #ab16c2, #3916c3);
}

/* Responsive Design */
@media (max-width: 480px) {
    .form-container {
        width: 90%;
        margin: 20px auto;
    }

    header {
        padding: 10px;
    }

    .logo img {
        height: 40px;
        width:130px;
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
            <nav class="nav-links">
                <a href="/">Home</a>
                <a href="BackToContact">Contact</a>
                <a href="#form-container">Apply</a>

            </nav>
        </div>
    </header>

    <div class="form-container">
        <h2>Apply for Internship</h2>
        <c:if test="${not empty SucMsg}">
            <h4 style="color: rgb(82, 169, 48);">${SucMsg}</h4>
           </c:if>
           
    <c:if test="${not empty FailMsg}">
        <h4 style="color: rgb(255, 0, 0);">${FailMsg}</h4>
       </c:if>
        <form  action="addinfo" method="POST"  id="internship-form">
            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>
            <div class="form-group">
                <label for="email">Email Address:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="mobile">Mobile Number:</label>
                <input type="tel" id="mobile" name="mobile" placeholder="Enter your mobile number" required>
            </div>
            <div class="form-group">
                <label for="domain">Internship Domain:</label>
                <select id="domain" name="domain" required>
                    <option value="" disabled selected>Select a domain</option>
                    <option value="web-development">Web Development</option>
                    <option value="android-development">Android Development</option>
                    <option value="data-science">Data Science</option>
                    <option value="java-programming">Java Programming</option>
                    <option value="python-programming">Python Programming</option>
                    <option value="c++-programming">C++ Programming</option>
                    <option value="c-programming">C Programming</option>
                    <option value="AWS">AWS</option>
                    <option value="AI-ML">AI-ML</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">Apply Now</button>
            </div>
        </form>
    </div>

   
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Handle all types of refresh
            window.addEventListener('beforeunload', function(e) {
                localStorage.setItem('refreshTarget', '/user_apply'); // Update to Apply page
            });
        
            // Check if we're coming from a refresh
            if (performance.navigation.type === 1) {
                window.location.href = localStorage.getItem('refreshTarget') || '/user_apply'; // Update to Apply page
            }
        
            // Handle F5 and Ctrl+R
            document.addEventListener('keydown', function(e) {
                if (e.key === 'F5' || (e.ctrlKey && e.key === 'r')) {
                    e.preventDefault();
                    window.location.href = '/user_apply'; // Update to Apply page
                }
            });
        });
        </script>
</body>
</html>