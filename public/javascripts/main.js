let webSocket;
let messageInput;
let keyResultData;
let skills;
let setOfKey = [];

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

let requestOptions = {
    method: 'POST',
    headers: myHeaders,
    redirect: 'follow'
};

setInterval(function () {
    for (let value of setOfKey) {
        doSend({message: value.split('"').join('')});
    }
}, 15000);

function addDataToTable(d, table) {
    console.log("**printing d here**"+d.length);
    console.log(d);
    let j = 0;
    if (d.length >= 10) {
        let ln = d.length;
        let loop = Math.abs(ln - 10);
        while (loop > 0) {
            d.shift();
            loop--;
        }
    }

    while (d.length > 0 && j < 10) {
        //let darray = (d[j].data[0]);
        // console.log("**bhai**"+darray);
        //console.log(Object.keys(d[j].data[0]).length);
        for (let i = 0; i <= 10; i++) {

            let it = d[0].data[i];
            if(it) {
                let row = table.insertRow(0);
                let cell1 = row.insertCell(0);
                let cell2 = row.insertCell(1);
                let cell3 = row.insertCell(2);
                let cell4 = row.insertCell(3);
                let cell5 = row.insertCell(4);
                let cell6 = row.insertCell(5);
                let cell7 = row.insertCell(6);
                let cell8 = row.insertCell(7);
                let cell9 = row.insertCell(8);


                cell1.innerHTML = `<a href="/user/${it.owner_id}">${it.owner_id}</a>`;
                cell2.innerHTML = it.date;
                cell3.innerHTML = it.title;
                cell4.innerHTML = it.type;
                //cell5.innerHTML=`<a href="/skills/${it.skills}">${it.skills}</a>`;
                skills = (it.skills);
                console.log(skills);
                console.log("length is -->" + skills.length);
                for (let k = 0; k < skills.length; k++) {
                    console.log("***Shivam count is --> " + k + " and skill is -->" + skills[k]);
                    //console.log(skills[k]);
                    //     let skill=it.skills[k];
                    let sk=encodeURIComponent(skills[k]);
                    cell5.innerHTML += `<ul><a href="/skill/${sk}">${skills[k]} </a></ul>`;
                }
                cell6.innerHTML = it.fleschKincaid;
                cell7.innerHTML = it.fleschReadIndex;
                cell8.innerHTML = it.eduLevel;
                let title = encodeURIComponent(it.title);
                let descr = encodeURIComponent(it.desc);
                cell9.innerHTML = `<a href="/individualStat/${descr}">wordStat</a>`;
                cell1.addEventListener("click", function () {
                    window.location.href = "/user/" + it.owner_id;
                });
                cell5.addEventListener("click", function () {
                    window.location.href = "/skill/" + sk;
                });

                console.log(title);
                console.log(descr);
                // title=encodeURI(title);
                // desc=encodeURI(desc);

                title = title.replaceAll(".", "");
                title = title.replaceAll(",", "");
                title = title.replaceAll("/", "");
                descr = descr.replaceAll(",", "");
                descr = descr.replaceAll(".", "");
                descr = descr.replaceAll("\\/", "");
                //title = @RouteController.EncodeURL(it.title);
                //descr = @RouteController.EncodeURL(it.desc);
                console.log("*****SHivam test xx******");
                console.log(title);
                console.log(descr);

                cell9.addEventListener("click", function () {
                    window.location.href = "/individualStat/" + descr;
                });
            }
            else{
                break;
            }
        }

        let row3 = table.insertRow(0);
        let cell31 = row3.insertCell(0);
        let cell32 = row3.insertCell(1);
        let cell33 = row3.insertCell(2);
        let cell34 = row3.insertCell(3);
        let cell35 = row3.insertCell(4);
        let cell36 = row3.insertCell(5);
        let cell37 = row3.insertCell(6);
        let cell38 = row3.insertCell(7);
        let cell39 = row3.insertCell(8);
        cell31.innerHTML="<h3>Owner Id</h3>";
        cell32.innerHTML="<h3>Date</h3>";
        cell33.innerHTML="<h3>Title</h3>";
        cell34.innerHTML="<h3>Type</h3>";
        cell35.innerHTML="<h3>Skills</h3>";
        cell36.innerHTML="<h3>FK</h3>";
        cell37.innerHTML="<h3>FRI</h3>";
        cell38.innerHTML="<h3>EduLevel</h3>";
        cell39.innerHTML="<h3>Stat</h3>";

        let row2 = table.insertRow(0);
        row2.insertCell(0);
        row2.insertCell(1);
        row2.insertCell(2);
        row2.insertCell(3);
        let cell10 = row2.insertCell(0);
        let cell11 = row2.insertCell(1);
        let cell12 = row2.insertCell(3);
        let cell13 = row2.insertCell(7);

        // cell10.style = "font-weight: bold";
        row2.style.cssText = 'background-color: #e9e9e9;' +
            'align:center;' +
            'width:100%;'
        // row2.style="width: 100%";
        // row2.style="background-color: #e9e9e9";
        row3.style="background-color: #c0bfbf";
        cell11.style = "font-size:30px";
        cell12.style="font-size:20px"
        cell13.style="font-size:20px"
        setOfKey.push(d[0].key.toString());
        setOfKey = [...new Set(setOfKey)];
         // cell10.innerHTML = new String("Search Term : ");
        cell11.innerHTML = new String( d[0].key);
        cell12.innerHTML = new String("AVG FKGL: " + d[0].Avg_FKGL);
        cell13.innerHTML = new String("AVG FRI: " + d[0].Avg_FRI);
        d.shift();
        j++;
    }
}

