"use strict";

var baseURL="http://localhost:9080/MyApp/hello/my"

function callHello(){
    var out=document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data="";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data="ERRORE=="+this.statusText;
        }

        out.innerHTML=data;
    };

    xhr.open('GET', baseURL+"/hello", true);
    xhr.send();
}

function callPostTest(){
    var out=document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data="";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data="ERRORE=="+this.statusText;
        }

        out.innerHTML=data;
    };

    xhr.open('POST', baseURL+"/postTest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(
        {
            valori:[
                {
                    chiave:"ciao",
                    valore:"sono un valore!"
                }
            ]
        }
    ));
}