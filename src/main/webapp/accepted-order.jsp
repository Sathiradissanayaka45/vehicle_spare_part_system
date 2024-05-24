<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Payment" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Orders</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">
    <style>
        /* Custom Styles */
        .container {
            margin-top: 20px;
        }

        th, td {
            vertical-align: middle !important;
        }

        .btn-group {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Pending Orders</h2>
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Payment ID</th>
                    <th>Customer ID</th>
                    <th>Full Name</th>
                    <th>Mobile Number</th>
                    <th>Address</th>
                    <th>Postal Code</th>
                    <th>Status</th> <!-- Ensure the Status column is here -->
                    <th>Actions</th> <!-- New column for actions -->
                </tr>
            </thead>
            <tbody>
                <% 
                List<Model.Payment> paymentList = (List<Model.Payment>) request.getAttribute("payments");
                if (paymentList != null) {
                    for (Model.Payment payment : paymentList) {
                %>
                <tr>
                    <td><%= payment.getPaymentId() %></td>
                    <td><%= payment.getCustomerId() %></td>
                    <td><%= payment.getFullName() %></td>
                    <td><%= payment.getMobileNumber() %></td>
                    <td><%= payment.getAddress() %></td>
                    <td><%= payment.getPostalCode() %></td>
                    <td><%= payment.getStatus() %></td>
                    <td>
                        <!-- Button Group for Actions -->
                        <div class="btn-group" role="group" aria-label="Payment Actions">
                            <!-- Update Button -->
                            <a href="UpdatePaymentServlet?paymentId=<%= payment.getPaymentId() %>" class="btn btn-warning btn-sm">Update</a>
                            <!-- Delete Button -->
                            <a href="#" onclick="confirmDelete(<%= payment.getPaymentId() %>)" class="btn btn-danger btn-sm">Delete</a>
                        </div>
                    </td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="text-center">No pending orders found.</td>
                </tr>
                <% 
                }
                %>
            </tbody>
        </table>
    </div>
    <script>
    function confirmDelete(paymentId) {
        if (confirm("Are you sure you want to delete this payment?")) {
            // Prevent default link action
            event.preventDefault();

            // AJAX request to delete the payment
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Call GetPaymentsServlet to update payment list
                        window.location.href = "GetPaymentsServlet";
                    } else {
                        // Handle error
                        alert("Failed to delete payment");
                    }
                }
            };
            xhr.open("GET", "DeletePaymentServlet?idpayment=" + paymentId, true);
            xhr.send();
        }
    }
    </script>
</body>
</html>
