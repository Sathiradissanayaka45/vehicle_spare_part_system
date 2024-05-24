<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.SupplierProduct" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Product</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
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
        .container {
            margin-left: 250px;
            padding: 20px;
        }
        h2 {
            font-weight: bold;
            margin-top: 0;
        }
        h2.update-product {
            color: #000; /* black color */
        }
        h2.supplier-dashboard {
            color: #fff; /* white color */
        }
        label {
            font-weight: bold;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }
        input[type="text"]:focus,
        input[type="number"]:focus,
        textarea:focus {
            border: 2px solid #007bff;
        }
        button {
            background-color: #007bff; /* blue color */
            color: #fff;
            padding: 14px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #0056b3; /* darker shade of blue */
        }
        button.btn-primary:hover {
    background-color: #0056b3; /* darker shade of blue */
}
        
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h2 class="supplier-dashboard">Supplier Dashboard</h2>
        <a href="supplier-dashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
        <a href="DisplaySupplierProductsServlet"><i class="fas fa-box"></i> View Products</a>
        <a href="add-supplier-product.jsp"><i class="fas fa-plus"></i> Add Product</a>
        <a href="DisplaySupplierRequestsServlet?page=supplier"><i class="fas fa-file-invoice-dollar"></i> View Pending Orders</a>
        <a href="DisplaySupplierRequestsServlet?page=orders"><i class="fas fa-list-alt"></i> View Accepted Orders</a>
        <a href="DisplaySupplierRequestsServlet?page=rejected"><i class="fas fa-ban"></i> View Rejected Orders</a>
        <!-- Logout Button -->
        <a href="login.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </div>

    <div class="container mt-5">
        <h2 class="update-product">Update Product</h2>
        <form action="UpdateSupplierProductServlet" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= (request.getAttribute("product") != null) ? ((SupplierProduct)request.getAttribute("product")).getName() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="details">Details:</label>
                <textarea class="form-control" id="details" rows="3" name="details" required><%= (request.getAttribute("product") != null) ? ((SupplierProduct)request.getAttribute("product")).getDetails() : "" %></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" class="form-control" id="price" name="price" value="<%= (request.getAttribute("product") != null) ? ((SupplierProduct)request.getAttribute("product")).getPrice() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="stock">Stock Level:</label>
                <input type="number" class="form-control" id="stock" name="stock" value="<%= (request.getAttribute("product") != null) ? ((SupplierProduct)request.getAttribute("product")).getStock() : "" %>" required>
            </div>
            <input type="hidden" name="idproduct" value="<%= (request.getAttribute("product") != null) ? ((SupplierProduct)request.getAttribute("product")).getId() : "" %>">
<button type="submit" class="btn btn-primary">Update Product</button>

        </form>
    </div>
</body>
</html>