function onMessage(evt) {
    console.log(evt.data);
    keyResultData = JSON.parse(evt.data);
    let table = document.getElementById("records_table");
    $("#records_table").empty();

    requestOptions.body = JSON.stringify({
        "id": sessionStorage.getItem("key"),
        "value": keyResultData
    });
    fetch("http://localhost:9000/setCache", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

    addDataToTable(keyResultData, table);
}

if (!sessionStorage.getItem("key")) {
    var requestOptionForGet = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch("http://localhost:9000/getCacheId", requestOptionForGet)
        .then(response => response.text())
        .then(result => {
            if (checkIfValidUUID(result)) {
                sessionStorage.setItem("key", result);
            }
        })
        .catch(error => console.log("error"));
} else {
    var requestOptionForGet = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch('http://localhost:9000/getCache/"' + sessionStorage.getItem("key") + '"', requestOptionForGet)
        .then(response => response.text())
        .then(result => {
            keyResultData = JSON.parse(result);
            let table = document.getElementById("records_table");
            $("#records_table").empty();
            addDataToTable(keyResultData, table);
        })
        .catch(error => console.log('error', error));
}

function checkIfValidUUID(str) {
    const regexExp = /^[0-9a-fA-F]{8}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{12}$/gi;

    return regexExp.test(str);
}

function appendMessageToView(title, message) {
    $("#messageContent").append("<p>" + title + ": " + message + "</p>");
}

function doSend(protocolMessage) {
    if (webSocket.readyState == WebSocket.OPEN) {
        webSocket.send(JSON.stringify(protocolMessage.message));
    } else {
        console.log("Could not send data. Websocket is not open.");
    }
}

window.addEventListener("load", init, false);

$("#sendButton").click(function () {
    newMessage();
});

$("#btn").click(function () {
    window.location.href = "/GlobalStat/";
});

$(window).on("keydown", function (e) {
    if (e.which == 13) {
        newMessage();
        return false;
    }

    if (e.which == 116 || e.keyCode == 116 || e.which == 168 || e.keyCode == 168) {
        if (confirm("Current data will be lost, are you sure")) {
            console.log("Page refreshed");
            return true;
        } else {
            console.log("do nothing");
            return false;
        }

    }
});

function newMessage() {
    messageInput = $("#messageInput").val();
    if ($.trim(messageInput) == "") {
        return false;
    }

    appendMessageToView("Me", messageInput);

    var message = {
        message: messageInput
    };

    doSend(message);
    document.getElementById("messageInput").value = "";
}
