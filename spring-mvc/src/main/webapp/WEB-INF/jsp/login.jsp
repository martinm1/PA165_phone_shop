<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="body">
        <%--<form:form id="loginForm" method="post" action="${pageContext.request.contextPath}/person/auth" modelAttribute="login">--%>
        <form name="f" th:th:action="@{/login}" method="post" action="login">
    <table align="center">
        <tr>
            <td>
                Email:
            </td>
            <td>
                <input type="text" name="username" />
            </td>
        </tr>
        <tr>
            <td>
                Password:
            </td>
            <td>
                <input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td align="left">
                <button type="submit" class="btn btn-primary" style="margin-top: 10px;">
                    <span class="glyphicon glyphicon-ok"></span>
                    Login
                </button>
            </td>
        </tr>
    </table>
    </form>

        <c:if test="${param.error ne null}">
            <div class="alert alert-danger" align="center">Wrong email or password!</div>
        </c:if>

        <c:if test="${param.logout ne null}">
            <div class="alert alert-success" align="center">Logout successful</div>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>