/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showPopup(id){
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

function newRecipe(){
    var req = new XMLHttpRequest();
    var url = "/Cookbook/controller";
    var params = "newrecipe=";
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

function newRecipeSave(){
    //hiddenfields for equipment and ingredients
    //equipment
    var array = document.getElementById("equipment").childNodes;
    var equipment="";
    for(var i = 0; i<array.length;i++){
        equipment+=array[i].childNodes[0].value;
        equipment+=';';
    }
    var hidden = document.createElement("input");
    hidden.type = 'hidden';
    hidden.value = equipment;
    hidden.name = 'equipment';
    document.getElementById("equipment").appendChild(hidden);
    //ingredients
    array = document.getElementById("ingredient").childNodes;
    var ingredient="";
    for(var i = 0; i<array.length;i++){
        ingredient+=array[i].childNodes[0].value;
        ingredient+=':';
        ingredient+=array[i].childNodes[1].value;
        ingredient+=':';
        ingredient+=array[i].childNodes[2].value;
        ingredient+=';';
    }
    hidden = document.createElement("input");
    hidden.type = 'hidden';
    hidden.value = ingredient;
    hidden.name = 'ingredient';
    document.getElementById("ingredient").appendChild(hidden);
//submit form
    document.getElementById("addrecipe").submit();
}

function addEquipment(){
    var child = document.createElement("div");
    child.innerHTML = '<input type="text"/>';
    document.getElementById("equipment").appendChild(child);
}

function addIngredient(){
    var child = document.createElement("div");
    child.innerHTML = '<input type="text"/><input type="text"/><input type="text"/>';
    document.getElementById("ingredient").appendChild(child);
}

function removeFromList(id){
    var req = new XMLHttpRequest();
    var url = "/Cookbook/controller";
    var params = "removeFromCookbook="+id;
    req.open("post", url, true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  
    req.onreadystatechange = function(){
        if(req.readyState==4){
            window.location.reload()
        }
    }
    req.send(params);
}

function removeAllFromList(){
    var req = new XMLHttpRequest();
    var url = "/Cookbook/controller";
    var params = "removeAllFromCookbook=";
    req.open("post", url, true);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  
    req.onreadystatechange = function(){
        if(req.readyState==4){
            window.location.reload()
        }
    }
    req.send(params);
}
