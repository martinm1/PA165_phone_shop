<%--
  Created by IntelliJ IDEA.
  User: stevo
  Date: 17.12.2018
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate title="Orders">
    <jsp:attribute name="body">
        <div class="jumbotron" id="jumbo">
        <table class="table" id="t1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Order state</th>
                <th>Ordered item</th>
                <th>Manufacturer</th>
                <th>Number of claims</th>
                <th></th>
                <th></th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var = "order">
                <tr>
                    <td>${order.id}</td>
                    <td><c:out value="${order.state}"/></td>
                    <td><c:out value="${order.phone.modelName}"/></td>
                    <td><c:out value="${order.phone.manufacturer}"/></td>
                    <td>${fn:length(order.claims)}</td>
                    <td class="button">
                        <form method="get"
                              action="/pa165/order/view/${order.id}" >
                            <input class="btn btn-warning" type="submit" value="View order" />
                        </form>
                    </td>

                    <c:if test="${fn:length(order.claims) lt 3}">
                        <td class="button">
                            <form method="get"
                                  action="/pa165/claim/new/${order.id}" >
                                <input class="btn btn-warning" type="submit" value="New claim" />
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </jsp:attribute>
</my:pagetemplate>

