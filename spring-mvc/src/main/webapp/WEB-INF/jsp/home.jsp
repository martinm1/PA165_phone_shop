<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
        <jsp:attribute name="body">
            <div style = "text-align:center">
                <h1>Welcome to our PhonEshop</h1>
                <p>Please note that without being signed in you can only</p>
                <p>view our sortiment but cant interact with it in any way</p>
                <a href="">I wish to continue without signing</a>
                Dont have an account?<br>
                <a href="">Sign up right now!</a>
            </div>
        </jsp:attribute>
</my:pagetemplate>
