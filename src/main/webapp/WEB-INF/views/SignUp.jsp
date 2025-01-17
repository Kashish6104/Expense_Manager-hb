<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create User</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #121212; /* Deep black background for a modern dark theme */
            color: #e0e0e0; /* Light gray text for readability */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full height of the viewport */
            margin: 0; /* Remove default margin */
        }
        .container {
            background-color: #1e1e1e; /* Darker background for the form */
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.4); /* Stronger shadow for depth */
            max-width: 600px;
            width: 100%;
        }
        h2 {
            color: #76c7c0; /* Soft turquoise for the heading */
            text-align: center;
            margin-bottom: 30px;
        }
        label {
            color: #e0e0e0; /* Light gray for label text */
        }
        .form-control, .form-select {
            background-color: #333333; /* Darker input fields */
            color: #e0e0e0; /* Light text inside inputs */
            border-radius: 8px;
            border: 1px solid #444; /* Slightly lighter border for inputs */
        }
        .form-control:focus, .form-select:focus {
            background-color: #004d40; /* Dark green focus background */
            color: #fff; /* White text when focused */
            border-color: #76c7c0; /* Turquoise border on focus */
        }
        .btn-primary {
            background-color: #00796b; /* Deep green for primary button */
            border: none;
            border-radius: 8px;
            padding: 12px;
            font-size: 16px;
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
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>Create User</h2>
    <form method="post" action="saveuser">
        <!-- First Name -->
        <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        
        <!-- Last Name -->
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        
        <!-- Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        
        <!-- Password -->
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        
        <!-- Role (Enum: Admin/User) -->
        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-select" id="role" name="role" required>
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        
        <!-- Active (Checkbox) -->
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="active" name="active" value="true">
            <label class="form-check-label" for="active">
                Active
            </label>
        </div>
        
        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary w-100">Create User</button>
    </form>
</div>

<!-- Bootstrap JS for interactivity -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
