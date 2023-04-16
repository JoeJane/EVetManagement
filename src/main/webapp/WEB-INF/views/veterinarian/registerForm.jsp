<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:directive.include file="./fragments/header.jsp"/>
<main id="main" class="main">
    <div class="d-flex justify-content-center flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <h2>Veterinarian's Prescription</h2>
        </div>
    </div>

    <div class="container">
        <div class="row align-items-center">
            <div class="col">
                <div class="mb-4 pt-4">
                    <div class="row">
                        <div class="card mb-4 col-md-12">
                            <h5 class="card-header">General information</h5>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="first_name" class="form-label fw-bold mb-0">First Name</label>
                                        <form:input path="userForm.firstName" type="text" id="first_name"
                                                    class="form-control-plaintext"/>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name" class="form-label fw-bold mb-0">Last Name</label>
                                        <form:input path="userForm.lastName" type="text" id="last_name"
                                                    class="form-control-plaintext"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="gender" class="form-label fw-bold mb-0">Gender</label>
                                        <form:input path="userForm.gender" type="text" id="gender"
                                                    class="form-control-plaintext"/>
                                    </div>

                                </div>


                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="email" class="form-label fw-bold mb-0">Email</label>
                                        <form:input path="userForm.email" type="email" id="email"
                                                    class="form-control-plaintext"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="phone" class="form-label fw-bold mb-0">Phone</label>
                                        <form:input path="userForm.phoneNumber" type="text" id="phone"
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
                                        <form:input path="userForm.pet.name" type="text" id="pet_name"
                                                    class="form-control-plaintext"/>
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="type" class="form-label fw-bold mb-0">Pet Type</label>
                                        <form:input path="userForm.pet.type" type="text" id="type"
                                                    class="form-control-plaintext"/>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="breed" class="form-label fw-bold mb-0">Breed</label>
                                        <form:input path="userForm.pet.breed" type="text" id="breed"
                                                    class="form-control-plaintext"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="age" class="form-label fw-bold mb-0">Age</label>
                                        <form:input path="userForm.pet.age" type="text" id="age"
                                                    class="form-control-plaintext"/>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="weight" class="form-label fw-bold mb-0">Weight</label>
                                        <form:input path="userForm.pet.weight" type="text" id="weight"
                                                    class="form-control-plaintext"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${appointment != null}">


                            <div class="row">
                                <div class="card mb-4 col-md-12">
                                    <h5 class="card-header">Appointment Information</h5>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label for="type" class="form-label fw-bold mb-0">Appointment
                                                    Date</label>
                                                <div class="form-control-plaintext">
                                                    <fmt:parseDate value="${appointment.appointmentDate}"
                                                                   pattern="yyyy-MM-dd" var="appointmentDate"
                                                                   type="date"/>
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${appointmentDate}"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-3">
                                                <label for="breed" class="form-label fw-bold mb-0">Appointment
                                                    Time</label>
                                                <form:input path="appointment.appointmentTime" type="text"
                                                            id="appointmentTime" class="form-control-plaintext"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label for="reason" class="form-label fw-bold mb-0">Reason</label>
                                                <form:input path="appointment.reason" type="text" id="reason"
                                                            class="form-control-plaintext"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label for="age" class="form-label fw-bold mb-0">Appointment
                                                    Status</label>
                                                <c:choose>
                                                    <c:when test="${appointment.status == null}">
                                                        <div class="form-control-plaintext text-danger">Inactive</div>
                                                    </c:when>
                                                    <c:when test="${appointment.status == 'NEW'}">
                                                        <div class="form-control-plaintext text-info">Pending</div>
                                                    </c:when>
                                                    <c:when test="${appointment.status == 'COMPLETED'}">
                                                        <div class="form-control-plaintext text-success">Completed</div>
                                                    </c:when>
                                                    <c:when test="${appointment.status == 'CANCELED'}">
                                                        <div class="form-control-plaintext text-warning">Cancled</div>
                                                    </c:when>
                                                </c:choose>
                                            </div>

                                        </div>
                                        <c:choose>
                                            <c:when test="${appointment.report != null}">
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label for="reason" class="form-label fw-bold mb-0">Report File
                                                            Name</label>

                                                        <div class="form-control-plaintext text-primary"
                                                             id="report-file-name">${appointment.report.fileName}</div>


                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="row">
                        <div class="card mb-4 col-md-12">
                            <h5 class="card-header">Write Prescription</h5>
                            <div class="card-body">

                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="gender" class="form-label fw-bold mb-0">Veterinarian Name:</label>
                                        <form:input path="appointment.veterinarian.fullName" type="text" id="gender"
                                                    class="form-control-plaintext"/>
                                    </div>

                                </div>

                                <form:form method="post" modelAttribute="diagnosis" action="/veterinarian/savePrescription/${appointment.id}">
                                <div class="row">
                                    <div class="col-md-12 mb-3">
                                        <spring:bind path="description">
                                            <label for="description" class="form-label fw-bold">Diagnoses:</label>
                                            <form:textarea path="description" type="text" id="description"
                                                        class="form-control ${status.error ? 'is-invalid' : ''}"
                                                        placeholder="Enter diagnosis details" required=""
                                                        aria-describedby="validationdescriptionFeedback"/>
                                            <form:errors path="description" id="validationdescriptionFeedback"
                                                         class="invalid-feedback"/>
                                        </spring:bind>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 mb-3">
                                        <spring:bind path="prescription.prescriptions">
                                            <label for="prescription.prescriptions" class="form-label fw-bold">Prescription:</label>
                                            <form:textarea path="prescription.prescriptions" type="text" id="prescriptions"
                                                        class="form-control ${status.error ? 'is-invalid' : ''}"
                                                        placeholder="Enter prescription" required=""
                                                        aria-describedby="validationprescriptionsFeedback"/>
                                            <form:errors path="prescription.prescriptions" id="validationprescriptionsFeedback"
                                                         class="invalid-feedback"/>
                                        </spring:bind>
                                    </div>
                                </div>


                                    <div class="mt-3 float-end">
                                        <button class="btn btn-sm btn-gray-800 d-inline-flex align-items-center"
                                                type="submit">Mark Complete
                                        </button>
                                        <a href="/veterinarian/home" class="fw-bold mx-2">Cancel</a>
                                    </div>
                                </form:form>


                            </div>
                        </div>
                    </div>





                </div>

            </div>

        </div>

    </div>
