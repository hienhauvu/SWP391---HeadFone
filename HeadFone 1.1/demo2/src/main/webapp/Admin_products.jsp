<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="">
        <meta name="keywords" content="au theme template">
        <title>Online Headphone Shop</title>
        <!-- Fontfaces CSS-->
        <link href="css/font-face.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">
        
        <style>
            .confirm-notice {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #f9f9f9;
                padding: 20px;
                                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

            }

            .confirm-notice p {
                margin: 0;
                margin-bottom: 10px;
            }

            .confirm-buttons {
                text-align: right;
            }

            .confirm-buttons button {
                margin-left: 10px;
            }
            
            .highlighted {
                 color: red !important;
                font-weight: bold;
            }

        </style>
    </head>
    <body>
        <%@include file="components/Dashboard.jsp" %>
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- DATA TABLE -->
                            <h3 class="title-5 m-b-35">manage products</h3>
                            <p style="color: red">${mess}</p>
                            <div class="table-data__tool">
                                <div class="table-data__tool-right">
                                    <button class="au-btn au-btn-icon au-btn--green au-btn--small" onclick="window.location.href = 'add-product'">
                                        <i class="zmdi zmdi-plus"></i>add item</button>
                                    <div class="rs-select2--dark rs-select2--sm rs-select2--dark2">
                                        
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2">
                                    <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>name</th>
                                            <th>quantity</th>
                                            <th>price</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="ls" items="${listPro}" >
                                            <tr class="tr-shadow">
                                                <td>${ls.id}</td>
                                                <td>${ls.name}</td>
                                                <td>${ls.quantity}</td>
                                                <td>${ls.price}</td>
                                                
                                                <td  class="action-cell">
                                                    <div class="table-data-feature">
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit" onclick="window.location.href = 'editproduct?id=${ls.id}'">
                                                            <i class="zmdi zmdi-edit"></i>
                                                        </button>
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete" onclick="showConfirmation('${ls.id}', '${ls.name}')">
                                                            <i class="zmdi zmdi-delete"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="spacer"></tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <!-- END DATA TABLE -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="confirmation-notice" class="confirm-notice" style="display: none;  z-index: 9999;">
            <p>Are you sure you want to delete the following product?</p>
            <p><strong>Product ID:</strong> <span id="product-id"></span></p>
            <p><strong>Product Name:</strong> <span id="product-name"></span></p>
            <div class="confirm-buttons">
                <button onclick="deleteProduct()">Yes</button>
                <button onclick="cancelDelete()">Cancel</button>
            </div>
        </div>

        <script>
            var productIdToDelete;

            // Show confirmation dialog
            function showConfirmation(id, name) {
                productIdToDelete = id;
                document.getElementById('product-id').textContent = id;
                document.getElementById('product-name').textContent = name;
                document.getElementById('confirmation-notice').style.display = 'block';
            }

            // Delete product
            function deleteProduct() {
                // Redirect to the delete action
                window.location.href = 'delete-product?id=' + productIdToDelete;
            }

            // Cancel delete
            function cancelDelete() {
                document.getElementById('confirmation-notice').style.display = 'none';
            }
        </script>

        <script>
            window.onload = function()
            {
                sessionStorage.removeItem('mess');
            };
        </script>
        
    </script>
    <!-- Jquery JS-->
    <script src="vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="vendor/slick/slick.min.js">
    </script>
    <script src="vendor/wow/wow.min.js"></script>
    <script src="vendor/animsition/animsition.min.js"></script>
    <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
    </script>
    <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="vendor/counter-up/jquery.counterup.min.js">
    </script>
    <script src="vendor/circle-progress/circle-progress.min.js"></script>
    <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="vendor/select2/select2.min.js">
    </script>

    <!-- Main JS-->
    <script src="js/main2.js"></script>
</body>
</html>


