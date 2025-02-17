<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
    <h2>Hello World!</h2>
    <form action="${ contextPath }/login" method="post">
        <label for="userId">아이디 : </label>
        <input type="text" name="username" id="userId" required/>

        <br>

        <label for="userPwd">패스워드 : </label>
        <input type="password" name="password" id="userPwd">

        <br>

        <input type="submit" value="로그인">
    </form>
</body>
</html>