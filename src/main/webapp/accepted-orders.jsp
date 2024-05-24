<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Payment" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accepted Orders</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .container {
            margin-top: 20px;
        }

        .animated-table {
            overflow: hidden;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .animated-table table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }

        .animated-table th,
        .animated-table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
            transition: background-color 0.3s, transform 0.2s;
        }

        .animated-table th {
            background-color: #343a40;
            color: #fff;
        }

        .animated-table tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .animated-table tbody tr:hover {
            background-color: #e9ecef;
            transform: scale(1.01);
        }

        .btn-group {
            margin-right: 5px;
        }
        
        .animated-header {
            background: linear-gradient(90deg, rgba(220, 53, 69, 1) 0%, rgba(220, 53, 69, 0.8) 50%, rgba(220, 53, 69, 1) 100%);
            color: #fff;
            padding: 20px;
            text-align: center;
            animation: gradient-animation 3s infinite;
            position: relative;
            margin-bottom: 20px;
            border-radius: 10px;
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
    <div class="container">
        <h2>Accepted Orders</h2>
        <div class="animated-table">
            <table class="table table-striped table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>Payment ID</th>
                        <th>Customer ID</th>
                        <th>Full Name</th>
                        <th>Mobile Number</th>
                        <th>Address</th>
                        <th>Postal Code</th>
                        <th>Status</th> <!-- Ensure the Status column is here -->
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<Model.Payment> paymentList = (List<Model.Payment>) request.getAttribute("payments");
                    if (paymentList != null && !paymentList.isEmpty()) {
                        for (Model.Payment payment : paymentList) {
                    %>
                    <tr>
                        <td><%= payment.getPaymentId() %></td>
                        <td><%= payment.getCustomerId() %></td>
                        <td><%= payment.getFullName() %></td>
                        <td><%= payment.getMobileNumber() %></td>
                        <td><%= payment.getAddress() %></td>
                        <td><%= payment.getPostalCode() %></td>
                        <td><%= payment.getStatus() %></td>
                    </tr>
                    <% 
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8" class="text-center">No accepted orders found.</td>
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
        <!-- Include jQuery -->
    <script src="js/jquery-2.1.1.min.js"></script>
    <!-- Include Bootstrap JS -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
