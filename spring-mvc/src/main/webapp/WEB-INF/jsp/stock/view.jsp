<%--
  Created by IntelliJ IDEA.
  User: stevo
  Date: 17.12.2018
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
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
                <th>Name</th>
                <th>Street name</th>
                <th>Street number</th>
                <th>City</th>
                <th>Country</th>
            </tr>
            </thead>
            <tbody>
            <tr>

                <td>${stock.id}</td>
                <td><c:out value="${stock.name}"/></td>
                <td><c:out value="${stock.address.streetName}"/></td>
                <td><c:out value="${stock.address.streetNumber}"/></td>
                <td><c:out value="${stock.address.city}"/></td>
                <td><c:out value="${stock.address.country}"/></td>
            </tr>
            </tbody>
        </table>

        <div class="row">
            <div class="col-xs-6">
                <table class="table">
                    <caption>Phones</caption>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Model name</th>
                        <th>Manufacturer</th>
                        <th>Specifications</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${stock.phones}" var="phone">
                        <tr>
                            <td><c:out value="${phone.id}"/></td>
                            <td><c:out value="${phone.modelName}"/></td>
                            <td><c:out value="${phone.manufacturer}"/></td>
                            <td><c:out value="${phone.technicalInfo}"/></td>
                            <td><c:out value="${phone.price}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:pagetemplate>
