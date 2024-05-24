<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Payment" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Payment</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
                .container {
            max-width: 600px;
        }

        .form-group {
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: bold;
            color: #333;
        }

        .form-control {
            border-radius: 20px;
            border-color: #ccc;
            box-shadow: none;
        }

        .form-control:focus {
            border-color: #6c757d;
            box-shadow: 0 0 0 0.2rem rgba(108, 117, 125, 0.25);
        }

        .btn-primary {
            background-color: #dc3545;
            border-color: #dc3545;
            border-radius: 20px;
            padding: 10px 20px;
        }

        .btn-primary:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        /* Optional: Center the form */
        .center-form {
            margin: 0 auto;
            margin-top: 50px;
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
    <div class="container center-form">
        <h2 class="text-center mb-4">Update Payment Details</h2>
        <form action="UpdatePaymentServlet" method="post">
            <div class="form-group">
                <label for="image" class="form-label">Current Image:</label>
                <div>
                    <img src="<%= (request.getAttribute("payment") != null) ? ((Payment)request.getAttribute("payment")).getPaymentSlip() : "" %>" alt="Payment Image" style="max-width: 200px;">
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="<%= (request.getAttribute("payment") != null) ? ((Payment)request.getAttribute("payment")).getFullName() : "" %>">
            </div>

            <div class="form-group">
                <label for="details" class="form-label">Mobile Number:</label>
                <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="<%= (request.getAttribute("payment") != null) ? ((Payment)request.getAttribute("payment")).getMobileNumber() : "" %>">
            </div>

            <div class="form-group">
                <label for="price" class="form-label">Address:</label>
                <textarea class="form-control" id="address" rows="3" name="address"><%= (request.getAttribute("payment") != null) ? ((Payment)request.getAttribute("payment")).getAddress() : "" %></textarea>
            </div>

            <div class="form-group">
                <label for="stock" class="form-label">Postal Code:</label>
                <input type="text" class="form-control" id="postalCode" name="postalCode" value="<%= (request.getAttribute("payment") != null) ? ((Payment)request.getAttribute("payment")).getPostalCode() : "" %>">
            </div>

            <input type="hidden" name="paymentId" value="<%= (request.getAttribute("payment") != null) ? ((Payment)request.getAttribute("payment")).getPaymentId() : "" %>">

            <div class="form-group text-center">
                <button type="submit" class="btn btn-primary">Update Payment</button>
            </div>
        </form>
    </div>
</body>
</html>