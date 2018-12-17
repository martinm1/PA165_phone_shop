<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate>
        <jsp:attribute name="body">
            <div class="jumbotron">
                <h1>Welcome to our PhonEshop</h1>
                <p>Please note that without being signed in you can only</p>
                <p>view our sortiment but cant interact with it in any way</p>

                <div class="row">
                    <div class="col-sm-12">
                        <security:authorize access="!isAuthenticated()">
                            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/auth" role="button"> Login form</a>
                            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/person/new" role="button"> Register form</a>
                        </security:authorize>
                    </div>
                </div>
            </div>
        </jsp:attribute>
</my:pagetemplate>
