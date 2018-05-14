<!DOCTYPE html>
<html>
<title>Store</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
<body class="w3-content" style="max-width:1200px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide"><b>SEARCH</b></h3>
  </div>
  <form action="/store_search" method="POST">
  	<fieldset id=genre>
    	<legend>Genre:</legend>
  		<input type="radio" name="genre" value="FPS"> FPS<br>
        <input type="radio" name="genre" value="MOBA"> MOBA<br>
        <input type="radio" name="genre" value="Arcade"> Arcade<br>
        <input type="radio" name="genre" value="Action"> Action<br>
        <input type="radio" name="genre" value="RPG"> RPG<br>
    </fieldset>
    <fieldset id=price>
    	<legend>Price Range:</legend>
  		<input type="radio" name="price" value="0"> Free to Play<br>
        <input type="radio" name="price" value="1"> Below 5TL<br>
        <input type="radio" name="price" value="2"> 5TL to 10TL<br>
        <input type="radio" name="price" value="3"> 10TL to 15TL<br>
        <input type="radio" name="price" value="4"> 15TL to 20TL<br>
    </fieldset>
    <fieldset id=rating>
    	<legend>Rating Range:</legend>
  		<input type="radio" name="rating" value="0"> 0 to 2.5<br>
        <input type="radio" name="rating" value="1"> 2.5 to 5.0<br>
        <input type="radio" name="rating" value="2"> 5.0 to 7.5<br>
        <input type="radio" name="rating" value="3"> 7.5 to 10.0<br>
    </fieldset>
    <input type="submit" value="Search">
  </form>
</nav>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

  <!-- Top header -->
  <header class="w3-container w3-xlarge">
    <p class="w3-left">STORE</p>
  </header>

  <!-- Product grid -->
    <#list games as game>
    <div class="w3-col l3 s6">
      <div class="w3-container">
        <a href="/games/${game.id}"><img src="${game.pic}" style="width:100%" height="140"></a>
        <p style="text-align:left;">${game.name}<br> Price: ${game.price}
		    <span style="float:right;">Rating: ${game.rating}</span>
		    </p>
      </div>
    </div>
    </#list>
</div>

<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>
