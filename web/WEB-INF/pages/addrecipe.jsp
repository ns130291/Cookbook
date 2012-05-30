<%-- 
    Document   : addrecipe
    Created on : 28.05.2012, 15:33:03
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="pcontent">
    <form method="post" enctype="multipart/form-data">
        <h1>Neues Rezept</h1>
        <div class="tr">
            <div class="td"><label for="author">Autor</label></div><div class="td"><input type="text" name="author" id="author"/></div>
        </div>
        <div class="tr">
            <div class="td"><label for="title">Titel</label></div><div class="td"><input type="text" name="title" id="title"/></div>
        </div>
        <div class="tr">
            <div class="td" style="vertical-align: top;"><label for="description">Anleitung</label></div><div class="td"><textarea rows="5" type="text" name="description" id="description"></textarea></div>
        </div>
        <div class="tr">
            <div class="td"><label for="picture">Bild</label></div><div class="td"><input type="file" accept="image/*" name="picture" id="picture"/></div>
        </div>
    </form>
</div>
<div id="pcontrols">
    <div class="button right" onclick="addToList(${rezept.id});closePopup();">Ok</div><div class="button right" onclick="closePopup()">Abbrechen</div>
    <div class="clear"></div>
</div>
