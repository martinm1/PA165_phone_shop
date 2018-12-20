<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="People">
    <jsp:attribute name="body">
        <table class="table">
            <caption>People</caption>
            <thead>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Type</th>
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
                    <td><c:out value="${person.personType}"/></td>

                <%--<td><c:out value="${person.address}"/></td>--%>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.phoneNumber}"/></td>
                    <td><my:localDate date="${person.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                    <td><c:out value="${person.gender}"/></td>

                    <td class="button">
                        <form method="get"
                              action="/pa165/person/newChangePersonType/${person.id}" >
                            <input class="btn btn-warning" type="submit" value="Change type" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <security:authorize access="hasRole('ROLE_ADMIN')">
        <div>
            <form method="get"
                  action="${pageContext.request.contextPath}/person/new/" >
                <input class="btn btn-warning" type="submit" value="Create new person" />
            </form>
        </div>
        </security:authorize>
    </jsp:attribute>
</my:pagetemplate>