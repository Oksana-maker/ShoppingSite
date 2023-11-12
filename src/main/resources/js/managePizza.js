
$(document).ready(function() {
    // Your JavaScript code here
});
$(function () {

    $('#inputName').on('input', function () {
        // console.log($('#inputName').val());
        validate();
    })
    $('#inputIngredients').on('input', function () {
        validate();

    })
    $('#inputPrice').on('input', function () {
        validate();

    })

    function validate() {
        let inputValueName =$('#inputName').val().trim();
        let inputValueIngredients = $('#inputIngredients').val().trim();
        let inputValuePrice = $('#inputPrice').val().trim();
        if (inputValueName!=''&& inputValueIngredients!=''&&inputValuePrice!=''){
            $('#buttonSubmit').prop('disabled', false);
        }
        else {
            $('#buttonSubmit').prop('disabled', true);
        }
    }
    $('#buttonSubmit').on('click', function (event) {
        if ($('#buttonSubmit').prop('disabled')) {
            event.preventDefault();
            $('#myModal').modal('show');
        } else {
            // Clear the form fields
            $('#inputName').val('');
            $('#inputIngredients').val('');
            $('#inputPrice').val('');
            $('#buttonSubmit').prop('disabled', true);
            // Submit the form
            $(this).unbind('submit').submit();
        }
    });
})
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
