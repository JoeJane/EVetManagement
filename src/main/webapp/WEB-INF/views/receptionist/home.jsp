<jsp:directive.include file = "fragments/header.jsp" />

<main id="main" class="main">
    <div class="alert position-fixed start-50 alert-success alert-dismissible fade user-msg ${message != null ? 'show' : 'hide'}" role="alert">
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <h2 class="h4">Pet Owner List</h2>
            </div>
        <div class="btn-toolbar mb-2 mb-md-0">
            <a href="/receptionist/petowner/add" class="btn btn-sm btn-gray-800 d-inline-flex align-items-center">
                <i class="fa-solid fa-plus"></i>&nbsp;&nbsp;New Owner</a>
        </div>
    </div>

    <div class="table-settings mb-4">
        <form:form action="search" method="post" modelAttribute="searchterm">
        <div class="row justify-content-between align-items-center">
            <div class="col-9 col-lg-8 d-md-flex">
                    <div class="input-group me-2 me-lg-3 fmxw-300">
                        <span class="input-group-text">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </span>
                        <form:input type="text" path="value" class="form-control" placeholder="Search users" />
                    </div>

                    <button type="submit" class="btn btn-sm px-3 btn-secondary ms-3">Search</button>
            </div>
        </div>
        </form:form>
    </div>


    <div class="card card-body shadow border-0 table-wrapper table-responsive">
        <table class="table user-table table-hover align-items-center">
            <thead>
            <tr>
                <th class="border-bottom">
                    <div class="form-check dashboard-check">
                        <input class="form-check-input" type="checkbox" value="" id="userCheck55">
                        <label class="form-check-label" for="userCheck55"></label>
                    </div>
                </th>
                <th class="border-bottom">Name</th>
                <th class="border-bottom">Pet Name</th>
                <th class="border-bottom">Breed</th>
                <th class="border-bottom">Appointment Status</th>
                <th class="border-bottom">Date Created</th>
                <th class="border-bottom">Status</th>
                <th class="border-bottom">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pet" items="${pets}" varStatus="counter">
                <tr>
                    <td>
                        <div class="form-check dashboard-check">
                            <c:out value="${counter.count}" />
                        </div>
                    </td>

                    <td>
                        <a href="#" class="d-flex align-items-center">
                            <div class="avatar d-flex align-items-center justify-content-center fw-bold rounded bg-secondary me-3">
                                <span>${pet.owner.icon}</span>
                            </div>
                            <div class="d-block"><span class="fw-bold">${pet.owner.firstName} ${pet.owner.lastName}</span>
                                <div class="small text-gray">${pet.owner.email}</div>
                            </div>
                        </a>
                    </td>
                    <td><span class="fw-normal">${pet.name}</span></td>
                    <td><span class="fw-normal">${pet.breed} (${pet.type.value})</span></td>
                    <td>
                        <c:choose>
                            <c:when test="${pet.latestAppointment == null}">
                                <span class="fw-normal text-danger">Inactive</span>
                            </c:when>
                            <c:when test="${pet.latestAppointment.status == 'NEW'}">
                                <span class="fw-normal text-info">Pending</span>
                            </c:when>
                            <c:when test="${pet.latestAppointment.status == 'COMPLETED'}">
                                <span class="fw-normal text-success">Completed</span>
                            </c:when>
                            <c:when test="${pet.latestAppointment.status == 'CANCELED'}">
                                <span class="fw-normal text-warning">Cancled</span>
                            </c:when>
                        </c:choose>
                    <td><span class="fw-normal">
                    <fmt:parseDate value="${pet.createdAt}" pattern="yyyy-MM-dd" var="createdAt" type="date"/>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${createdAt}"/></span></td>
                    <td>
                        <c:choose>
                            <c:when test="${pet.deleted}">
                                <span class="fw-normal text-danger">Inactive</span>
                            </c:when>
                            <c:otherwise>
                                <span class="fw-normal text-success">Active</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <div class="btn-group">
                            <button class="btn btn-link text-dark dropdown-toggle dropdown-toggle-split m-0 p-0"
                                    data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa-solid fa-ellipsis fa-lg"></i> <span
                                    class="visually-hidden">Toggle Dropdown</span>
                            </button>
                            <div class="dropdown-menu dashboard-dropdown dropdown-menu-start mt-2 py-1">
                                <a class="dropdown-item d-flex align-items-center" href="petowner/view/${pet.owner.userId}"><i class="fa-solid fa-eye"></i> View Details </a>
                                <a class="dropdown-item d-flex align-items-center" href="petowner/edit/${pet.owner.userId}"><i class="fa-solid fa-pen-to-square"></i> Edit Details </a>




                                <c:choose>
                                    <c:when test="${not pet.deleted}">
                                        <c:choose>
                                            <c:when test="${pet.latestAppointment == null || (pet.latestAppointment.status == 'COMPLETED' || pet.latestAppointment.status == 'CANCELED')}">
                                                <a class="dropdown-item d-flex align-items-center" href="appointment/book/${pet.id}"><i class="fa-solid fa-pen-to-square"></i> Book Appointment </a>
                                            </c:when>
                                            <c:when test="${pet.latestAppointment.status == 'NEW' }">
                                                <a class="dropdown-item d-flex align-items-center cancelbtn" href="#" id="c_${pet.id}" data-petname="${pet.name}" data-myid="${pet.id}"><i class="fa-solid fa-pen-to-square"></i> Cancel Appointment </a>
                                            </c:when>
                                            <c:otherwise>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${pet.deleted}">
                                        <a class="dropdown-item d-flex align-items-center activatebtn" href="#" id="a_${pet.owner.userId}" data-petname="${pet.name}" data-myid="${pet.owner.userId}"><i class="fa-solid fa-user-check text-success"></i> Activate</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="dropdown-item d-flex align-items-center deletebtn" href="#" id="d_${pet.owner.userId}" data-petname="${pet.name}" data-myid="${pet.owner.userId}"><i class="fa-solid fa-user-xmark text-danger suspend"></i> Delete</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <div class="card-footer px-3 border-0 d-flex flex-column flex-lg-row align-items-center justify-content-between">
            <nav aria-label="Page navigation example">
                <ul class="pagination mb-0">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>
            <div class="fw-normal small mt-4 mt-lg-0">Showing <b>5</b> out of <b>25</b> entries</div>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="confirm-warn" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-confirm modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header justify-content-center warning">
                    <div class="icon-box">
                        <i class="fa-solid fa-question"></i>
                    </div>
                    <button type="button" class="close btn btn-light" aria-label="Close" data-dismiss="modal"
                            aria-hidden="true">x
                    </button>
                </div>


                <div class="modal-body text-center">
                    <h4 id="modal-warn-txt">Do you want to delete?</h4>
                    <span id="user_details" class="fw-bold"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" id="confirm" class="btn btn-secondary">Yes</button>
                    <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="confirm-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-confirm modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header justify-content-center">
                    <div class="icon-box">
                        <i class="fa-solid fa-check"></i>
                    </div>
                    <button type="button" class="close btn btn-light" aria-label="Close" data-dismiss="modal"
                            aria-hidden="true">x
                    </button>
                </div>


                <div class="modal-body text-center">
                    <h4 id="message_box"></h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal" id="close-btn">Close</button>
                </div>
            </div>
        </div>
    </div>

