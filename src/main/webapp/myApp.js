"use strict";

var baseURL = "http://localhost:9080/MyApp/hello/my"

function callInstantTimerStart() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('GET', baseURL + "/instantTimerStart", true);
    xhr.send();
}

function callHello() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('GET', baseURL + "/hello", true);
    xhr.send();
}

function callValueTest() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('GET', baseURL + "/valueTest", true);
    xhr.send();
}

function callPostTest() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('POST', baseURL + "/postTest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(
        {
            "ciao": "come stai"
        }
    ));
}


function callInsertTest() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('POST', baseURL + "/insertTest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(
        {
            "identity"      : "1"           ,
            "name"          : "nome"        ,
            "description"   : "descrizione"
        }
    ));
}

function callDeleteTest() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('POST', baseURL + "/deleteTest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(
        {
            "identity"      : "1"           ,
        }
    ));
}

function callUpdateTest() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }

        out.innerHTML = data;
    };

    xhr.open('POST', baseURL + "/updateTest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(
        {
            "identity"      : "1"           ,
            "description"   : "un altra descrizione"
        }
    ));
}

function callSelectListTest() {
    var out = document.getElementById("Output")

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;
        var data = "";
        if (this.status == 200) {
            data = this.responseText;
        } else {
            data = "ERRORE==" + this.statusText;
        }
        out.innerHTML =``;
        data=data.substring(1,data.length-1);
        data=data.split(',');
        for (var i in data){
            var row=data[i];
            row=row.substring(1,row.length-1);
            row=row.replaceAll("\\n","<br>");
            if(i!=0)  out.innerHTML +=`<h3>Risultato ${i}</h3>`
            out.innerHTML +=`${row}<br><br>`
        }
    };

    xhr.open('POST', baseURL + "/selectListTest", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(
        {
            "identity"      : "1"
        }
    ));
}