</main>
<!-- End #main -->

<%@ include file="./fragments/footer.jsp" %>

<script>
    $(document).ready(function () {
        const reportFileUploadInput = document.querySelector('#reportFileUploadInput');
        const reportFileUploadError = document.querySelector('#reportFileUploadError');

        function uploadSingleFile(file, appointmentId) {

            const ajaxUrl = "/labassistant/uploadreport/" + appointmentId;

            const file_data = $('#reportFileUploadInput').prop('files')[0];
            const form_data = new FormData();
            form_data.append('file', file_data);

            $.ajax({
                url: ajaxUrl,
                type: "POST",
                data: form_data,
                contentType: false,
                processData: false,
                success: function (res) {
                    $('#reportFileUploadSuccess').text("File upload successfully!")
                    $('#report-file-name').text(res)
                    $("#reportFileUploadSuccess").attr("style", "display:block")
                    const myTimeout = setTimeout(clearMessages, 5000);
                    $('#reportFileUploadInput').val('');
                }, fail: function (res) {
                    $('#reportFileUploadError').text("File upload failed!")
                    $("#reportFileUploadError").attr("style", "display:block")
                    const myTimeout = setTimeout(clearMessages, 5000);
                }
            });


        }

        function clearMessages() {
            $('#reportFileUploadSuccess').text("");
            $('#reportFileUploadError').text("");
            $("#reportFileUploadSuccess").attr("style", "display:none")
            $("#reportFileUploadError").attr("style", "display:none")
        }

        $("#reportUploadForm").submit(function (event) {
            event.preventDefault();

            const files = reportFileUploadInput.files;
            const appointmentId = $('#appointmentId').val();

            uploadSingleFile(files[0], appointmentId);

        });

    })
</script>

</body>
</html>