$(document).ready(function () {
    let articles = document.querySelectorAll('.card');
    let previewLength = 400;

    articles.forEach(function (article) {
        let previewContent = article.querySelector('.card-text').textContent;
        if(previewContent.length > previewLength) {
            article.querySelector('.card-text').textContent = previewContent.substring(0, previewLength) + '...';
        }
    })
})