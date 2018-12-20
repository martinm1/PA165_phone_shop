<%--
  Created by IntelliJ IDEA.
  User: stevo
  Date: 20.12.2018
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate title="Change person type">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/person/addPersonType"
                   modelAttribute="person"  cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="personType" cssClass="col-sm-2 control-label">Person type:</form:label>
                <div class="col-sm-10">
                    <form:select path="personType" cssClass="form-control">
                        <c:forEach items="${personTypeR}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="personType" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10">
                    <form:hidden path="id" cssClass="form-control" value="${id}"/>
                    <form:errors path="id" cssClass="help-block"/>
                </div>
            </div>


        <button class="btn btn-primary" type="submit">Change</button>
        </form:form>

        <c:if test="${not empty chyba}">
            <div class="alert alert-warning" align="center">Can not remove only admin</div>

        </c:if>
    </jsp:attribute>
</my:pagetemplate>