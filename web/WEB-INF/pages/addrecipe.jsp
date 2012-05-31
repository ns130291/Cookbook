<%-- 
    Document   : addrecipe
    Created on : 28.05.2012, 15:33:03
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="pcontent">
    <form id="addrecipe" name="addrecipe" action="controller" method="post" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="addrecipe" value=""/>
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
            <div class="td"><label for="degree">Schwierigkeit</label></div><div class="td"><input type="number" name="degree" id="degree"/></div>
        </div>
        <div class="tr">
            <div class="td"><label for="duration">Dauer</label></div><div class="td"><input type="number" name="duration" id="duration"/></div>
        </div>
        <div class="tr">
            <div class="td" style="vertical-align: top;"><label for="note">Notiz</label></div><div class="td"><textarea rows="5" type="text" name="note" id="note"></textarea></div>
        </div>
        <div class="tr">
            <div class="td" style="vertical-align: top;">Zutaten</div><div class="td"><div>Name Menge Einheit</div><div  id="ingredient"></div><div class="button" onclick="addIngredient()">+</div></div>
        </div>
        <div class="tr">
            <div class="td" style="vertical-align: top;">Zubehör</div><div class="td"><div  id="equipment"></div><div class="button" onclick="addEquipment()">+</div></div>
        </div>        
    </form>
    <iframe src="controller?iframe=upload" width="100%"></iframe>
</div>
<div id="pcontrols">
    <div class="button right" onclick="newRecipeSave();">Speichern</div><div class="button right" onclick="closePopup()">Abbrechen</div>
    <div class="clear"></div>
</div>
