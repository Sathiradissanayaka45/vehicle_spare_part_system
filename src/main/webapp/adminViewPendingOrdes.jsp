<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Pending Orders</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <style>
        /* Add your custom styles here */
        body {
            font-family: Arial, sans-serif;
        }
        #sidebar {
            position: fixed;
            top: 0;
            left: 0;
            bottom: 0;
            width: 250px;
            background-color: #343a40;
            padding: 20px;
        }
        .container {
            margin-left: 250px;
            padding: 20px;
        }
        .h1 {
            text-align: center; /* Align the heading to the center */
        }
        .animated-row {
            animation: slideInFromTop 0.5s ease-out;
        }
        @keyframes slideInFromTop {
            0% {
                transform: translateY(-100%);
            }
            100% {
                transform: translateY(0);
            }
        }
        /* Additional styling for the table */
        .table th,
        .table td {
            vertical-align: middle;
        }
        .table th {
            background-color: #343a40;
            color: white;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: rgba(0, 0, 0, 0.05);
        }
        .table-hover tbody tr:hover {
            background-color: rgba(0, 0, 0, 0.075);
        }
        .btn {
            padding: 6px 12px;
            font-size: 14px;
            border-radius: 20px;
        }
    </style>
</head>
<body>
<!-- Sidebar -->
<div id="sidebar">
  <h2 class="text-white">Admin Dashboard</h2>
  <ul class="nav flex-column">
    <li class="nav-item">
      <a class="nav-link text-white" href="admin_dashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="ViewRecievedOrders?page=Pending"><i class="fas fa-shopping-cart"></i> Orders</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="add-product.jsp"><i class="fas fa-box"></i> Add Product</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="DisplayProductsServlet"><i class="fas fa-users"></i> All products</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="DisplaySendRequestServlet?page=supplier"><i class="fas fa-exclamation-circle"></i> View Pending Requests</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="DisplaySendRequestServlet?page=rejected"><i class="fas fa-times-circle"></i> View Rejected Requests</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="DisplaySendRequestServlet?page=orders"><i class="fas fa-check-circle"></i> View Accepted Requests</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="add-supplier-request.jsp"><i class="fas fa-receipt"></i>Send Request for Supplier</a>
    </li>
    <li class="nav-item">
      <a class="nav-link text-white" href="login.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </li>
  </ul>
</div>
<div class="container mt-4">
    <h1>Supplier Requests</h1>
    <% 
        List<Model.SupplierRequest> supplierRequests = (List<Model.SupplierRequest>) request.getAttribute("supplierRequests");
        if (supplierRequests != null && !supplierRequests.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    %>
    <table class="table table-striped table-hover">
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
            <% 
                for (Model.SupplierRequest supplierRequest : supplierRequests) {
                    if (supplierRequest.getStatus().equals("Pending")) {
            %>
            <tr class="animated-row">
                <td><%= supplierRequest.getId() %></td>
                <td><%= supplierRequest.getProductName() %></td>
                <td><%= supplierRequest.getQuantity() %></td>
                <td><%= supplierRequest.getDetails() %></td>
                <td><%= supplierRequest.getStatus() %></td>
                <td><%= sdf.format(supplierRequest.getRequestTime()) %></td>
                <td>
                    <a href="UpdateSendSupplierRequestServlet?idrequest=<%= supplierRequest.getId() %>" class="btn btn-primary">Update</a>
                    <a href="#" onclick="confirmDelete(<%= supplierRequest.getId() %>)" class="btn btn-danger">Delete</a>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
    <% 
        } else {
            out.println("No pending supplier requests found.");
        }
    %>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Font Awesome JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
<!-- Custom Script -->
<script>
    function confirmDelete(idrequest) {
        if (confirm("Are you sure you want to delete this product?")) {
            // Prevent default link action
            event.preventDefault();

            // AJAX request to delete the product
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Call DisplayProductsServlet to update product list
                        window.location.href = "DisplaySendRequestServlet?page=supplier";
                    } else {
                        // Handle error
                        alert("Failed to delete product");
                    }
                }
            };
            xhr.open("GET", "DeleteSendSupplierRequestServlet?idrequest=" + idrequest, true);
            xhr.send();
        }
    }
</script>
</body>
</html>
