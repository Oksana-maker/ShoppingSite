$(function (){
    let rowOriginalHeight;
    $('.pizzaRow').hover(mouseEnter,mouseLeave );
    function mouseEnter(){
        imageOriginalHeight=$(this).css('height').slice(0,-2);
        $(this).css('height',rowOriginalHeight * 3.5 +"px");
        $(this).find("img").css('transform','scale(3.5,3.5');

    }
    function mouseLeave(){
        $(this).css('height', rowOriginalHeight +"px");
        $(this).find("img").css('transform', 'none');
    }
})