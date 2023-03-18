<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:directive.include file="fragments/header.jsp"/>
<link href="/resources/css/select2.min.css" rel="stylesheet">
<main id="main" class="main">
    <div class="d-flex justify-content-center flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <h2>New Booking</h2>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <div class="row">
                        <div class="card mb-4 col-md-12">
                            <h5 class="card-header">Owner information</h5>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="first_name" class="form-label fw-bold mb-0">First Name</label>
                                        <form:input path="owner.firstName" type="text" id="first_name" class="form-control-plaintext"/>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name" class="form-label fw-bold mb-0">Last Name</label>
                                        <form:input path="owner.lastName" type="text" id="last_name" class="form-control-plaintext"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="email" class="form-label fw-bold mb-0">Email</label>
                                        <form:input path="owner.email" type="email" id="email" class="form-control-plaintext"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="phone" class="form-label fw-bold mb-0">Phone</label>
                                        <form:input path="owner.phoneNumber" type="text" id="phone" class="form-control-plaintext"/>
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
                                        <label for="pet_name" class="form-label fw-bold mb-0">Pet Name ${pet.id}</label>
                                        <form:input path="pet.name" type="text" id="pet_name"
                                                    class="form-control-plaintext"/>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="type" class="form-label fw-bold mb-0">Pet Type</label>
                                        <form:input path="pet.type.value" type="text" id="type"
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

                    <form:form method="post" modelAttribute="bookingForm" action="/receptionist/appointment/book/save/${pet.id}">
                        <form:hidden path="pet" value="${pet.id}"/>
                        <div class="row">
                            <div class="card mb-4 col-md-12">
                                <h5 class="card-header">Booking information</h5>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="appointmentDate">
                                                <label for="appointmentDate" class="form-label fw-bold">Booking Date</label>
                                                <div class="input-group">
                                                    <span class="input-group-text"><i class="fa-regular fa-calendar-days"></i> </span>
                                                    <form:input path="appointmentDate" type="text" id="appointmentDate"
                                                                class="form-control ${status.error ? 'is-invalid' : ''}"
                                                                required="" placeholder="dd/MM/yyyy"
                                                                aria-describedby="validationBDFeedback"/>
                                                    <form:errors path="appointmentDate" id="validationBDFeedback"
                                                                 cssClass="invalid-feedback-force-display"/>
                                                </div>
                                            </spring:bind>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="appointmentTime">
                                                <label for="appointmentTime" class="form-label fw-bold">Booking Time</label>
                                                <div class="input-group">
                                                    <span class="input-group-text"><i class="fa-regular fa-calendar-days"></i> </span>
                                                    <form:input path="appointmentTime" type="text" id="appointmentTime"
                                                                class="form-control datepicker-input ${status.error ? 'is-invalid' : ''}"
                                                                required="" placeholder="HH:mm"
                                                                aria-describedby="validationATFeedback"/>
                                                    <form:errors path="appointmentTime" id="validationATFeedback"
                                                                 cssClass="invalid-feedback-force-display"/>
                                                </div>
                                            </spring:bind>
                                        </div>
                                    </div>

                                    <div class="row align-items-center">
                                        <div class="col-md-12 mb-3">
                                            <spring:bind path="reason">
                                                <label for="reason" class="form-label fw-bold">Reason</label>
                                                <form:textarea path="reason" type="text" id="reason"
                                                            class="form-control ${status.error ? 'is-invalid' : ''}"
                                                            placeholder="Enter the reason" required=""
                                                            aria-describedby="validationReasonFeedback"/>
                                                <form:errors path="reason" id="validationReasonFeedback"
                                                             class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>

                                    <div class="row align-items-center">
                                        <div class="col-md-6 mb-3">
                                            <spring:bind path="veterinarian">
                                                <label for="veterinarian" class="form-label fw-bold">Doctor</label>
                                                <form:select path="veterinarian" id="veterinarian"
                                                             class="form-select mb-0 ${status.error ? 'is-invalid' : ''}"
                                                             aria-label="Enter the veterinarian name"
                                                             aria-describedby="validationVeterinarianFeedback">
                                                    <form:option value="" label="Province"/>
                                                </form:select>
                                                <form:errors path="veterinarian" id="validationDoctorFeedback" class="invalid-feedback"/>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="mt-3 float-end">
                            <button class="btn btn-sm btn-gray-800 d-inline-flex align-items-center" type="submit">Add
                            </button>
                            <a href="/receptionist/home" class="fw-bold mx-2">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>

    </div>
</main>
<!-- End #main -->

<%@ include file="fragments/footer.jsp" %>
<script src="/resources/js/select2.min.js"></script>

<script>
    $(document).ready(function () {
        $("#role").change(function() {
            if(this.value == 'VETERINARIAN'){
                $( "#vet_speciality" ).addClass('show').removeClass('hide');
            } else if(this.value == 'LAB_ASSISTANT') {
                $( "#vet_speciality" ).addClass('hide').removeClass('show');
            } else if(this.value == 'RECEPTIONIST') {
                $( "#vet_speciality" ).addClass('hide').removeClass('show');
            }
        });


            const currentDate = new Date();

            $('input#appointmentDate').tempusDominus({
                display: {
                    viewMode: 'calendar',
                    components: {
                        decades: true,
                        year: true,
                        month: true,
                        date: true,
                        hours: false,
                        minutes: false,
                        seconds: false
                    }
                },
                restrictions: {
                    daysOfWeekDisabled: [0],
                    minDate: new Date(),
                    maxDate: new Date(currentDate.getFullYear(), currentDate.getMonth() + 3, currentDate.getDate())
                },
                localization: {
                    format: 'dd/MM/yyyy',
                }
            });

            $('input#appointmentTime').tempusDominus({
                display: {
                    viewMode: 'clock',
                    components: {
                        decades: false,
                        year: false,
                        month: false,
                        date: false,
                        hours: true,
                        minutes: true,
                        seconds: false
                    }
                },
                restrictions: {
                    minDate: new Date(),
                },
                localization: {
                    format: 'HH:mm',
                },
            });
        });

    $('#veterinarian').select2({
        selectionCssClass: 'form-control form-select mb-0 ',
        placeholder: "Select a Veterinarian",
        ajax: {
            url: '/receptionist/veterinary/options',
            dataType: 'json',
            delay: 250,
            data: function(params) {
                return {
                    veterinaryName: params.term // Search term entered by the user
                };
            },
            processResults: function(data) {
                return {
                    results: data
                };
            }
        }
    });

</script>

</body>
</html>