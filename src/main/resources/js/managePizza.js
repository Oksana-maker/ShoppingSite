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
})