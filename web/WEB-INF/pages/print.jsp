<%-- 
    Document   : print
    Created on : 08.05.2012, 21:09:22
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="kochbuch" scope="session" type="java.util.List<model.Receipt>"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kochbuch drucken</title>
        <link rel="stylesheet" type="text/css" href="style.css">        
    </head>
    <body>
        <div>
            <a class="button" href="controller">Zurück</a>
        </div>
        <div id="kochbuch">
            <c:choose>
                <c:when test="${empty kochbuch}">
                    keine Rezepte im Kochbuch
                </c:when>
                <c:otherwise>
                    <h1>Kochbuch</h1>
                    <h2>Inhalt</h2>
                    <ul>
                        <c:forEach items="${kochbuch}" var="rezept">
                            <li>${rezept.title}</li>
                        </c:forEach>
                    </ul>
                    <div>
                        <c:forEach items="${kochbuch}" var="rezept">
                            <div>
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
                            <br><br><br><br><br>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
