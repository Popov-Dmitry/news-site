function sendJSON(url) {
    let request;
    if(window.XMLHttpRequest){
        request = new XMLHttpRequest();
    } else if(window.ActiveXObject){
        request = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        return;
    }

    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");

    let title = document.querySelector('#articleTitle');
    let content = document.querySelector('#articleContent');


    let data = JSON.stringify({
        "title": title.value,
        "content": content.value
    });
    request.send(data);
}