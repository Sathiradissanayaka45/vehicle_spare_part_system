<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rejected Order History</title>
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
            background-color: #000;
            color: #343a40;
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
        /* Table styling */
        table {
            width: 100%;
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #f2f2f2;
        }
        th {
            background-color: #343a40;
            color: #fff;
            font-weight: bold;
            text-transform: uppercase;
        }
        tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        tbody tr:hover {
            background-color: #e9ecef;
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
        <h1>View Orders</h1>
        <% 
            List<Model.SupplierRequest> supplierRequests = (List<Model.SupplierRequest>) request.getAttribute("supplierRequests");
            if (supplierRequests != null && !supplierRequests.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        %>
        <div class="table-responsive">
            <table class="table table-hover">
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
                            if (supplierRequest.getStatus().equalsIgnoreCase("rejected")) {
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
        </div>
        <% 
            } else {
                out.println("No accepted supplier requests found.");
            }
        %>
    </div>
</body>
</html>
