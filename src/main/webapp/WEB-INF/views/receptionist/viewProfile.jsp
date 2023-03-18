<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<main id="main" class="main">
    <div class="d-flex justify-content-center flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <h3>User Details : ${userForm.firstName} ${userForm.lastName}</h3>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <form:form method="post" modelAttribute="userForm" action="/receptionist">

                        <div class="row">
                            <div class="col-md-6 card mb-4">
                                <h5 class="card-header">Login information</h5>
                                <div class="card-body">
                                    <label for="username" class="form-label fw-bold mb-0">Username</label>
                                    <form:input path="username" type="text" id="username" class="form-control-plaintext"/>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">General information</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="first_name" class="form-label fw-bold mb-0">First Name</label>
                                            <form:input path="firstName" type="text" id="first_name" class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="last_name" class="form-label fw-bold mb-0">Last Name</label>
                                            <form:input path="lastName" type="text" id="last_name"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="role" class="form-label fw-bold mb-0">Role</label>
                                            <form:input path="role.value" type="text" id="role"
                                                        class="form-control-plaintext"/>
                                        </div>

                                        <c:if test="${userForm.role eq 'VETERINARIAN'}">
                                            <div class="col-md-6 mb-3">
                                                <label for="speciality" class="form-label fw-bold mb-0">Veterinarian Speciality</label>
                                                <form:input path="speciality" type="text" id="speciality"
                                                            class="form-control-plaintext"/>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="clinic" class="form-label fw-bold mb-0">Clinic</label>
                                            <form:input path="clinic.name" type="text" id="clinic"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>


                                    <div class="row align-items-center">
                                        <div class="col-md-6 mb-3">
                                            <label for="dob" class="form-label fw-bold mb-0">Date of Birth</label>
                                            <div class="input-group">
                                                <form:input path="dateOfBirth" type="text" id="dob"
                                                            class="form-control-plaintext"/>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="gender" class="form-label fw-bold mb-0">Gender</label>
                                            <form:input path="gender" type="text" id="gender"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="email" class="form-label fw-bold mb-0">Email</label>
                                            <form:input path="email" type="email" id="email"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="phone" class="form-label fw-bold mb-0">Phone</label>
                                            <form:input path="phoneNumber" type="text" id="phone"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">Location</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-6 mb-3">
                                            <label for="address.street" class="form-label fw-bold mb-0">Address</label>
                                            <form:input path="address.street" type="text" id="street"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-sm-6 mb-3">
                                            <label for="address.addressNo" class="form-label fw-bold mb-0">Number</label>
                                            <form:input path="address.addressNo" type="text" id="addressNo"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6 mb-3">
                                            <label for="address.city" class="form-label fw-bold mb-0">City</label>
                                            <form:input path="address.city" type="text" id="city"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-sm-6 mb-3">
                                            <label for="address.province" class="form-label fw-bold mb-0">Province</label>
                                            <form:input path="address.province.value" type="text" id="province"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="address.postalCode" class="form-label fw-bold mb-0">Postal
                                                Code</label>
                                            <form:input path="address.postalCode" type="text" id="postalcode"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="address.country" class="form-label fw-bold mb-0">Country</label>
                                            <form:input path="address.country" type="text" id="country"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="float-end">
                            <a href="/receptionist/home" class="fw-bold mx-2">Back</a>
                            <a href="/receptionist/edit" class="btn btn-sm btn-gray-800 d-inline-flex align-items-center">Edit</a>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>

    </div>
</main><!-- End #main -->

<%@ include file="fragments/footer.jsp" %>

</body>
</html>