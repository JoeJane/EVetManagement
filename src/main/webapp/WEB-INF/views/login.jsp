<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Home</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/volt.min.css" rel="stylesheet">

    <link href="/resources/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">

<body>

<main>
    <section class="vh-lg-100 mt-5 mt-lg-0 bg-soft d-flex align-items-center">
        <div class="container" style="max-width: 2000px">
            <div class="row justify-content-center form-bg-image"
                 data-background-lg="/resources/images/signin.png"
                 style="background: url(/resources/images/signin.png); height: 100%; background-size: contain; background-position: center">
                <div class="d-flex align-items-center justify-content-center">
                    <div class="bg-white shadow border-0 rounded border-light p-4 p-lg-5 w-100 fmxw-500">
                        <div class="text-center text-md-center mb-2 mt-md-0"><h1 class="mb-0 h3">Sign in to E-Patient</h1></div>

                        <span class="text-center text-md-center mb-4 mt-md-0 alert alert-success ${message != null ? 'show' : 'hide'}" role="alert">
                            ${message}
                        </span>
                        <span class="text-center text-md-center mb-4 mt-md-0 alert alert-danger ${error != null ? 'show' : 'hide'}" role="alert">
                            ${error}
                        </span>

                        <form action="login" method="post" class="mt-4">
                            <div class="form-group mb-4"><label for="username">User Name</label>
                                <div class="input-group"><span class="input-group-text" id="basic-addon1"><i class="fa-solid fa-envelope fa-lg"></i> </span><input
                                        type="text" class="form-control" placeholder="User Name" id="username" name="username"
                                        autofocus="" required=""></div>
                            </div>
                            <div class="form-group">
                                <div class="form-group mb-4"><label for="password">Your Password</label>
                                    <div class="input-group"><span class="input-group-text" id="basic-addon2"><i class="fa-solid fa-lock fa-lg"></i> </span><input
                                            type="password" placeholder="Password" class="form-control" id="password" name="password"
                                            required=""></div>
                                </div>

                            </div>



                            <div class="d-grid">
                                <button type="submit" class="btn btn-gray-800">Sign in</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="/resources/js/bootstrap.bundle.min.js"></script>

</body>
</html>