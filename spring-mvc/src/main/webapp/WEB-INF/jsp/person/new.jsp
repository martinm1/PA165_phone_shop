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
                <form:label path=""
            </div>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>