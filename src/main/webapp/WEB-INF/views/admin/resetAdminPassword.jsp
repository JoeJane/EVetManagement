<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:directive.include file = "fragments/header.jsp" />

<main id="main" class="main">
    <div class="container py-4">
        <div class="row justify-content-center form-bg-image">
            <p class="text-center">
                <a href="/admin/home" class="d-flex align-items-center justify-content-center">
                    <i class="fa-solid fa-arrow-left-long"></i>&nbsp; Back</a>
            </p>
            <form:form method="post" action="/admin/resetPassword" modelAttribute="userForm">
                <form:hidden path="userId"/>
                <div class="col-12 d-flex align-items-center justify-content-center">
                    <div class="bg-white shadow border-0 rounded p-4 p-lg-5 w-100 fmxw-500">
                        <h1 class="h3 mb-4">Reset Admin Password</h1>
                        <form action="#">
                            <div class="mb-4">
                                <spring:bind path="username">
                                    <label for="username"
                                           class="form-label fw-bold">Username</label>
                                    <form:input path="username" type="text" id="username"
                                                class="form-control ${status.error ? 'is-invalid' : ''}"
                                                placeholder="Enter user name" required="" disabled="true"
                                                aria-describedby="validationUserNameFeedback"/>
                                    <form:errors path="username" id="validationUserNameFeedback"
                                                 class="invalid-feedback"/>
                                    <form:hidden path="username"/>
                                </spring:bind>
                            </div>
                            <div class="form-group mb-4">
                                <spring:bind path="password">
                                    <label for="password"
                                           class="form-label fw-bold">Password</label>
                                    <form:input path="password" type="password" id="password"
                                                class="form-control ${status.error ? 'is-invalid' : ''}"
                                                placeholder="Enter Password" required=""
                                                aria-describedby="validationPasswordFeedback"/>
                                    <form:errors path="password" id="validationPasswordFeedback"
                                                 class="invalid-feedback"/>
                                </spring:bind>
                            </div>
                            <div class="form-group mb-4">
                                <spring:bind path="confirmPassword">
                                    <label for="confirmPassword"
                                           class="form-label fw-bold">Confirm Password</label>
                                    <form:input path="confirmPassword" type="password" id="confirmPassword"
                                                class="form-control ${status.error ? 'is-invalid' : ''}"
                                                placeholder="Confirm Password" required=""
                                                aria-describedby="validationPasswordFeedback"/>
                                    <form:errors path="confirmPassword" id="validationPasswordFeedback"
                                                 class="invalid-feedback"/>
                                </spring:bind>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-gray-800">Reset password</button>
                            </div>
                        </form>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</main><!-- End #main -->

<script src="/resources/js/bootstrap.bundle.min.js"></script>

</body>
</html>