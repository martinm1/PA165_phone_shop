<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 17/12/2018
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Pgone View">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Street Name</th>
                <th>Street Number</th>
                <th>Country</th>
                <th>City</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${address.id}</td>
                <td><c:out value="${address.streetName}"/></td>
                <td><c:out value="${address.streetNumber4}"/></td>
                <td><c:out value="${address.country}"/></td>
                <td><c:out value="${address.city}"/></td>
            </tr>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>
