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

<my:pagetemplate title="New Address">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/address/create"
                   modelAttribute="addressCreate" cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="streetName" cssClass="col-sm-2 control-label">Street name</form:label>
                <div class="col-sm-10">
                    <form:input path="streetName" cssClass="form-control"/>
                    <form:errors path="streetName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="streetNumber" cssClass="col-sm-2 control-label">Street number</form:label>
                <div class="col-sm-10">
                    <form:input path="streetNumber" cssClass="form-control"/>
                    <form:errors path="streetNumber" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="country" cssClass="col-sm-2 control-label">country</form:label>
                <div class="col-sm-10">
                    <form:input path="country" cssClass="form-control"/>
                    <form:errors path="country" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group>">
                <form:label path="city" cssClass="col-sm-2 control-label">City</form:label>
                <div class="col-sm-10">
                    <form:input path="city" cssClass="form-control"/>
                    <form:errors path="city" cssClass="form-block"/>
                </div>
            </div>

            <%--TODO--%>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>