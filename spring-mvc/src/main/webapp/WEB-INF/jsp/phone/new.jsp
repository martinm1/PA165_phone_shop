<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 17/12/2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New phone">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/phone/create"
                   modelAttribute="phoneCreate" cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="modelName" cssClass="col-sm-2 control-label">Model name</form:label>
                <div class="col-sm-10">
                    <form:input path="modelName" cssClass="form-control"/>
                    <form:errors path="modelName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                <div class="col-sm-10">
                    <form:input path="price" cssClass="form-control"/>
                    <form:errors path="price" cssClass="form-block"/>
                </div>
            </div>

            <div class="form-group">
                <form:label path="technicalInfo" cssClass="col-sm-2 control-label">Technical Info</form:label>
                <div class="col-sm-10">
                    <form:input path="technicalInfo" cssClass="form-control"/>
                    <form:errors path="technicalInfo" cssClass="form-block"/>
                </div>
            </div>

         <div class="form-group">
             <form:label path="manufacturer" cssClass="col-sm-2 control-label">Manufacturer: </form:label>
             <div class="col-sm-10">
                    <form:select path="manufacturer" cssClass="form-control">
                        <c:forEach items="${manufacturers}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
             </div>
         </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>