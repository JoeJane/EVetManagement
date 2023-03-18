<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="admin/fragments/header.jsp" />
<body>

<div class="container">

    <code>
        <h2>Exception: ${exception.message}.</h2>
        <c:forEach items="${exception.stackTrace}" var="stackTrace">
            ${stackTrace}
        </c:forEach>
    </code>

</div>

<jsp:include page="admin/fragments/footer.jsp" />

</body>
</html>