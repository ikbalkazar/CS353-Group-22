<html>
<body>
<h1>Friends</h1>
<#list friends as friend>
<h3>${friend.username}</h3>
<form action="/delete_friend" method="post">

</form>
</#list>
</body>
</html>