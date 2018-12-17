<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stevo
  Date: 17.12.2018
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<my:pagetemplate title="Claim View">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Wanted resolution</th>
                <th>Technical report</th>
                <th>Reason of claim</th>
                <th>Claim state</th>
                <th>Order</th>
                <th>Order date</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone number</th>

            </tr>
            </thead>
            <tbody>
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
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>