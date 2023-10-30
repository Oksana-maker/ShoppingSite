$(function (){
    let priceOfPizza = parseFloat($("#pricePizza").text());
    $("#selectAmount").on("input",function (){
        let numberOfPizzas = $("#selectAmount").val();
        console.log(numberOfPizzas);
        let totalPrice = Number((priceOfPizza*numberOfPizzas).toFixed(2));
        console.log(totalPrice);
        $("#totalPrice").text("Total Price: "+ totalPrice);
    })
})