<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:directive.include file="../fragments/header.jsp"/>
<main id="main" class="main">
    <div class="d-flex justify-content-center flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <c:choose>
                <c:when test="${clinicForm['new']}">
                    <h2>Add Clinic</h2>
                </c:when>
                <c:otherwise>
                    <h2>Update Clinic</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <form:form method="post" modelAttribute="clinicForm" action="/admin/clinic/save">
                        <form:hidden path="id"/>
                        <c:choose>
                            <c:when test="${clinicForm['new']}">
                                <div class="row">
                                    <div class="card mb-4 col-md-12">
                                        <h5 class="card-header">Clinic information</h5>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <spring:bind path="name">
                                                        <label for="name"
                                                               class="form-label fw-bold">Clinic Name</label>
                                                        <form:input path="name" type="text" id="name"
                                                                    class="form-control ${status.error ? 'is-invalid' : ''}"
                                                                    placeholder="Enter user name" required=""
                                                                    aria-describedby="validationNameFeedback"/>
                                                        <form:errors path="name" id="validationNameFeedback"
                                                                     class="invalid-feedback"/>
                                                    </spring:bind>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="card mb-4 col-md-6">
                                        <h5 class="card-header">Clinic information</h5>
                                        <div class="card-body">
                                            <label for="name" class="form-label fw-bold">Clinic Name</label>
                                            <form:input path="name" type="text" id="name" disabled="true"
                                                        class="form-control ${status.error ? 'is-invalid' : ''}"
                                                        placeholder="Enter user name" required=""
                                                        aria-describedby="validationNameFeedback"/>
                                            <form:hidden path="name"/>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">Location</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-8 mb-3">
                                            <spring:bind path="address.street">
                                                <label for="address.street" class="form-label fw-bold">Address</label>
                                                <form:input path="address.street" type="text" id="streeet"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="Enter your home address"
                                                            aria-describedby="validationAddresseFeedback"/>
                                                <form:errors path="address.street" id="validationAddresseFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                        <div class="col-sm-4 mb-3">
                                            <spring:bind path="address.addressNo">
                                                <label for="address.addressNo" class="form-label fw-bold">Number</label>
                                                <form:input path="address.addressNo" type="text" id="addressNo"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="No."
                                                            aria-describedby="validationAddressNoFeedback"/>
                                                <form:errors path="address.addressNo" id="validationAddressNoFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-sm-4 mb-3">
                                            <spring:bind path="address.city">
                                                <label for="address.city" class="form-label fw-bold">City</label>
                                                <form:input path="address.city" type="text" id="city"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="City"
                                                            aria-describedby="validationCityFeedback"/>
                                                <form:errors path="address.city" id="validationCityFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                        <div class="col-sm-4 mb-3">
                                            <spring:bind path="address.province">
                                                <label for="address.province" class="form-label fw-bold">Province</label>
                                                <form:select path="address.province" id="province"
                                                             class="form-select mb-0 ${status.error ? 'is-invalid' : ''}"
                                                             aria-label="Select Province"
                                                             aria-describedby="validationProvinceFeedback">
                                                    <form:option value="" label="- Select Province -"/>
                                                    <form:options items="${provinces}"/>
                                                </form:select>
                                                <form:errors path="address.province" id="validationProvinceFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>

                                        <div class="col-sm-4">
                                            <spring:bind path="address.postalCode">
                                                <label for="postalcode" class="form-label fw-bold">Postal
                                                    Code</label>
                                                <form:input path="address.postalCode" type="text" id="postalcode"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="Postal Code"
                                                            aria-describedby="validationPostalcodeFeedback"/>
                                                <form:errors path="address.postalCode" id="validationPostalcodeFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4 mb-3">
                                            <spring:bind path="address.country">
                                                <label for="address.country" class="form-label fw-bold">Country</label>
                                                <form:select path="address.country" id="country"
                                                             class="form-select mb-0 ${status.error ? 'is-invalid' : ''}"
                                                             aria-label="Select Country"
                                                             aria-describedby="validationCountryFeedback">
                                                    <form:option value="" label="- Select Country -"/>
                                                    <form:options items="${countries}"/>
                                                </form:select>

                                                <form:errors path="address.country" id="validationCountryFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="mt-3 float-end">
                            <c:choose>
                                <c:when test="${clinicForm['new']}">
                                    <button class="btn btn-sm btn-gray-800 d-inline-flex align-items-center"
                                            type="submit">Add
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-sm btn-gray-800 d-inline-flex align-items-center"
                                            type="submit">Update
                                    </button>
                                </c:otherwise>
                            </c:choose>
                            <a href="/admin/clinic/home" class="fw-bold mx-2">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>

    </div>
</main>
<!-- End #main -->

<%@ include file="../fragments/footer.jsp" %>

</body>
</html>