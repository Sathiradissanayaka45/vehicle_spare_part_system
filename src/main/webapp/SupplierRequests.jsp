<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Received Orders</title>
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
    .table {
        background-color: #fff;
    }
    .table th,
    .table td {
        border: 1px solid #dee2e6;
        padding: 8px;
        text-align: center;
    }
    .table th {
        background-color: #343a40;
        color: #fff;
    }
    .btn {
        margin-right: 5px;
    }
    /* Animation */
    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }
    .animated-row {
        animation: fadeIn 0.5s ease forwards;
        opacity: 0;
    }
    .animated-row:nth-child(even) {
        animation-delay: 0.1s;
    }
    .animated-row:nth-child(odd) {
        animation-delay: 0.3s;
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
    <div class="container mt-4">
        <h1 class="text-center">Supplier Requests</h1>
        <% 
            List<Model.SupplierRequest> supplierRequests = (List<Model.SupplierRequest>) request.getAttribute("supplierRequests");
            if (supplierRequests != null && !supplierRequests.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int rowIndex = 0;
                for (Model.SupplierRequest supplierRequest : supplierRequests) {
                    if (supplierRequest.getStatus().equals("Pending")) {
        %>
        <div class="animated-row">
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Details</th>
                        <th>Status</th>
                        <th>Request Time</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%= supplierRequest.getId() %></td>
                        <td><%= supplierRequest.getProductName() %></td>
                        <td><%= supplierRequest.getQuantity() %></td>
                        <td><%= supplierRequest.getDetails() %></td>
                        <td><%= supplierRequest.getStatus() %></td>
                        <td><%= sdf.format(supplierRequest.getRequestTime()) %></td>
                        <td>
                            <form action="UpdateStatusServlet" method="post">
                                <input type="hidden" name="requestId" value="<%= supplierRequest.getId() %>">
                                <button type="submit" name="action" value="accept" class="btn btn-success">Accept</button>
                                <button type="submit" name="action" value="reject" class="btn btn-danger">Reject</button>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <% 
                    }
                    rowIndex++;
                }
            } else {
                out.println("No pending supplier requests found.");
            }
        %>
    </div>
</body>
</html>
