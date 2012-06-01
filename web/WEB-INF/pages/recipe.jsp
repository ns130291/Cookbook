<%-- 
    Document   : recipe
    Created on : 09.05.2012, 10:00:59
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="rezept" scope="session" type="model.Receipt"/>

<div id="pcontent">
    <h2 style="display: inline-block;">${rezept.title}</h2>
    <c:choose>
        <c:when test="${empty rezept.author}">
        </c:when>
        <c:otherwise>
            von ${rezept.author}
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${empty rezept.picture}">
        </c:when>
        <c:otherwise>
            <div class="img" style="background-image: url('img/${rezept.picture}')"></div>
        </c:otherwise>
    </c:choose>
    <div>
        <h3>Zutaten</h3>
        <c:forEach items="${rezept.ingredientTbls}" var="ingredient">
            ${ingredient.name} ${ingredient.amount} ${ingredient.unit}<br>
        </c:forEach>
    </div>
    <div>
        <h3>Zubehör</h3>
        <c:forEach items="${rezept.equipments}" var="equipment">
            ${equipment.name}<br>
        </c:forEach>
    </div>
    <div>
        <h3>Anleitung</h3>
        ${rezept.description}
    </div>
</div>
<div id="pcontrols">
    <div class="button right" onclick="closePopup()">Schließen</div><div class="button right" onclick="addToList(${rezept.id});closePopup();">Hinzufügen</div>
    <div class="clear"></div>
</div>
