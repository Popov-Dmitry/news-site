let token;

function signIn(url) {
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
            localStorage.setItem("token", token);
            window.location.replace('http://localhost:8080/news');
        }
    });


    // let xhr = new XMLHttpRequest(); // инициализируем переменную новым объектом XMLHttpRequest
    //
    // xhr.open("GET", 'http://localhost:8080/news/new'); // определяем параметры для запроса на определенный адрес
    // xhr.onreadystatechange = function() { // обработчик событий, вызываемый при запуске события readystatechange (при каждом изменении свойства readyState)
    //     // проверяем состояние запроса, числовой код состояния HTTP ответа и наличие переданной функции
    //     console.log(xhr.readyState);
    //     if (this.readyState === 4 && this.status === 200) {
    //         console.log(xhr.response);
    //         alert(xhr.response);
    //     }
    // }
    // xhr.setRequestHeader("Authorization", token); // задаем значение заголовка HTTP запроса (текстовые данные в кодировке UTF-8)
    // xhr.send(); // отправляем запрос на сервер (используем значение параметра в качестве тела запроса)



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



// function signUp(url) {
//
//     let firstName = document.querySelector('#inputFirstName');
//     let lastName = document.querySelector('#inputLastName');
//     let email = document.querySelector('#inputEmail');
//     let password = document.querySelector('#inputPassword');
//     let data = JSON.stringify({
//         "firstName": firstName.value,
//         "lastName": lastName.value,
//         "email": email.value,
//         "password": password.value
//     });
//
//     $.ajax({
//         url: url,
//         type: 'POST',
//         data: data,
//         contentType: 'application/json; charset=utf-8',
//         dataType: 'json',
//         async: false,
//         success: function(resp) {
//             token = resp.token;
//             localStorage.setItem("token", token);
//             window.location.replace('http://localhost:8080/news');
//         }
//     });
// }





$(document).ready(function () {
    $("#sendBtn").click(function () {

        // let title = document.querySelector('#articleTitle');
        // let content = document.querySelector('#articleContent');
        // let data = JSON.stringify({
        //     "title": title.value,
        //     "content": content.value
        // });
        //
        // let xhr = new XMLHttpRequest(); // инициализируем переменную новым объектом XMLHttpRequest
        //
        // xhr.open("POST", 'http://localhost:8080/news'); // определяем параметры для запроса на определенный адрес
        // xhr.onreadystatechange = function() { // обработчик событий, вызываемый при запуске события readystatechange (при каждом изменении свойства readyState)
        //     // проверяем состояние запроса, числовой код состояния HTTP ответа и наличие переданной функции
        //     console.log(xhr.readyState);
        //     if (this.readyState === 4 && this.status === 200) {
        //         console.log(xhr.response);
        //         //alert(xhr.response);
        //         window.location.replace('http://localhost:8080/news');
        //         //$("body").html(xhr.response);
        //     }
        // }
        // xhr.setRequestHeader("Authorization", localStorage.getItem("token")); // задаем значение заголовка HTTP запроса (текстовые данные в кодировке UTF-8)
        // xhr.send(data); // отправляем запрос на сервер (используем значение параметра в качестве тела запроса)

        let title = document.querySelector('#articleTitle');
        let content = document.querySelector('#articleContent');
        let data = JSON.stringify({
            "title": title.value,
            "content": content.value
        });

        $.ajax({
            url: 'http://localhost:8080/news',
            type: 'POST',
            data: data,
            contentType: 'application/json; charset=utf-8',
            //dataType: 'json',
            headers: { 'Authorization': localStorage.getItem("token"),
                        'Content-Type': 'application/json' },
            async: false,
            success: function(resp) {
                window.location.replace('http://localhost:8080/news');
            },
            error: function (data) {
                if(data.status === 500) {
                    alert("Заполните все поля");
                }
                if(data.status === 403) {
                    alert("Недостаточно прав");
                }
                else {
                    alert(data.status + ':' + data.statusText);
                }
            }

        });



    })
})