<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




  <%--  <div class="jumbotron">
        <h1>Welcome to SpringMVC !</h1>
        <p class="lead">In this seminar, the mysteries of Spring MVC will be revealed to you. </p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/example/foo/1/platypus55?b=42"
              role="button">Call ExampleController</a></p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/shopping/show"
              role="button">Go shopping</a></p>
    </div>


    <div class="row">
        <c:forEach begin="1" end="12" var="i">
        <div class="col-xs-12 col-sm-6 col-md-2 col-lg-1">
            <p><button class="btn btn-default">Button ${i}</button></p>
        </div>
        </c:forEach>
    </div>--%>

    <my:pagetemplate>
        <jsp:attribute name="body">
            <div style = "text-align:center">
                <h1>Welcome to our PhonEshop</h1>
                <p>Please note that without being signed in you can only</p>
                <p>view our sortiment but cant interact with it in any way</p>
                <a href="">I wish to continue without signing</a>
                <form>
                    <input type="text" name="username" value="username"/><br>
                    Password:<br>
                    <input type="text" name="password" value="password"/><br><br>
                    <input type="submit" value="Sign in">
                </form>
                Dont have an account?<br>
                <a href="">Sign up right now!</a>
            </div>
        </jsp:attribute>
    </my:pagetemplate>
