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
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<my:pagetemplate title="New order">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/order/create"
                   modelAttribute="orderCreate" cssClass="form-horizontal">

            <%--<div class="form-group">--%>
                <%--<div class="col-sm-10">--%>
                    <%--<my:localDate date=""--%>
                    <%--<form:hidden path="orderDate" cssClass="form-control" value="${orderCreate.orderDate}"/>--%>
                    <%--<form:errors path="orderDate" cssClass="help-block"/>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="form-group">
                <div class="col-sm-10">

                    <form:hidden path="person" cssClass="form-control" value="${personId}"/>
                    <form:errors path="person" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10">
                    <form:hidden path="state" cssClass="form-control" value="${orderCreate.state}"/>
                    <form:errors path="state" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10">
                    <form:hidden path="phone" cssClass="form-control" value="${phone.id}"/>
                    <form:errors path="phone" cssClass="form-block"/>
                </div>
            </div>

            <table class="table">
                <thead>
                <tr>
                    <th>Manufacturer</th>
                    <th>Model Name</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${phone.manufacturer}"/></td>
                    <td><c:out value="${phone.modelName}"/></td>
                    <td><c:out value="${phone.price}"/></td>
                </tr>
                </tbody>
            </table>
           <button class="btn btn-primary" type="submit">Create order</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
