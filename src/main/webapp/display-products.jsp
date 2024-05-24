<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Products</title>
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
        .table {
            margin-top: 20px;
            border-radius: 10px;
            overflow: hidden;
        }
        .table th {
            background-color: #007bff;
            color: #fff;
        }
        .table th,
        .table td {
            text-align: center;
            vertical-align: middle !important;
        }
        .table .btn {
            padding: 6px 12px;
            font-size: 14px;
        }
        .table .btn-primary {
            background-color: #28a745;
            border-color: #28a745;
        }
        .table .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .table .btn-primary:hover,
        .table .btn-danger:hover {
            opacity: 0.9;
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

<div class="container mt-5">
    <h2>All Products</h2>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Details</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Product> productList = (List<Product>) request.getAttribute("productList");
                for (Product product : productList) {
                %>
                <tr class="animated-row">
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getDetails() %></td>
                    <td>Rs.<%= product.getPrice() %></td>
                    <td><%= product.getStock() %></td>
                    <td>
                        <a href="UpdateProductServlet?idproduct=<%= product.getId() %>" class="btn btn-primary">Update</a>
                        <a href="#" onclick="confirmDelete(<%= product.getId() %>)" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Font Awesome JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
<script>
    function confirmDelete(productId) {
        if (confirm("Are you sure you want to delete this product?")) {
            // Prevent default link action
            event.preventDefault();

            // AJAX request to delete the product
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Call DisplayProductsServlet to update product list
                        window.location.href = "DisplayProductsServlet";
                    } else {
                        // Handle error
                        alert("Failed to delete product");
                    }
                }
            };
            xhr.open("GET", "DeleteProductServlet?idproduct=" + productId, true);
            xhr.send();
        }
    }
</script>
</body>
</html>