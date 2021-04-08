$(document).ready(function () {
    $.getJSON('http://localhost:8080/dev/api/public/articles', function(data, key) {

        $.each(data, function(key, data) {
            $('<article class="card">\
             <h5 class="card-header">' + data.title + '</h5>\
            <div class="card-body">\
                <p class="card-text">' + data.content + '</p>\
                <a href="http://localhost:8080/news/' + data.id + '" class="btn btn-primary">Читать далее</a>\
            </div>\
            <div class="card-footer text-center">' + 'Имя ' + 'Фамилия ' + ' &nbsp;&nbsp; | &nbsp;&nbsp;' + data.timestamp + '</div>\
        </article>')
                .appendTo('#articles-container')
        });
    });

});

// <div class="card-footer text-center">' + data.author.firstName + ' ' + data.author.lastName + ' &nbsp;&nbsp; | &nbsp;&nbsp;' + data.timestamp + '</div>\