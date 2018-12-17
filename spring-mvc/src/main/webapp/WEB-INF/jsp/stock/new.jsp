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



<my:pagetemplate title="New stock">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/stock/create"
                   modelAttribute="stockCreate" cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="order" cssClass="col-sm-2 control-label">Address</form:label>
                <div class="col-sm-10">
                    <form:input path="address" cssClass="form-control"/>
                    <form:errors path="address" cssClass="form-block"/>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Create product</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
