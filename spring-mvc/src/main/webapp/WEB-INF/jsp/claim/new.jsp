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


<my:pagetemplate title="New claim">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/claim/create"
                   modelAttribute="claimCreate"  cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="reasonOfClaim" cssClass="col-sm-2 control-label">Reason of claim:</form:label>
                <div class="col-sm-10">
                    <form:input path="reasonOfClaim" cssClass="form-control"/>
                    <form:errors path="reasonOfClaim" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group">
                <%--<form:label path="order" cssClass="col-sm-2 control-label">Reason of claim:</form:label>--%>
                <div class="col-sm-10">
                    <form:hidden path="orderId" cssClass="form-control" value="${orderId}" />
                    <form:errors path="orderId" cssClass="help-block"/>
                </div>
            </div>


          <div class="form-group">
              <form:label path="wantedSolutionByCustomer" cssClass="col-sm-2 control-label">Wanted resolution:</form:label>
              <div class="col-sm-10">
                    <form:select path="wantedSolutionByCustomer" cssClass="form-control">
                        <c:forEach items="${claimSolution}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                  <form:errors path="wantedSolutionByCustomer" cssClass="error"/>
              </div>
          </div>
        <button class="btn btn-primary" type="submit">Create claim</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>
