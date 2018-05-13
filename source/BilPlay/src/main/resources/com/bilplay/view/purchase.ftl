<html>
<body>
<h1>${game.name}</h1>
<p>${game.description}</p>
<p>Balance: ${user.budget}</p>
<p>Cost:  ${game.price}</p>
<form action="/make_purchase/${game.id}" method="post">
    <button type="submit">Purchase</button>
</form>
</body>
</html>