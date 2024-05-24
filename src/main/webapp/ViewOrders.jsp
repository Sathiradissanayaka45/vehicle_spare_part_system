<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Accepted Orders</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
        .sidebar {
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            width: 250px;
            background-color: #343a40;
            padding: 20px;
            overflow-y: auto;
        }
        .sidebar a {
            display: block;
            padding: 10px 15px;
            color: #fff;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            padding: 15px;
            text-align: center;
        }
        .container {
            margin-left: 250px;
            padding: 20px;
        }
        .h1 {
            text-align: center; /* Align the heading to the center */
        }
        .table {
            width: 100%;
            margin-bottom: 1rem;
            color: #212529;
            background-color: #fff;
            opacity: 0; /* Initially hidden */
            animation: fadeIn 1s ease-in forwards; /* Fade-in animation */
        }
        .table th,
        .table td {
            padding: 0.75rem;
            vertical-align: top;
            border-top: 1px solid #dee2e6;
        }
        .table thead th {
            vertical-align: bottom;
            border-bottom: 2px solid #dee2e6;
            background-color: #343a40;
            color: #fff;
        }
        .table tbody + tbody {
            border-top: 2px solid #dee2e6;
        }
        .table-bordered {
            border: 1px solid #dee2e6;
        }
        .table-bordered th,
        .table-bordered td {
            border: 1px solid #dee2e6;
        }
        .table-bordered thead th,
        .table-bordered thead td {
            border-bottom-width: 2px;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f8f9fa;
        }
        .table-striped tbody tr:hover {
            background-color: #e9ecef;
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h2 class="text-white mb-4">Supplier Dashboard</h2>
        <a href="supplier-dashboard.jsp" class="active"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
        <a href="DisplaySupplierProductsServlet"><i class="fas fa-box"></i> View Products</a>
        <a href="add-supplier-product.jsp"><i class="fas fa-plus"></i> Add Product</a>
        <a href="DisplaySupplierRequestsServlet?page=supplier"><i class="fas fa-file-invoice-dollar"></i> View Pending Orders</a>
        <a href="DisplaySupplierRequestsServlet?page=orders"><i class="fas fa-list-alt"></i> View Accepted Orders</a>
        <a href="DisplaySupplierRequestsServlet?page=rejected"><i class="fas fa-ban"></i> View Rejected Orders</a>
        <!-- Logout Button -->
        <a href="login.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </div>
    <!-- Page Content -->
    <div class="container mt-4">
        <h1 class="text-center">View Orders</h1>
        <% 
            List<Model.SupplierRequest> supplierRequests = (List<Model.SupplierRequest>) request.getAttribute("supplierRequests");
            if (supplierRequests != null && !supplierRequests.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        %>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Details</th>
                    <th>Status</th>
                    <th>Request Time</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Model.SupplierRequest supplierRequest : supplierRequests) {
                        if (supplierRequest.getStatus().equalsIgnoreCase("accepted")) {
                %>
                <tr>
                    <td><%= supplierRequest.getId() %></td>
                    <td><%= supplierRequest.getProductName() %></td>
                    <td><%= supplierRequest.getQuantity() %></td>
                    <td><%= supplierRequest.getDetails() %></td>
                    <td><%= supplierRequest.getStatus() %></td>
                    <td><%= sdf.format(supplierRequest.getRequestTime()) %></td>
                </tr>
                <% 
                        }
                    }
                %>
            </tbody>
        </table>
        <% 
            } else {
                out.println("No accepted supplier requests found.");
            }
        %>
    </div>
</body>
</html>
