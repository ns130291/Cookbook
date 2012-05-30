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
                    Inhalt
                    <ul>
                        <c:forEach items="${kochbuch}" var="rezept">
                            <li>${rezept.title}</li>
                        </c:forEach>
                    </ul>
                    <div>
                        <c:forEach items="${kochbuch}" var="rezept">
                            <h2 style="display: inline-block;">${rezept.title}</h2> von ${rezept.author}
                            <div>
                                ${rezept.description}
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
