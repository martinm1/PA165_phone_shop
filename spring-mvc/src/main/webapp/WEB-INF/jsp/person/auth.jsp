<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="body">
        <form:form id="loginForm" method="post" action="${pageContext.request.contextPath}/person/auth" modelAttribute="login">
    <table align="center">
        <tr>
            <td>
                <form:label path="email">Email: </form:label>
            </td>
            <td>
                <form:input path="email" name="email" id="mail" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="pass">Password: </form:label>
            </td>
            <td>
                <form:password path="pass" name="pass" id="pass" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td align="left">
                <form:button id="login" name="login">Login</form:button>
            </td>
        </tr>
    </table>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>