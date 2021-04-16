function sendJSON(url) {
    // let request;
    // if(window.XMLHttpRequest){
    //     request = new XMLHttpRequest();
    // } else if(window.ActiveXObject){
    //     request = new ActiveXObject("Microsoft.XMLHTTP");
    // } else {
    //     return;
    // }
    //
    // request.open("POST", url, true);
    // request.setRequestHeader("Content-Type", "application/json");
    //
    // let email = document.querySelector('#inputEmail');
    // let password = document.querySelector('#inputPassword');
    //
    // request.onreadystatechange = function() {
    //     console.log(1);
    //
    //     if (request.status === 200 && request.readyState === 4) {
    //         window.location.replace('http://localhost:8080/news');
    //     }
    // };
    //
    // let data = JSON.stringify({
    //     "email": email.value,
    //     "password": password.value
    // });
    // request.send(data);


    // $.getJSON(url, function(data, key) {
    //     console.log(data[1])
    // });

    // $(".btn").submit(function ())




    let email = document.querySelector('#inputEmail');
    let password = document.querySelector('#inputPassword');
    let data = JSON.stringify({
        "email": email.value,
        "password": password.value
    });
    let token;
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(resp) {
            // alert(resp.token);
            token = resp.token;
            window.location.replace('http://localhost:8080/news');
        }
    });

}


// $(document).ready(function () {
//     $("#new").click(function () {
//         $.ajax({
//             url: "http://localhost:8080/news/new",
//             type: 'GET',
//             headers: {'Authorization': token},
//             async: false,
//             success: function(resp) {
//                 // alert(resp.token);
//                 window.location.replace('http://localhost:8080/news');
//             }
//         });
//     })
// })