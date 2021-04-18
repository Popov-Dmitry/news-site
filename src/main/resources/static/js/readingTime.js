$(document).ready(function () {
    document.getElementsByClassName("article-info")[1].textContent = "Время чтения: " +
        Math.ceil(document.getElementsByClassName("article-text")[0].textContent
            .split(" ").length / 150) + " мин."
})