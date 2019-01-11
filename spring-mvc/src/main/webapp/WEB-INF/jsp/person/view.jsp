<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:pagetemplate title="User View">
    <jsp:attribute name="body">
        <div class="jumbotron" id="jumbo">
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


        <div class="jumbotron" id="jumbo">
        <c:if test="${not empty person.orders}">
            <div class="row">
                <div class="col-xs-6">
                    <table class="table">
                        <caption>Orders</caption>
                        <thead>
                        <tr>
                            <th>Manufacturer</th>
                            <th>Model</th>
                            <th>Date</th>
                            <th>State</th>
                            <th>Number of claims</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${person.orders}" var="order">
                            <tr>
                                <td><c:out value="${order.phone.manufacturer}"/></td>
                                <td><c:out value="${order.phone.modelName}"/></td>
                                <td><my:localDate date="${order.orderDate}" pattern="dd-MM-yyyy"/></td>
                                <td><c:out value="${order.state}"/></td>
                                <td>${fn:length(order.claims)}</td>

                                <c:if test="${fn:length(order.claims) lt 3}">
                                    <td class="button">
                                        <form method="get"
                                              action="/pa165/claim/new/${order.id}" >
                                            <input class="btn btn-warning" type="submit" value="New claim" />
                                        </form>
                                    </td>
                                </c:if>
                                <td class="button">
                                    <form method="get"
                                          action="/pa165/order/view/${order.id}" >
                                        <input class="btn btn-warning" type="submit" value="View order" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        </div>
    </jsp:attribute>
</my:pagetemplate>