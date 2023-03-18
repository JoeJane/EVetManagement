<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp"/>

<main id="main" class="main">
    <div class="d-flex justify-content-center flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <h3>Pet Owner Details : ${userForm.firstName} ${userForm.lastName}</h3>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <form:form method="post" modelAttribute="userForm" action="/receptionist/save">

                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">General information</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="first_name" class="form-label fw-bold mb-0">First Name</label>
                                            <form:input path="firstName" type="text" id="first_name"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="last_name" class="form-label fw-bold mb-0">Last Name</label>
                                            <form:input path="lastName" type="text" id="last_name"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>

                                    <div class="row">
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
                                            <form:input path="address.street" type="text" id="address"
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
                                            <form:input path="address.province" type="text" id="province"
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


                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">Pet information</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="pet_name" class="form-label fw-bold mb-0">Pet Name</label>
                                            <form:input path="pet.name" type="text" id="pet_name"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="type" class="form-label fw-bold mb-0">Pet Type</label>
                                            <form:input path="pet.type" type="text" id="type"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="breed" class="form-label fw-bold mb-0">Breed</label>
                                            <form:input path="pet.breed" type="text" id="breed"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="age" class="form-label fw-bold mb-0">Age</label>
                                            <form:input path="pet.age" type="text" id="age"
                                                        class="form-control-plaintext"/>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="weight" class="form-label fw-bold mb-0">Weight</label>
                                            <form:input path="pet.weight" type="text" id="weight"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">Past Booking Details</h5>
                                <div class="card-body table-wrapper table-responsive">
                                    <table class="table user-table table-hover align-items-center">
                                        <thead>
                                        <tr>
                                            <th class="border-bottom">#</th>
                                            <th class="border-bottom">Appointment Dt</th>
                                            <th class="border-bottom">Status</th>
                                            <th class="border-bottom">Veterinarian</th>
                                            <th class="border-bottom">Report</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="booking" items="${bookings}" varStatus="loop">
                                            <c:choose>
                                                <c:when test="${booking.status != 'NEW'}">
                                                    <tr>
                                                        <td>${loop.index}</td>
                                                        <td><fmt:parseDate value="${booking.appointmentDate}" pattern="yyyy-MM-dd" var="appointmentAt" type="date"/>
                                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${appointmentAt}"/> : ${booking.appointmentTime}
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${booking == null}">
                                                                    <span class="fw-normal text-danger">Inactive</span>
                                                                </c:when>
                                                                <c:when test="${booking.status == 'NEW'}">
                                                                    <span class="fw-normal text-primary">Pending</span>
                                                                </c:when>
                                                                <c:when test="${booking.status == 'COMPLETED'}">
                                                                    <span class="fw-normal text-success">Completed</span>
                                                                </c:when>
                                                                <c:when test="${booking.status == 'CANCELED'}">
                                                                    <span class="fw-normal text-warning">Cancled</span>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td>${booking.veterinarian.fullName}</td>

                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${booking.status == 'COMPLETED'}">
                                                                    <a class="align-items-center"
                                                                       href="/nurse/patient/view-prescription/${booking.id}"
                                                                       title="View Prescription"><i
                                                                            class="fa-solid fa-capsules"></i></a>
                                                                </c:when>
                                                            </c:choose>

                                                        </td>
                                                    </tr>

                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="float-end">
                            <a href="/labassistant/home" class="fw-bold mx-2"><< Back</a>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>

    </div>
</main><!-- End #main -->

<%@ include file="../fragments/footer.jsp" %>

</body>
</html>