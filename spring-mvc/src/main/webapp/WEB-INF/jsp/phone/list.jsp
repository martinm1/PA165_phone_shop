<%--
  Created by IntelliJ IDEA.
  User: jakub
  Date: 17/12/2018
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Phones">
    <jsp:attribute name="body">

        <table class="table">
            <caption>Phones</caption>
            <thead>
            <tr>
                <th>ID</th>
                <th>Model Name</th>
                <th>Price</th>
                <th>Manufacturer</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${phones}" var = "phone">
                <tr>
                    <td>${phone.id}</td>
                    <td><c:out value="${phone.modelName}"/></td>
                    <td><c:out value="${phone.price}"/></td>
                    <td><c:out value="${phone.manufacturer}"/></td>
                    <td class="button">
                        <form method="get"
                              action="/pa165/phone/view/${phone.id}" >
                            <input class="btn btn-warning" type="submit" value="View" />
                        </form>
                    </td>
                    <c:set var="userId"><security:authentication property="principal.personId"/></c:set>
                    <td class="button">
                        <form method="get"
                              action="/pa165/order/new/${phone.id}/${userId}" >
                            <input class="btn btn-warning" type="submit" value="Order now" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <security:authorize access="hasRole('ROLE_ADMIN')">
        <div>
        <form method="get"
              action="/pa165/phone/new/" >
            <input class="btn btn-warning" type="submit" value="Create new Phone" />
        </form>
        </div>
        </security:authorize>
    </jsp:attribute>
</my:pagetemplate>
