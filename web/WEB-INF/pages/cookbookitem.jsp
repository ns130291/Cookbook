<%-- 
    Document   : cookbookitem
    Created on : 28.05.2012, 12:02:03
    Author     : ns130291
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cookbookitem" scope="session" type="model.Receipt"/>
<div>
    <c:choose>
        <c:when test="${empty cookbookitem.picture}">
            <div class="cpicture left"></div>
        </c:when>
        <c:otherwise>
            <div class="cpicture left" style="background-image: url('img/${cookbookitem.picture}');"></div>
        </c:otherwise>
    </c:choose>
    ${cookbookitem.title}
    <div class="right button" onclick="removeFromList(${cookbookitem.id})">x</div>
</div>
<div class="clear"></div>