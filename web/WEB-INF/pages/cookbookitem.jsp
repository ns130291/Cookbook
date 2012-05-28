<%-- 
    Document   : cookbookitem
    Created on : 28.05.2012, 12:02:03
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cookbookitem" scope="session" type="model.Receipt"/>
<div>
    <div class="cpicture left" style="background-image: url('img/abc.png');"></div>
    <%--<div style="background-image: url(img/${cookbookitem.picture});"></div>--%>
    ${cookbookitem.title}
</div>
<div class="clear"></div>