<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #121212; /* Deep black background for a modern dark theme */
            color: #e0e0e0; /* Light gray text for readability */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Make the body take the full height of the viewport */
            margin: 0; /* Remove any default margin */
        }
        .container {
            background-color: #1e1e1e; /* Darker background for the form */
            border-radius: 15px;
            padding: 60px; /* Increased padding for larger height */
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.4); /* Stronger shadow for depth */
            max-width: 500px; /* Reduced width to 500px */
            width: 100%;
        }
        h2 {
            color: #76c7c0; /* Soft turquoise for the heading */
            text-align: center;
            margin-bottom: 50px; /* Increased space for the heading */
        }
        label {
            color: #e0e0e0; /* Light gray for label text */
        }
        .form-control {
            background-color: #333333; /* Darker input fields */
            color: #e0e0e0; /* Light text inside inputs */
            border-radius: 8px;
            border: 1px solid #444; /* Slightly lighter border for inputs */
            height: 50px; /* Increased height for input fields */
        }
        .form-control:focus {
            background-color: #004d40; /* Dark green focus background */
            color: #fff; /* White text when focused */
            border-color: #76c7c0; /* Turquoise border on focus */
        }
        .btn-primary {
            background-color: #00796b; /* Deep green for primary button */
            border: none;
            border-radius: 8px;
            padding: 16px; /* Larger padding for the button */
            font-size: 18px; /* Larger text for the button */
        }
        .btn-primary:hover {
            background-color: #004d40; /* Darker green on hover */
        }
        .form-check-label {
            color: #e0e0e0; /* Light gray checkbox label */
        }
        .form-check-input:checked {
            background-color: #76c7c0; /* Turquoise checkbox checked state */
            border-color: #76c7c0; /* Border color for checked state */
        }
        .form-check-input:focus {
            border-color: #004d40; /* Dark green focus border */
        }
        .forgot-password {
            color: #76c7c0; /* Soft turquoise for the link */
            text-align: center;
            display: block;
            margin-top: 20px;
            text-decoration: none;
        }
        .forgot-password:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Reset Password</h2>
    <form method="post" action="resetpassword">
    
    <input type="hidden" name="token" value="${token}"> <!-- token passed from controller -->
    
       
        
        <!-- Password -->
        <div class="mb-4">
            <label for="password" class="form-label">New Password</label>
            <input type="password" class="form-control" id="password" name="newPassword" required>
        </div>
        
        <!-- Confirm Password -->
        <div class="mb-4">
            <label for="confirmpassword" class="form-label">New Password</label>
            <input type="password" class="form-control" id="password" name="confirmnewpassword" required>
        </div>
        
        
        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary w-100">Reset Password</button>
        
        <!-- Forgetpassword (href) -->
        <div class="form-check mb-4">
            <a href="login" class="forgot-password">login</a>
         
        </div>
        
        <div class="form-check mb-4">
           <br>
			<span class="text-danger">${error}</span>
			<br>
			<span class="text-danger">${sucess}</span>
         
        </div>
    </form>
</div>

<!-- Bootstrap JS for interactivity -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
