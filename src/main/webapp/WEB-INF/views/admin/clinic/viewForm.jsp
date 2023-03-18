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
            <h3>Clinic Details : ${clinicForm.name}</h3>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <form:form method="post" modelAttribute="clinicForm" action="/admin/save">

                        <div class="row">
                            <div class="col-md-6 card mb-4">
                                <h5 class="card-header">Clinic information</h5>
                                <div class="card-body">
                                    <label for="name" class="form-label fw-bold mb-0">Clinic Name</label>
                                    <form:input path="name" type="text" id="name" class="form-control-plaintext"/>
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
                                            <label for="address.province.value" class="form-label fw-bold mb-0">Province</label>
                                            <form:input path="address.province.value" type="text" id="province"
                                                        class="form-control-plaintext"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="address.postalCode" class="form-label fw-bold mb-0">Postal Code</label>
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
                            <a href="/admin/clinic/home" class="fw-bold mx-2">Back</a>
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