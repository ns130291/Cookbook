<%-- 
    Document   : index
    Created on : 26.04.2012, 14:06:43
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="rezepte" scope="session" type="java.util.List<model.Receipt>"/>
<jsp:useBean id="kochbuch" scope="session" type="java.util.List<model.Receipt>"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rezepte-Übersicht</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>
        <div>
            <div class="button right" onclick="newRecipe()">Neues Rezept</div>
            <h1>Rezepte</h1>
        </div>
        <div class="clear"></div>
        <div>
            <div class="right">
                <div class="right button" onclick="removeAllFromList()">Leeren</div>
                <a class="button right" href="controller?print=true">Drucken</a>
                <h2>Kochbuch</h2>
                <div id="selectedreceipts"><c:forEach items="${kochbuch}" var="rezept">
                        <div class="cookbookitem">
                            <div>
                                <c:choose>
                                    <c:when test="${empty rezept.picture}">
                                        <div class="cpicture left"></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="cpicture left" style="background-image: url('img/${rezept.picture}');"></div>
                                    </c:otherwise>
                                </c:choose>
                                ${rezept.title}
                                <div class="right button" onclick="removeFromList(${rezept.id})">x</div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </c:forEach></div>
                <div class="clear"></div>
            </div>
            <c:forEach items="${rezepte}" var="rezept">
                <div class="receiptrow">
                    <div class="receipttitle">${rezept.title}</div><div class="button" onclick="showPopup(${rezept.id})">Ansehen</div><div class="button" onclick="addToList(${rezept.id})">Hinzufügen</div>
                </div>
            </c:forEach>
        </div>
        <div class="clear"></div>
    </body>
</html>
