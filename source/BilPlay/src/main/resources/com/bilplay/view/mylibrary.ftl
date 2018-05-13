<html>
<head>
<style>

table, td, th{
	border: 1px solid black;
	border-collapse: collapse;
	height: 50px;
	width: 50%;
	padding: 30px;
	text-align: center;
	vertical-align: center;
	background-color: orange;
}

</style>
</head>

<body>
<table align=center>
	<tr>
		<th>Store</th>
		<th>My Library</th>
		<th>Profile</th>
		<th>Friends</th>
	</tr>
</table>

<hr>

<#list games as game>
<p>${game.name}</p>
</#list>
</body>
</html>
