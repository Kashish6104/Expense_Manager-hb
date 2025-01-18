<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #121212; /* Dark theme background */
            color: #e0e0e0; /* Light gray text */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
            margin: 0;
        }
        .container {
            background-color: #1e1e1e; /* Darker background for container */
            border-radius: 15px;
            padding: 60px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.4); /* Shadow for depth */
            max-width: 700px;
            width: 100%;
            text-align: center;
        }
        h2 {
            color: #76c7c0; /* Turquoise color for heading */
            margin-bottom: 50px; /* Space below heading */
        }
        a {
            display: block;
            color: #76c7c0; /* Turquoise for links */
            text-decoration: none;
            margin-bottom: 20px;
            font-size: 20px;
        }
        a:hover {
            text-decoration: underline; /* Underline on hover */
        }
        .btn-primary {
            background-color: #00796b; /* Deep green button */
            border: none;
            border-radius: 8px;
            padding: 16px;
            font-size: 18px;
            margin-top: 40px;
        }
        .btn-primary:hover {
            background-color: #004d40; /* Dark green on hover */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome to Your Home Page!</h2>
    
    <!-- Links to various sections -->
    <a href="listaccount">List Account</a>
    <a href="addaccount">Add New Account</a>
    <a href="listvendor">List Vendor</a>
    <a href="addvendor">Add New Vendor</a>
   

    <!-- Logout Button -->
    <a href="login" class="btn btn-primary w-100">Logout</a>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
