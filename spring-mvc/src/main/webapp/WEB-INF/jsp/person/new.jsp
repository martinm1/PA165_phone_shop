<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New person">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/person/create"
                   modelAttribute="personCreate" cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="firstName" cssClass="col-sm-2 control-label">First name: </form:label>
                <div class="col-sm-10">
                    <form:input path="firstName" cssClass="form-control"/>
                    <form:errors path="firstName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="lastName" cssClass="col-sm-2 control-label">Last name: </form:label>
                <div class="col-sm-10">
                    <form:input path="lastName" cssClass="form-control"/>
                    <form:errors path="lastName" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="email" cssClass="col-sm-2 control-label">Email: </form:label>
                <div class="col-sm-10">
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="phoneNumber" cssClass="col-sm-2 control-label">Phone number: </form:label>
                <div class="col-sm-10">
                    <form:input path="phoneNumber" cssClass="form-control"/>
                    <form:errors path="phoneNumber" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="gender" cssClass="col-sm-2 control-label">Gender: </form:label>
                <div class="col-sm-10">
                    <form:select path="gender" cssClass="form-control">
                        <c:forEach items="${genders}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <form:label path="password" cssClass="col-sm-2 control-label">Password: </form:label>
                <div class="col-sm-10">
                    <form:password path="password" cssClass="form-control"/>
                    <form:errors path="password" cssClass="form-block"/>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Register user</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>