</main><!-- End #main -->

<script src="/resources/js/bootstrap.bundle.min.js"></script>
<script src="/resources/js/jquery-3.6.1.min.js"></script>

<script>
    setTimeout(function(){
        $(".alert").alert('close')
    }, 5000);


    $('#close-btn').click(function (){
        // location.reload();
        window.location.href = "/receptionist/home";
    });

    $('#userCheck55').change(function (){
        $('input:checkbox').not(this).prop('checked', this.checked);
    });

    

    $('a.deletebtn, a.activatebtn, a.cancelbtn, a.completebtn').click(function (e) {
        let anchor = this;
        $('#confirm-warn #user_details').text('Pet Name : ' + $(anchor).data('petname'))

        let url = '';
        let myid = $(anchor).data('myid');
        let modalcontent = '';
        let modalsuccesscontent = '';

        if($(anchor).hasClass('activatebtn')){
            modalcontent = 'activate';
            modalsuccesscontent = 'User activated';
            url = 'petowner/activate/'+myid;
        } else if($(anchor).hasClass('deletebtn')){
            modalcontent = 'delete';
            modalsuccesscontent = 'User deleted';
            url = 'petowner/delete/'+myid;
        } else if($(anchor).hasClass('cancelbtn')){
            modalcontent = 'cancel booking';
            modalsuccesscontent = 'Booking canceled';
            url = 'appointment/cancel/'+myid;
        } else if($(anchor).hasClass('completebtn')){
            modalcontent = 'complete booking';
            modalsuccesscontent = 'Booking completed';
            url = 'appointment/complete/'+myid;
        }

        $('#confirm-warn #modal-warn-txt').text('Do you want to '+modalcontent+'?');
        $('#confirm-warn').modal('show');

        $('button#confirm').click(function (e) {
            $.ajax({
                url: url,
                type: "GET",
                dataType: 'json',
                success: function (data, textStatus, xhr) {
                    $('#confirm-warn').modal('hide');
                    $('#confirm-modal').modal('show');
                    $('#confirm-modal #message_box').html(modalsuccesscontent+'!')
                },
                error: function (xhr, textStatus, errorThrown) {
                    $('#confirm-warn').modal('hide');
                    $('#confirm-modal').modal('show');
                    $('#confirm-modal #message_box').html(modalsuccesscontent+' failed!')
                }
            });
        });
    });

</script>
</body>
</html>