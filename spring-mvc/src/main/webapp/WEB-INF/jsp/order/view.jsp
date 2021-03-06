<%--
  Created by IntelliJ IDEA.
  User: stevo
  Date: 17.12.2018
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Order view">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
            <tr>
                <th>ID:</th>
                <th>Order state:</th>
                <th>Order date:</th>
                <th>First name:</th>
                <th>Last name:</th>
                <th>Phone number:</th>
                <th>Email:</th>
                <th>Ordered item:</th>
                <th>Manufacturer:</th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${order.id}</td>
                <td><c:out value="${order.state}"/></td>
                <td><c:out value="${order.orderDate}"/></td>
                <td><c:out value="${order.person.firstName}"/></td>
                <td><c:out value="${order.person.lastName}"/></td>
                <td><c:out value="${order.person.phoneNumber}"/></td>
                <td><c:out value="${order.person.email}"/></td>
                <td><c:out value="${order.phone.modelName}"/></td>
                <td><c:out value="${order.phone.manufacturer}"/></td>

            </tr>
            </tbody>
        </table>

       <c:if test="${not empty order.claims}">
            <div class="row">
                <div class="col-xs-6">
                    <table class="table">
                        <caption>Claims</caption>
                        <thead>
                        <tr>
                            <th>ID:</th>
                            <th>Wanted resolution:</th>
                            <th>Technical report:</th>
                            <th>Reason of claim:</th>
                            <th>Claim state:</th>
                            <th>Order:</th>
                            <th>Order date:</th>
                            <th>First name:</th>
                            <th>Last name:</th>
                            <th>Phone number:</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.claims}" var="claim">
                            <tr>
                                <td>${claim.id}</td>
                                <td><c:out value="${claim.wantedSolutionByCustomer}"/></td>
                                <td><c:out value="${claim.technicalReport}"/></td>
                                <td><c:out value="${claim.reasonOfClaim}"/></td>
                                <td><c:out value="${claim.claimState}"/></td>
                                <td><c:out value="${claim.order.phone.modelName}"/></td>
                                <td><c:out value="${claim.order.orderDate}"/></td>
                                <td><c:out value="${claim.order.person.firstName}"/></td>
                                <td><c:out value="${claim.order.person.lastName}"/></td>
                                <td><c:out value="${claim.order.person.phoneNumber}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
    </jsp:attribute>
</my:pagetemplate>