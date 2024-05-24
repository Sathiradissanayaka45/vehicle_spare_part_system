<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
  <!-- Custom CSS -->
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
    #content {
      margin-left: 250px;
      padding: 20px;
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

  <!-- Page Content -->
  <div id="content">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <button type="button" id="sidebarCollapse" class="btn btn-dark">
        <i class="fas fa-bars"></i>
      </button>
    </nav>

    <!-- Main Content -->
    <div class="container-fluid">
      <h2>Dashboard</h2>
      <!-- Add your dashboard content here -->
      <div class="row">
        <div class="col-md-6">
          <!-- Example Chart -->
          <div class="card">
            <div class="card-body">
              <canvas id="myChart" width="400" height="200"></canvas>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <!-- Example Table -->
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Recent Orders</h5>
              <table class="table">
                <thead>
                  <tr>
                    <th>Order ID</th>
                    <th>Customer</th>
                    <th>Date</th>
                    <th>Total</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>John Doe</td>
                    <td>2024-05-08</td>
                    <td>$100.00</td>
                  </tr>
                  <!-- More rows -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS and jQuery -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <!-- Font Awesome JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
  <!-- Custom Script -->
  <script>
    // Toggle sidebar
    $(document).ready(function () {
      $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
      });
    });
  </script>
</body>
</html>
