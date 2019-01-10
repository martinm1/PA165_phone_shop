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


<my:pagetemplate title="Claims">
    <jsp:attribute name="body">
        <table class="table" id="t1">
            <caption>People</caption>
            <thead>
            <tr>
                <th>ID</th>
                <th>Wanted resolution</th>
                <th>Technical report</th>
                <th>Reason of claim</th>
                <th>Claim state</th>
                <th>View claim</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${claims}" var = "claim">
                <tr>
                    <td>${claim.id}</td>
                    <td><c:out value="${claim.wantedSolutionByCustomer}"/></td>
                    <td><c:out value="${claim.technicalReport}"/></td>
                    <td><c:out value="${claim.reasonOfClaim}"/></td>
                    <td><c:out value="${claim.claimState}"/></td>
                    <td class="button">
                        <form method="get"
                              action="/pa165/claim/view/${claim.id}" >
                            <input class="btn btn-warning" type="submit" value="View" />
                        </form>
                    </td>
                    <td class="button">
                        <form method="get"
                              action="/pa165/claim/newReport/${claim.id}" >
                            <input class="btn btn-warning" type="submit" value="Add report" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>
