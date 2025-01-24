<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Expense</title>
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
            background-color: #1e1e1e; /* Darker background for form */
            border-radius: 15px;
            padding: 60px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.4); /* Shadow for depth */
            max-width: 600px;
            width: 100%;
        }
        h2 {
            color: #76c7c0; /* Turquoise for heading */
            text-align: center;
            margin-bottom: 50px;
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
            background-color: #00796b; /* Deep green for button */
            border: none;
            border-radius: 8px;
            padding: 16px; /* Larger padding for button */
            font-size: 18px; /* Larger text for button */
        }
        .btn-primary:hover {
            background-color: #004d40; /* Darker green on hover */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add Expense</h2>
    <form method="post" action="saveexpense">
        
        <!-- Title -->
        <div class="mb-4">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>

        <!-- Amount -->
        <div class="mb-4">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" class="form-control" id="amount" name="amount" required>
        </div>

        <!-- Description -->
        <div class="mb-4">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>

		
		 <div class="mb-4">
            <label for="transactionDate" class="form-label">Transaction Date</label>
             <input type="date" class="form-control" id="description" name="transactionDate" required>
        </div>	
        
         <!-- Drop Down
         <div class="mb-4">
            <label for="category" class="form-label">Category</label>
             	<select name="category" id="description" >
				  <option value="food">food</option>
				  <option value="cloths">"cloths"</option>
				  <option value="automobile">automobile</option>
				  <option value="household">household</option>
				</select>
        </div>	
         -->
			
        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary w-100">Add Expense</button>
        
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
