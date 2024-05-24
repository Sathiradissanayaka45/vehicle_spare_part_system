<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Success</title>
    <!-- Include Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Include your custom CSS if any -->
    <link href="css/global.css" rel="stylesheet">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa;
        }

        .success-message {
            text-align: center;
            margin-top: 100px;
        }

        .animated-message {
            font-size: 32px;
            font-weight: bold;
            color: #28a745;
            animation: fadein 3s;
            -moz-animation: fadein 3s; /* Firefox */
            -webkit-animation: fadein 3s; /* Safari and Chrome */
            -o-animation: fadein 3s; /* Opera */
        }

        @keyframes fadein {
            from { opacity: 0; }
            to   { opacity: 1; }
        }

        /* Firefox */
        @-moz-keyframes fadein {
            from { opacity: 0; }
            to   { opacity: 1; }
        }

        /* Safari and Chrome */
        @-webkit-keyframes fadein {
            from { opacity: 0; }
            to   { opacity: 1; }
        }

        /* Opera */
        @-o-keyframes fadein {
            from { opacity: 0; }
            to   { opacity: 1; }
        }

        .back-home-btn {
            margin-top: 30px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-message">
            <div class="animated-message">Payment Successful!</div>
            <!-- Bootstrap toast message -->
            <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-delay="5000">
                <div class="toast-header">
                    <strong class="mr-auto">Payment Success</strong>
                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="toast-body">
                    Your payment has been successfully processed. Thank you for shopping with us!
                </div>
            </div>
            <!-- Button to navigate back to the home page -->
            <div class="back-home-btn">
                <a href="index.jsp" class="btn btn-primary">Back to Home</a>
            </div>
        </div>
    </div>

    <!-- Include Bootstrap JS -->
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script>
        // Show toast message on page load
        $(document).ready(function(){
            $('.toast').toast('show');
        });
    </script>
</body>
</html>
