<html>
<body>
<h1>${game.name}</h1>
<p>${game.description}</p>
<h2>Reviews</h2>
<#list reviews as review>
<h3>${review.gameId}</h3>
<p>${review.comment}</p>
</#list>
<a href="/purchase/${game.id}">Purchase</a>
</body>
</html>
