<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Product" %>
<!DOCTYPE html>
<html lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Accepted Orders By Supplier</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
  <style>
    /* Add your custom styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
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
<div class="container">
    <h2 class="text-center mb-4">Update Product</h2>
    <form action="UpdateProductServlet" method="post">
        <div class="form-group">
            <label for="image">Current Image:</label><br>
            <img src="<%= (request.getAttribute("product") != null) ? ((Product)request.getAttribute("product")).getImagePath() : "" %>" alt="Product Image" style="max-width: 200px;">
        </div>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= (request.getAttribute("product") != null) ? ((Product)request.getAttribute("product")).getName() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="details">Details:</label>
            <textarea class="form-control" id="details" rows="3" name="details" required><%= (request.getAttribute("product") != null) ? ((Product)request.getAttribute("product")).getDetails() : "" %></textarea>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" name="price" value="<%= (request.getAttribute("product") != null) ? ((Product)request.getAttribute("product")).getPrice() : "" %>" required>
        </div>
        <div class="form-group">
            <label for="stock">Stock Level:</label>
            <input type="number" class="form-control" id="stock" name="stock" value="<%= (request.getAttribute("product") != null) ? ((Product)request.getAttribute("product")).getStock() : "" %>" required>
        </div>
        <input type="hidden" name="idproduct" value="<%= (request.getAttribute("product") != null) ? ((Product)request.getAttribute("product")).getId() : "" %>">
        <button type="submit" class="btn btn-primary btn-block">Update Product</button>
    </form>
</div>
</body>
</html>