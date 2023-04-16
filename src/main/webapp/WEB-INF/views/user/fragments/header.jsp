<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Home</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/volt.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/tempus-dominus.min.css" />
<body>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">
    <div class="d-flex align-items-center justify-content-between">
        <a href="/receptionist/home" class="logo d-flex align-items-center">
            <img src="/resources/images/logo.png" alt="">
            <span class="d-none d-lg-block">E-Veterinary</span>
        </a>
    </div><!-- End Logo -->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">
            <li class="nav-item dropdown">
                <a class="nav-link nav-icon" href="/user/home">
                    <i class="fa-solid fa-house"></i>
                </a><!-- End Notification Icon -->
            </li><!-- End Notification Nav -->

            <li class="nav-item dropdown pe-3">
                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <div class="rounded-circle avatar d-flex align-items-center justify-content-center fw-bolder bg-secondary">
                        <security:authentication property="principal.icon" />
                    </div>

                    <span class="d-none d-md-block dropdown-toggle ps-2"><security:authentication property="principal.fullName" /></span>
                </a><!-- End Profile Iamge Icon -->

                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6><security:authentication property="principal.fullName" /></h6>
                        <span>(<security:authentication property="principal.role.value" />)</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="/user/profile">
                            <i class="fa-solid fa-user"></i>
                            <span class="ms-2">My Profile</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="/user/resetPassword">
                            <i class="fa-solid fa-unlock"></i>
                            <span  class="ms-2">Change Password</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="/logout">
                            <i class="fa-solid fa-arrow-right-from-bracket"></i>
                            <span  class="ms-2">Sign Out</span>
                        </a>
                    </li>
                </ul><!-- End Profile Dropdown Items -->
            </li><!-- End Profile Nav -->
        </ul>
    </nav><!-- End Icons Navigation -->
</header><!-- End Header -->
