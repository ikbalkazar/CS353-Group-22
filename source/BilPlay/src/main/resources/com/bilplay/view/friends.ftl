<html>
<body>
<h1>Friends</h1>
<h2>Add Friend</h2>
<form action="/add_friend" method="post">
    <label>Enter username</label>
    <input type="text" name="username">
    <button type="submit">Add</button>
</form>
<h2>Friends</h2>
<#list friends as friend>
<h3>${friend.username}</h3>
<form action="/delete_friend/${friend.id}" method="post">
    <button type="submit">Delete</button>
</form>
<a href="/chat/${friend.id}">Chat</a>
</#list>
</body>
</html>