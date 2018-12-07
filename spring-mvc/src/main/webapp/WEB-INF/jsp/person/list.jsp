<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="People">
    <jsp:attribute name="body">
        <table class="table">
            <caption>People</caption>
            <thead>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Email</th>
                <th>Phone number</th>
                <th>Date of birth</th>
                <th>Gender</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${people}" var = "person">
                <tr>
                    <td>${person.id}</td>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                    <td><c:out value="${person.address}"/></td>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.phoneNumber}"/></td>
                    <td><fmt:formatDate value="${user.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                    <td><c:out value="${person.gender}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>