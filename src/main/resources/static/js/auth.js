let token;

function signIn(url) {

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
        },
        error: function (data) {
            if(data.status === 403) {
                alert("Неверный логин или пароль");
            }
            else {
                alert(data.status + ':' + data.statusText);
            }
        }
    });

}


$(document).ready(function () {
    $("#sendBtn").click(function () {

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
                else if(data.status === 403) {
                    alert("Недостаточно прав");
                }
                else {
                    alert(data.status + ':' + data.statusText);
                }
            }

        });
    })
})




$(document).ready(function () {
    let data = JSON.stringify({
        "token": localStorage.getItem("token")
    });

    let userRef = 'http://localhost:8080/users/';

    $.ajax({
        url: 'http://localhost:8080/dev/api/public/auth',
        type: 'POST',
        data: data,
        contentType: 'application/json; charset=utf-8',
        //dataType: 'json',
        async: false,
        success: function(resp) {
            console.log(resp);
            if (resp.isValid) {
                $('#reg').hide();
                $('#login').hide();
                $('#user').show();
                $('#navbarDropdown').html(resp.name);
                userRef = userRef + resp.id
                $('#navbarDropdown').attr('href', userRef);
            }
            else {
                $('#reg').show();
                $('#login').show();
                $('#user').hide();
            }
        },
        error: function (data) {
            alert(data.status + ':' + data.statusText);
        }

    });

    $('#exit').click(function () {
        $.ajax({
            url: 'http://localhost:8080/dev/api/public/auth/logout',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            //dataType: 'json',
            headers: { 'Authorization': localStorage.getItem("token"),
                'Content-Type': 'application/json' },
            async: false,
            success: function(resp) {
                localStorage.removeItem('token');
                window.location.replace('http://localhost:8080/news');
            },
            error: function (data) {
                alert(data.status + ':' + data.statusText);
            }

        });
    });

    $('#navbarDropdown').click(function () {
        window.location.replace(userRef);
    })

})