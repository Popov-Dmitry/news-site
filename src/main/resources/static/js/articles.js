let url = 'http://localhost:8080/dev/api/public/articles';
let offset = 0;
let limit = 5;
let sortBy = 'dateDESC'
let articlesCount = 0;
let lastScroll = false;

$.getJSON(url + '/count', function(data, key) {
    articlesCount = data;
});

// <div class="card-footer text-center">' + data.author.firstName + ' ' + data.author.lastName + ' &nbsp;&nbsp; | &nbsp;&nbsp;' + data.timestamp + '</div>\

function scrollArticles() {
    let $target = $('#show-more-trigger');
    $target.show();

    let wt = $(window).scrollTop();
    let wh = $(window).height();
    let et = $target.offset().top;
    let eh = $target.outerHeight();
    let dh = $(document).height();

    if (wt + wh >= et || wh + wt === dh || eh + et < wh){

        $.getJSON(url + '?offset=' + offset + '&limit=' + limit + '&sortBy=' + sortBy, function(data, key) {

        $.each(data, function(key, data) {
            let previewContent = data.content.trim();
            let previewLength = 400;
            if(previewContent.length > previewLength) {
                previewContent = previewContent.substring(0, previewLength) + '...';
            }
            $('<article class="card">\
                    <h5 class="card-header">' + data.title + '</h5>\
                    <div class="card-body">\
                        <div class="card-text">' + previewContent + '</div>\
                        <a href="http://localhost:8080/news/' + data.id + '" class="btn btn-primary">Читать далее</a>\
                    </div>\
                    <div class="card-footer text-center">' + data.author.fullName + ' &nbsp;&nbsp; | &nbsp;&nbsp; ' + data.timestamp + '</div>\
                </article>')
                .appendTo('#articles-container')
        });
    });
        offset += limit;
        if(offset >= articlesCount && lastScroll) {
            $target.hide();
        }
        if(offset >= articlesCount && !lastScroll) {
            lastScroll = true;
        }
    }
}


$(window).scroll(function(){
    scrollArticles();
});

$(document).ready(function(){
    scrollArticles();

    $("#dateASC").click(function () {
        $("#popularity").removeClass("active");
        $("#dateDESC").removeClass("active");
        $("#dateASC").addClass("active");
        sortBy = "dateASC";
        offset = 0;
        document.getElementById("articles-container").innerHTML = "";
        scrollArticles();
    })

    $("#dateDESC").click(function () {
        $("#popularity").removeClass("active");
        $("#dateASC").removeClass("active");
        $("#dateDESC").addClass("active");
        sortBy = "dateDESC";
        offset = 0;
        document.getElementById("articles-container").innerHTML = "";
        scrollArticles();
    })

    $("#popularity").click(function () {
        $("#dateASC").removeClass("active");
        $("#dateDESC").removeClass("active");
        $("#popularity").addClass("active");
        sortBy = "popularity";
        offset = 0;
        document.getElementById("articles-container").innerHTML = "";
        scrollArticles();
    })

});