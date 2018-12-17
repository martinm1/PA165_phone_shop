<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="User View">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Phone number</th>
                <th>Date of birth</th>
                <th>Gender</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${person.id}</td>
                <td><c:out value="${person.firstName}"/></td>
                <td><c:out value="${person.lastName}"/></td>
                <%--<td><c:out value="${person.address}"/></td>--%>
                <td><c:out value="${person.email}"/></td>
                <td><c:out value="${person.phoneNumber}"/></td>
                <td><my:localDate date="${person.dateOfBirth}" pattern="dd-MM-yyyy"/></td>
                <td><c:out value="${person.gender}"/></td>
            </tr>
            </tbody>
        </table>

        <div class="row">
            <div class="col-xs-6">
                <table class="table">
                    <caption>Orders</caption>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>State</th>
                        <th>Date</th>
                        <th>Manufacturer</th>
                        <th>Model</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${person.orders}" var="order">
                        <tr>
                            <td><c:out value="${order.id}"/></td>
                            <td><c:out value="${order.state}"/></td>
                            <td><my:localDate date="${order.orderDate}" pattern="dd-MM-yyyy"/></td>
                            <td><c:out value="${order.phone.manufacturer}"/></td>
                            <td><c:out value="${order.phone.modelName}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:pagetemplate>