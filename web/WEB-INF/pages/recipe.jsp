<%-- 
    Document   : recipe
    Created on : 09.05.2012, 10:00:59
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="rezept" scope="session" type="model.Receipt"/>

<div id="pcontent">
  ${rezept.title}
</div>
<div id="pcontrols">
    <div class="button right" onclick="closePopup()">Schließen</div><div class="button right" onclick="addToList(${rezept.id});closePopup();">Hinzufügen</div>
    <div class="clear"></div>
</div>
