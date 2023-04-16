<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:directive.include file="../fragments/header.jsp"/>
<main id="main" class="main">
    <div class="d-flex justify-content-center flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <c:choose>
                <c:when test="${userForm['new']}">
                    <h2>Add Pet Owner</h2>
                </c:when>
                <c:otherwise>
                    <h2>Update User</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <form:form method="post" modelAttribute="userForm" action="/receptionist/petowner/save">
                        <form:hidden path="id"/>
                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">General information</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="firstName">
                                                <label for="first_name" class="form-label fw-bold">First Name</label>
                                                <form:input path="firstName" type="text" id="first_name"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            placeholder="Enter your first name" required=""
                                                            aria-describedby="validationFirstNameFeedback"/>
                                                <form:errors path="firstName" id="validationFirstNameFeedback" class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="lastName">
                                                <label for="last_name" class="form-label fw-bold">Last Name</label>
                                                <form:input path="lastName" type="text" id="last_name"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            placeholder="Also your last name" required=""
                                                            aria-describedby="validationLastNameFeedback"/>
                                                <form:errors path="lastName" id="validationLastNameFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>

                                    <div class="row align-items-center">

                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="gender">
                                                <label for="gender" class="form-label fw-bold">Gender</label>
                                                <form:select path="gender" id="gender"
                                                             class="form-select mb-0 ${status.error ? 'is-invalid' : ''}"
                                                             aria-label="Select Gender"
                                                             aria-describedby="validationGenderFeedback">
                                                    <form:option value="" label="- Select Gender -" />
                                                    <form:option value="Female" label="Female"/>
                                                    <form:option value="Male" label="Male"/>
                                                </form:select>
                                                <form:errors path="gender" id="validationGenderFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>

                                    <div class="row align-items-center">
                                        <div class="col-md-6">
                                            <spring:bind path="email">
                                                <label for="email" class="form-label fw-bold">Email</label>
                                                <form:input path="email" type="email" id="email"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="name@centennialcollege.com"
                                                            aria-describedby="validationEmailFeedback"/>
                                                <form:errors path="email" id="validationEmailFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                        <div class="col-md-6">
                                            <spring:bind path="phoneNumber">
                                                <label for="phone" class="form-label fw-bold">Phone</label>
                                                <form:input path="phoneNumber" type="text" id="phone"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="+123-456-78910"
                                                            aria-describedby="validationPhoneFeedback"/>
                                                <form:errors path="phoneNumber" id="validationPhoneFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
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
                                        <div class="col-sm-8 mb-3">
                                            <spring:bind path="address.street">
                                                <label for="address.street" class="form-label fw-bold">Address</label>
                                                <form:input path="address.street" type="text" id="street"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="Enter your home address"
                                                            aria-describedby="validationStreetFeedback"/>
                                                <form:errors path="address.street" id="validationStreetFeedback"
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
                                                             aria-label="- Select Province -"
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
                                                <label for="address.postalCode" class="form-label fw-bold">Postal
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

                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">Pet information</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="pet.name">
                                                <label for="pet_name" class="form-label fw-bold">Pet Name</label>
                                                <form:input path="pet.name" type="text" id="pet_name"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            placeholder="Enter your pet name" required=""
                                                            aria-describedby="validationPetNameFeedback"/>
                                                <form:errors path="pet.name" id="validationPetNameFeedback" class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>

                                    <div class="row align-items-center">
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="pet.type">
                                                <label for="type" class="form-label fw-bold">Pet Type</label>
                                                <form:select path="pet.type" id="type"
                                                             class="form-select mb-0 ${status.error ? 'is-invalid' : ''}"
                                                             aria-label="Select Pet Type"
                                                             aria-describedby="validationTypeFeedback">
                                                    <form:option value="" label="- Select Pet Type -"/>
                                                    <form:options items="${types}"/>
                                                </form:select>
                                                <form:errors path="pet.type" id="validationTypeFeedback" class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="pet.breed">
                                                <label for="breed" class="form-label fw-bold">Breed</label>
                                                <form:input path="pet.breed" type="text" id="breed"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            placeholder="Enter your pet breed" required=""
                                                            aria-describedby="validationBreedFeedback"/>
                                                <form:errors path="pet.breed" id="validationBreedFeedback" class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>

                                    <div class="row align-items-center">
                                        <div class="col-md-6">
                                            <spring:bind path="pet.age">
                                                <label for="age" class="form-label fw-bold">Age</label>
                                                <form:input path="pet.age" type="text" id="age"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="Enter your pet age"
                                                            aria-describedby="validationAgeFeedback"/>
                                                <form:errors path="pet.age" id="validationAgeFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                        <div class="col-md-6">
                                            <spring:bind path="pet.weight">
                                                <label for="weight" class="form-label fw-bold">Weight (KG)</label>
                                                <form:input path="pet.weight" type="text" id="weight"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            required="" placeholder="Enter your pet weight"
                                                            aria-describedby="validationWeightFeedback"/>
                                                <form:errors path="pet.weight" id="validationWeightFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="mt-3 float-end">
                            <c:choose>
                                <c:when test="${userForm['new']}">
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
                            <a href="/receptionist/home" class="fw-bold mx-2">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>

    </div>
</main>
<!-- End #main -->

<%@ include file="../fragments/footer.jsp" %>

<script>
    $(document).ready(function () {
        $(function () {
            $('input#dob').datepicker({
                endDate: "today"
            });
        });
    })
</script>

</body>
</html>