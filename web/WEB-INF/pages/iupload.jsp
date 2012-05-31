<%-- 
    Document   : iupload
    Created on : 31.05.2012, 21:01:00
    Author     : ns130291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form  enctype="multipart/form-data" action="controller?uploadpicture=" method="POST">
            <div id="picdiv" class="tr">
                <div class="td"><label for="picture">Bild</label></div><div class="td"><input type="file" accept="image/*" name="picture" id="picture" /></div><input type="submit" class="button" value="Upload"/>
            </div>
        </form>
    </body>
</html>
