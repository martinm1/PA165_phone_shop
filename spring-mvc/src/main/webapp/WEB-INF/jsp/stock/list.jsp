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

<my:pagetemplate title="Stocks">
    <jsp:attribute name="body">
        <div class="jumbotron" id="jumbo">
        <table class="table" id="t1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Street name</th>
                <th>Street number</th>
                <th>City</th>
                <th>Country</th>
                <th>Phones in stock</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${stocks}" var = "stock">
                <tr>
                    <td>${stock.id}</td>
                    <td><c:out value="${stock.name}"/></td>
                    <td><c:out value="${stock.address.streetName}"/></td>
                    <td><c:out value="${stock.address.streetNumber}"/></td>
                    <td><c:out value="${stock.address.city}"/></td>
                    <td><c:out value="${stock.address.country}"/></td>
                    <td class="button">
                        <form method="get"
                              action="/pa165/phone/list/byStockId">
                            <input type=hidden name="stockId" value="${stock.id}">
                            <input class="btn btn-warning" type="submit" value="View" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
        <div>
            <form method="get"
                  action="/pa165/stock/new/" >
                <input class="btn btn-warning" type="submit" value="Create new stock" />
            </form>
        </div>
    </jsp:attribute>
</my:pagetemplate>
