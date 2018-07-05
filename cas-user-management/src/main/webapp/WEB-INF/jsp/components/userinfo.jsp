<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>userinfo</title>
</head>
<body>
<div>
    <c:set var="user" value="${sessionScope.user_session}"/>
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg" alt="User Image">
        <div>
            <p class="app-sidebar__user-name">${user.realname}</p>
            <p class="app-sidebar__user-designation">Frontend Developer</p>
        </div>
    </div>
</div>
</body>
</html>
