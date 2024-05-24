<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Payment" %>
<!DOCTYPE html>
<html>
<head>
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
        .btn-accept,
        .btn-reject {
            padding: 6px 12px;
            font-size: 14px;
        }
        .btn-accept {
            background-color: #28a745;
            color: #fff;
            border: none;
        }
        .btn-reject {
            background-color: #dc3545;
            color: #fff;
            border: none;
        }
        .btn-accept:hover,
        .btn-reject:hover {
            opacity: 0.8;
        }
        /* Animation */
        @keyframes slideInFromTop {
            0% {
                transform: translateY(-100%);
            }
            100% {
                transform: translateY(0);
            }
        }
        .animated-row {
            animation: slideInFromTop 0.5s ease-out;
        }
    </style>
</head>
<body>
<!-- Sidebar -->
<div id="sidebar">
    <h2 class="text-white">Admin Dashboard</h2>
    <ul class="nav flex-column">
        <!-- Your sidebar menu items -->
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
    <h2 class="mb-4">Received Orders</h2>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
            <tr>
                <th>Payment ID</th>
                <th>Customer ID</th>
                <th>Full Name</th>
                <th>Mobile Number</th>
                <th>Address</th>
                <th>Postal Code</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% 
            List<Payment> paymentList = (List<Payment>) request.getAttribute("payments");
            if (paymentList != null) {
                int index = 0;
                for (Payment payment : paymentList) {
            %>
            <tr class="animated-row">
                <td><%= payment.getPaymentId() %></td>
                <td><%= payment.getCustomerId() %></td>
                <td><%= payment.getFullName() %></td>
                <td><%= payment.getMobileNumber() %></td>
                <td><%= payment.getAddress() %></td>
                <td><%= payment.getPostalCode() %></td>
                <td><%= payment.getStatus() %></td>
                <td>
                    <form action="ProcessOrderServlet" method="post" style="display:inline;">
                        <input type="hidden" name="paymentId" value="<%= payment.getPaymentId() %>">
                        <input type="hidden" name="action" value="accept">
                        <button type="submit" class="btn btn-accept">Accept</button>
                    </form>
                    <form action="ProcessOrderServlet" method="post" style="display:inline;">
                        <input type="hidden" name="paymentId" value="<%= payment.getPaymentId() %>">
                        <input type="hidden" name="action" value="reject">
                        <button type="submit" class="btn btn-reject">Reject</button>
                    </form>
                </td>
            </tr>
            <% 
                index++;
                }
            } else {
            %>
            <tr>
                <td colspan="8" class="text-center">No received orders found.</td>
            </tr>
            <% 
            }
            %>
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
