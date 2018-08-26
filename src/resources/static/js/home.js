$(document).scroll(function() {
    var y = $(this).scrollTop();
    if (y > 300) {
        $('.header-image').fadeIn();
    } else {
        $('.header-image').fadeOut();
    }
});

$(function() {
    $('#carousel-learn-more').click(function(){
        $('html, body').animate({scrollTop: $("#about-paragraph").offset().top - 270}, 1500);
    });
});

// Hide home arrow on window scroll
$(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
        $('.arrow').fadeOut(1000);
    } else {
        $('.arrow').fadeIn(1000);
    }
});