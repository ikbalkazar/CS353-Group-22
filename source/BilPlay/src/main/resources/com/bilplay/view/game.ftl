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
  	<p style="text-shadow:1px 1px 0 #444">REVIEWS</p>
    <#list reviews as review>
  	<div class="w3-row w3-container w3-border w3-margin-bottom">
      <p style="text-align:left;">${review.comment}
      <span style="float:right;">Score: ${review.rating}</span></p>
    </div>
    </#list>
  </div>
  <div class="w3-row w3-container w3-border w3-margin-top">
  	<h1 style="text-align:left;">PRICE: ${game.price}
    <span style="float:right;">RATING: ${game.rating}</span></h1>
  </div>
  <a href="/purchase/${game.id}" class="w3-button w3-red w3-margin-top w3-round-xxlarge w3-xxlarge w3-border w3-border-blue w3-hover-teal w3-center">Purchase Game
  </a>
</div>

</body>
</html>
