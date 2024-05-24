<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Product</title>
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
    /* Form styling */
    .form-group {
        margin-bottom: 20px;
    }
    .form-control {
        border-radius: 20px;
    }
    .form-control:focus {
        box-shadow: none;
    }
    .btn-primary {
        border-radius: 20px;
    }
    .btn-primary:hover {
        background-color: #0069d9;
    }
    .error-message {
        color: #dc3545;
        font-size: 14px;
        margin-top: 5px;
        display: block;
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
  <div class="container mt-5">
    <h2>Add Product</h2>
<form action="AddSupplierProductsServlet" method="post" id="addProductForm" onsubmit="return validateForm()">
  <div class="form-group">
    <label for="name">Name:</label>
    <input type="text" class="form-control" id="name" name="name" required>
    <span id="nameError" class="error-message"></span>
  </div>
  <div class="form-group">
    <label for="details">Details:</label>
    <textarea class="form-control" id="details" rows="3" name="details" required></textarea>
    <span id="detailsError" class="error-message"></span>
  </div>
  <div class="form-group">
    <label for="price">Price:</label>
    <input type="number" class="form-control" id="price" name="price" required>
    <span id="priceError" class="error-message"></span>
  </div>
  <div class="form-group">
    <label for="stock">Stock Level:</label>
    <input type="number" class="form-control" id="stock" name="stock" required>
    <span id="stockError" class="error-message"></span>
  </div>
  <button type="submit" class="btn btn-primary">Add Product</button>
</form>

  </div>
  <script>
  // Function to validate the form
  function validateForm() {
    // Get form inputs
    var name = document.getElementById('name').value.trim();
    var details = document.getElementById('details').value.trim();
    var price = document.getElementById('price').value.trim();
    var stock = document.getElementById('stock').value.trim();

    // Initialize flag for validation
    var isValid = true;

    // Validation for Name field
    if (name === '') {
      isValid = false;
      document.getElementById('nameError').innerText = 'Please enter a name.';
    } else {
      document.getElementById('nameError').innerText = '';
    }

    // Validation for Details field
    if (details === '') {
      isValid = false;
      document.getElementById('detailsError').innerText = 'Please enter details.';
    } else {
      document.getElementById('detailsError').innerText = '';
    }

    // Validation for Price field
    if (price === '' || isNaN(price)) {
      isValid = false;
      document.getElementById('priceError').innerText = 'Please enter a valid price.';
    } else {
      document.getElementById('priceError').innerText = '';
    }

    // Validation for Stock Level field
    if (stock === '' || isNaN(stock)) {
      isValid = false;
      document.getElementById('stockError').innerText = 'Please enter a valid stock level.';
    } else {
      document.getElementById('stockError').innerText = '';
    }

    return isValid;
  }
</script>
  

</body>
</html>
