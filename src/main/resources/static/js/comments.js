let url = 'http://localhost:8080/dev/api/public/comments';
let id = 0;

function getComments() {
    $.getJSON(url + '/' + id, function(data, key) {

        $.each(data, function(key, data) {
            console.log(data.content);
            $('<div class="card">\
                    <div class="card-body">\
                        <h5 class="card-title">' + data.user.fullName + '</h5>\
                        <p class="card-text">' + data.content + '</p>\
                        <p class="card-text" style="font-size: 13px">' + data.timestamp + '</p>\
                    </div>\
                </div>')
                .appendTo('.comments-container')
        });
    });
}

$(document).ready(function(){
    id = document.location.pathname.split("/")[2];
    getComments();

    $("#sendCommentBtn").click(function () {
        let content = document.querySelector('#commentContent');
        let data = JSON.stringify({
            "content": content.value
        });

        $.ajax({
            url: 'http://localhost:8080/dev/api/public/comments/' + id ,
            type: 'POST',
            data: data,
            contentType: 'application/json; charset=utf-8',
            //dataType: 'json',
            headers: { 'Authorization': localStorage.getItem("token"),
                'Content-Type': 'application/json' },
            async: false,
            success: function(resp) {
                document.getElementsByClassName("comments-container")[0].innerHTML = "";
                getComments();
            },
            error: function (data) {
                if(data.status === 204) {
                    alert("Комментарий не может быть пустым");
                }
                if(data.status === 403) {
                    alert("Недостаточно прав, пожалуйста, войдите в свой аккаунт");
                }
                else {
                    alert(data.status + ':' + data.statusText);
                }
            }

        });

    });

});