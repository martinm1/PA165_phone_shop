<%--
  Created by IntelliJ IDEA.
  User: stevo
  Date: 18.12.2018
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate title="New claim">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/claim/addReport/${id}"
                   modelAttribute="claim"  cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="technicalReport" cssClass="col-sm-2 control-label">Technical report:</form:label>
                <div class="col-sm-10">
                    <form:input path="technicalReport" cssClass="form-control"/>
                    <form:errors path="technicalReport" cssClass="help-block"/>
                </div>
            </div>


        <button class="btn btn-primary" type="submit">Add report</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>