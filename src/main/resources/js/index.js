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

    // $('img').hover(mouseEnter, mouseLeave);
    //     function mouseEnter(){
    //         rowOriginalHeight = $(this).parent().parent().css('height').slice(0,-2);
    //         imageOriginalHeight=$(this).css('height');
    //             console.log(rowOriginalHeight);
    //         $(this).css('transform', 'scale(3.5, 3.5)');
    //         $(this).parent().parent().css('height',rowOriginalHeight * 3.5 +"px");
    //     }
    // function mouseLeave() {
    //     $(this).css('transform', 'none');
    //     $(this).parent().parent().css('height',rowOriginalHeight +"px");
    // }
})
