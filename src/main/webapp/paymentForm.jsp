<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Form</title>
    <!-- Include Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Include your custom CSS if any -->
    <link href="css/global.css" rel="stylesheet">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .red-theme {
            color: #fff;
            background-color: #dc3545;
        }

        .red-theme button[type="submit"] {
            background-color: #dc3545;
            border-color: #dc3545;
        }

        .red-theme button[type="submit"]:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        .account-details {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .account-details h3 {
            color: #dc3545;
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }

        .custom-form {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        .custom-form h2 {
            color: #dc3545;
            font-size: 28px;
            margin-bottom: 30px;
            text-align: center;
        }

        .custom-form label {
            font-weight: bold;
            color: #333;
        }

        .custom-form .form-control {
            border-radius: 20px;
            margin-bottom: 20px;
        }

        .custom-form button[type="submit"] {
            background-color: #dc3545;
            border-color: #dc3545;
            border-radius: 20px;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            text-transform: uppercase;
        }

        .custom-form button[type="submit"]:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        .animated-header {
            background: linear-gradient(90deg, rgba(220, 53, 69, 1) 0%, rgba(220, 53, 69, 0.8) 50%, rgba(220, 53, 69, 1) 100%);
            color: #fff;
            padding: 20px;
            text-align: center;
            animation: gradient-animation 3s infinite;
            position: relative;
            margin-bottom: 20px;
        }

        @keyframes gradient-animation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .header-buttons .btn {
            margin: 10px;
            border-radius: 20px;
            transition: transform 0.2s;
        }

        .header-buttons .btn:hover {
            transform: scale(1.1);
        }
        .header-buttons .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
            color: #fff;
        }

        .header-buttons .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }

        .header-title {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .home-button {
            position: absolute;
            top: 10px;
            left: 10px;
        }

        .home-button a {
            color: #fff;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            background-color: rgba(0, 0, 0, 0.2);
            padding: 10px 20px;
            border-radius: 20px;
            transition: background-color 0.3s;
        }

        .home-button a:hover {
            background-color: rgba(0, 0, 0, 0.4);
        }
    </style>
</head>
<body>
<!-- Animated Header with Buttons -->
<div class="animated-header">
    <div class="home-button">
        <a href="index.jsp">Home</a>
    </div>
    <h1 class="header-title">Welcome to the Payment Portal</h1>
    <div class="header-buttons">
        <a href="GetPaymentsServlet" class="btn btn-primary">My Order History</a>
        <a href="GetPaymentsServlet?page=Pending" class="btn btn-warning">View Pending Orders</a>
        <a href="GetPaymentsServlet?page=Accepted" class="btn btn-danger">View Accepted Orders</a>
        <a href="login.jsp" class="btn btn-secondary">Logout</a> <!-- Added logout button -->
    </div>
</div>
    <div class="container py-5">
        <div class="row">
            <!-- Account Details Column -->
            <div class="col-md-6">
                <div class="account-details">
                    <h3>Account Details</h3>
                    <p><strong>Account Number:</strong> 9035-3421-4321-1234</p>
                    <p><strong>Account Holder Name:</strong> Amandi</p>
                    <p><strong>Bank Name:</strong>Commercial Bank</p>
                    <p><strong>Branch:</strong>Malabe Branch</p>
                </div>
            </div>
            <!-- Payment Form Column -->
            <div class="col-md-6">
                <div class="custom-form">
                    <h2>Payment Form</h2>
                    <form action="AddPaymentServlet" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="paymentSlip">Upload Payment Slip:</label>
                            <input type="file" class="form-control" id="paymentSlip" name="paymentSlip" accept=".pdf,.jpg,.png" required>
                        </div>
                        <div class="form-group">
                            <label for="fullName">Full Name:</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" required>
                        </div>
                        <div class="form-group">
                            <label for="mobileNumber">Mobile Number:</label>
                            <input type="number" class="form-control" id="mobileNumber" name="mobileNumber" pattern="[0-9]{10}" required>
                        </div>
                        <div class="form-group">
                            <label for="address">Address:</label>
                            <textarea class="form-control" id="address" name="address" rows="3" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="postalCode">Postal Code:</label>
                            <input type="number" class="form-control" id="postalCode" name="postalCode" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Include jQuery -->
    <script src="js/jquery-2.1.1.min.js"></script>
    <!-- Include Bootstrap JS -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
