<jsp:directive.include file = "fragments/header.jsp" />

<main id="main" class="main">
    <div class="alert position-fixed start-50 alert-success alert-dismissible fade user-msg ${message != null ? 'show' : 'hide'}" role="alert">
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4">
        <div class="d-block mb-4 mb-md-0">
            <h2 class="h4">Users List</h2>
        </div>
        <div class="btn-toolbar mb-3" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-1" role="group" aria-label="First group">
                <a href="/admin/user/add" class="btn btn-sm btn-gray-800 d-inline-flex align-items-center me-3">
                    <i class="fa-solid fa-plus"></i>&nbsp;&nbsp;New User</a>
            </div>

            <div class="btn-group">
                <button class="btn btn-sm btn-gray-800 d-inline-flex align-items-center dropdown-toggle "
                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa-solid fa-chevron-down"></i></i>&nbsp;Clinic <span class="visually-hidden">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu dashboard-dropdown dropdown-menu-start mt-2 py-1">
                    <a class="dropdown-item d-flex align-items-center" href="/admin/clinic/add"><i class="fa-solid fa-plus"></i> Add Clinic </a>
                    <a class="dropdown-item d-flex align-items-center" href="/admin/clinic/home"><i class="fa-solid fa-eye"></i> View Clinics </a>
                </div>
            </div>

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
                    <div class="input-group me-2 me-lg-3 fmxw-200">
                        <form:select path="role" id="role"
                                     class="form-select mb-0">
                            <form:option value="" label="All"/>
                            <form:options items="${roles}"/>
                        </form:select>
                    </div>
                    <form:select class="form-select fmxw-200 d-none d-md-inline" path="status" aria-label="Message select example 2">
                        <form:option value="" label="All"/>
                        <form:option value="1" label="Active"/>
                        <form:option value="2" label="Inactive"/>
                    </form:select>
                    <button type="submit" class="btn btn-sm px-3 btn-secondary ms-3">Search</button>
            </div>
        </div>
        </form:form>
    </div>


    <form:form action="bulkAction" method="post" modelAttribute="bulkAction" >
    <div class="card card-body shadow border-0 table-wrapper table-responsive">
        <div class="d-flex mb-3">
            <form:select class="form-select fmxw-200" path="status" aria-label="Message select example">
                <form:option value="1" label="Activate User"/>
                <form:option value="2" label="Delete User"/>
            </form:select>

            <button type="submit" class="btn btn-sm px-3 btn-secondary ms-3">Apply</button>
        </div>
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
                <th class="border-bottom">User Name</th>
                <th class="border-bottom">Role</th>
                <th class="border-bottom">Gender</th>
                <th class="border-bottom">Date Created</th>
                <th class="border-bottom">Status</th>
                <th class="border-bottom">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <div class="form-check dashboard-check">
                            <form:checkbox class="form-check-input" path="ids" value="${user.userId}" />
                        </div>
                    </td>

                    <td>
                        <a href="#" class="d-flex align-items-center">
                            <div class="avatar d-flex align-items-center justify-content-center fw-bold rounded bg-secondary me-3">
                                <span>${user.icon}</span>
                            </div>
                            <div class="d-block"><span class="fw-bold">${user.firstName} ${user.lastName}</span>
                                <div class="small text-gray">${user.email}</div>
                            </div>
                        </a>
                    </td>
                    <td><span class="fw-normal">${user.username}</span></td>
                    <td><span class="fw-normal">${user.role.value}</span></td>
                    <td><span class="fw-normal">${user.gender}</span></td>
                    <td><span class="fw-normal">
                    <fmt:parseDate value="${user.createdAt}" pattern="yyyy-MM-dd" var="createdAt" type="date"/>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${createdAt}"/></span></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.deleted}">
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
                                <a class="dropdown-item d-flex align-items-center" href="user/view/${user.userId}"><i class="fa-solid fa-eye"></i> View Details </a>
                                <a class="dropdown-item d-flex align-items-center" href="user/edit/${user.userId}"><i class="fa-solid fa-pen-to-square"></i> Edit Details </a>
                                <a class="dropdown-item d-flex align-items-center" href="user/resetPassword/${user.userId}"><i class="fa-solid fa-unlock"></i> Reset Password </a>
                                <c:choose>
                                    <c:when test="${user.deleted}">
                                        <a class="dropdown-item d-flex align-items-center activatebtn" href="#" id="${user.userId}" data-username="${user.username}" data-myid="${user.userId}"><i class="fa-solid fa-user-check text-success"></i> Activate</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="dropdown-item d-flex align-items-center deletebtn" href="#" id="${user.userId}" data-username="${user.username}" data-myid="${user.userId}"><i class="fa-solid fa-user-xmark text-danger suspend"></i> Delete</a>
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
    <div class="modal fade" id="confirm-delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
    </form:form>

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
        window.location.href = "/admin/home";
    });

    $('#userCheck55').change(function (){
        $('input:checkbox').not(this).prop('checked', this.checked);
    });

    $('a.deletebtn, a.activatebtn').click(function (e) {
        var anchor = this;
        $('#confirm-delete #user_details').text('User Name : ' + $(anchor).data('username'))

        var url = '';
        var userid = $(anchor).data('myid');
        var modalcontent = '';

        if($(anchor).hasClass('activatebtn')){
            modalcontent = 'activate';
            url = 'activate/'+userid;
        } else {
            modalcontent = 'delete';

            url = 'delete/'+userid;
        }

        $('#confirm-delete #modal-warn-txt').text('Do you want to '+modalcontent+'?');
        $('#confirm-delete').modal('show');

        $('button#confirm').click(function (e) {
            $.ajax({
                url: url,
                type: "GET",
                dataType: 'json',
                success: function (data, textStatus, xhr) {
                    $('#confirm-delete').modal('hide');
                    $('#confirm-modal').modal('show');
                    $('#confirm-modal #message_box').html("User "+modalcontent+'d!')
                },
                error: function (xhr, textStatus, errorThrown) {
                    $('#confirm-delete').modal('hide');
                    $('#confirm-modal').modal('show');
                    $('#confirm-modal #message_box').html("User "+modalcontent+'failed!')
                }
            });
        });
    });


</script>
</body>
</html>