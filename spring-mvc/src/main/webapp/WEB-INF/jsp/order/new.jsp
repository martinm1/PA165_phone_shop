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


<my:pagetemplate title="New order">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/order/create"
                   modelAttribute="orderCreate" cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="orderDate" cssClass="col-sm-2 control-label">Order date</form:label>
                <div class="col-sm-10">
                    <form:input path="orderDate" cssClass="form-control"/>
                    <form:errors path="orderDate" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="person" cssClass="col-sm-2 control-label">Person</form:label>
                <div class="col-sm-10">
                    <form:input path="person" cssClass="form-control"/>
                    <form:errors path="person" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="phone" cssClass="col-sm-2 control-label">Phone</form:label>
                <div class="col-sm-10">
                    <form:input path="phone" cssClass="form-control"/>
                    <form:errors path="phone" cssClass="form-block"/>
                </div>
            </div>



        <button class="btn btn-primary" type="submit">Create order</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
