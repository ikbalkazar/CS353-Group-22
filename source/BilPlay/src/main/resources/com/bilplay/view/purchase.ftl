<!DOCTYPE html>
<html>
<title>Game Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {font-family: "Montserrat", sans-serif;}
h1 {
    font-size: 28px;
}
</style>


<body class="w3-content" style="max-width:1200px">

<div class="w3-half w3-container">
    <img src="${game.pic}" style="width:100%" height="600">
</div>
<div class="w3-half w3-container w3-border w3-padding-16 w3-margin-bottom">
    <div class="w3-row w3-container w3-border">
        <p align="left">${game.description}</p>
    </div>
    <div class="w3-row w3-container w3-border w3-margin-top">
        <h1 style="text-align:left;">BALANCE: ${user.budget}<br>
            PRICE: ${game.price}</h1>
    </div>
    <form action="/make_purchase/${game.id}" method="post">
        <button type="submit" class="w3-button w3-red w3-margin-top w3-round-xxlarge w3-xxlarge w3-border w3-border-blue w3-hover-teal w3-center">Purchase</button>
    </form>
    </a>
</div>

<strong>${message}</strong>

</body>
</html>