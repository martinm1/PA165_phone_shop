<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate>
    <jsp:attribute name="body">
        <%--<form:form id="loginForm" method="post" action="${pageContext.request.contextPath}/person/auth" modelAttribute="login">--%>
        <form name="loginForm" method="post" action="auth">
    <table align="center">
        <tr>
            <td>
                Email:
            </td>
            <td>
                <input type="text" name="email" id="mail" />
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

        <c:if test="${not empty msg}">
             <div class="alert alert-danger" align="center">
                 <c:out value="${msg}"/>
             </div>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>