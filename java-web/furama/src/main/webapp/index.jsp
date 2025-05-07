<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Furama Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
        }

        .navbar-custom {
            background-color: #0d6efd;
        }

        .navbar-custom .nav-link,
        .navbar-custom .navbar-brand,
        .navbar-custom .form-control {
            color: white !important;
        }

        .navbar-custom .nav-link:hover {
            text-decoration: underline;
        }

        .sidebar {
            min-height: 500px;
            background-color: #ffffff;
            border-right: 1px solid #ddd;
            padding-top: 1rem;
        }

        .sidebar ul {
            list-style-type: none;
            padding-left: 0;
        }

        .sidebar li {
            padding: 10px;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .sidebar li:hover {
            background-color: #f0f0f0;
        }

        .main-content {
            padding: 2rem;
            background-color: #ffffff;
            min-height: 500px;
        }

        .footer {
            background-color: #0d6efd;
            color: white;
            text-align: center;
            padding: 10px;
            margin-top: 2rem;
        }
    </style>
</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" alt="Logo" width="30" height="30">
        </a>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/employee">Employee</a></li>
            <li class="nav-item"><a class="nav-link" href="/customer">Customer</a></li>
            <li class="nav-item"><a class="nav-link" href="/facility">Service</a></li>
            <li class="nav-item"><a class="nav-link" href="/contract">Contract</a></li>
        </ul>
        <form class="d-flex me-2">
            <input class="form-control me-2" type="search" placeholder="Search">
            <button class="btn btn-light" type="submit">Search</button>
        </form>
        <span class="navbar-text">Nguyen Van A</span>
    </div>
</nav>

<!-- Main content layout -->
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <ul>
                <li>Item One</li>
                <li>Item Two</li>
                <li>Item Three</li>
            </ul>
        </div>

        <!-- Body -->
        <div class="col-md-10 main-content">
            <h3>Welcome to Furama Resort Management System</h3>
            <p>This is the homepage. Use the navigation bar to explore different sections.</p>
        </div>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>Furama Resort &copy; 2025</p>
</div>

</body>
</html>
