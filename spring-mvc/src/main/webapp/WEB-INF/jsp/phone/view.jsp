<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 17/12/2018
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Phone View">
    <jsp:attribute name="body">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Model Name</th>
                <th>Price</th>
                <th>Technical Info</th>
                <th>Manufacturer</th>
                <th>Stock</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${phone.id}</td>
                <td><c:out value="${phone.modelName}"/></td>
                <td><c:out value="${phone.price}"/></td>
                <td><c:out value="${phone.technicalInfo}"/></td>
                <td><c:out value="${phone.manufacturer}"/></td>
                <td><c:out value="${phone.stock.name}"/></td>
            </tr>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>
