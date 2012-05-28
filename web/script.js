/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showPopup(id){
    //alert(id);
    var req = new XMLHttpRequest();
    var url = "/Cookbook/controller";
    var params = "article="+id;
    req.open("get", url+"?"+params, true);
    req.onreadystatechange = function(){
        if(req.readyState==4&&req.status==200){
            var popup = document.createElement("div");
            popup.innerHTML = req.responseText;
            popup.id = "popup";
            document.body.insertBefore(popup, document.body.childNodes[0]);
        }
    }
    req.send();
}

function closePopup(){
    document.body.removeChild(document.getElementById("popup"));
}

function addToList(id){
    var req = new XMLHttpRequest();
    var url = "/Cookbook/controller";
    var params = "toCookbook="+id;
    req.open("post", url, true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  
    req.onreadystatechange = function(){
        if(req.readyState==4){
            if(req.status==200){                
                var newCookbookEntry = document.createElement("div"); 
                newCookbookEntry.className="cookbookitem";
                newCookbookEntry.innerHTML = req.responseText;
                document.getElementById("selectedreceipts").appendChild(newCookbookEntry);
            }else{
                alert(req.responseText);
            }
        }
    }
    req.send(params);
}

function newReceipt(){
    
}
