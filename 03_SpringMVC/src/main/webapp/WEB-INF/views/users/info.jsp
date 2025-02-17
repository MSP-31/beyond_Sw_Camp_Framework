<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
    <title>회원 정보</title>
</head>
<body>
    <h2>회원 정보</h2>

    <c:if test="${not empty user}">
        사용자 번호 : ${ user.no }
        <br>
        사용자 아이디 : ${user.name}
        <br>
        사용자 주소 : ${user.address}
    </c:if>
    <c:if test="${empty user}">
        회원정보가 존재하지 않습니다.
    </c:if>
</body>
</html>
