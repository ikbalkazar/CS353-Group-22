<html>
<head>
<style>

.grid-container{
	display: grid;
	grid-template-column: auto auto auto;
	background-color: #2196F3;
	padding: 10px;
}

.grid-item{
	background-color: rgba(255, 255, 255, 0.8);
	border: 1px solid rgba(0, 0, 0, 0.8);
	padding: 10px;
	font-size: 20px;
	text-align: center;

}
</style>
</head>
<body>
<form action="/submit_signup" method="post">
	<div class = "container">
		
		<h1 style = "text-align:center; font-size:60px; background-color:DodgerBlue; color:White;">
			<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
			Sign Up Page to Bilplay
		</h1>
	</div>
	<div class = "grid-container">
		<div class = "grid-item">	
			<p>
				<label style = "color:Blue;" tabindex = "2"><b>Username</b></label>
				<input type="text" name="username" required>
			</p>
		</div>
		
		<div class = "grid-item">
			<p>
				<label style = "color:Blue;"><b>Email</b></label>
				<input type="email" name="email" required>
			</p>
		</div>
		<div class = "grid-item">
			<p>
				<label style = "color:Blue;"><b>Password</b></label>
				<input type="password" name="password" required>
			</p>
		</div>
		<div class = "grid-item">
			<p>
				<label style = "color:Blue;"><b>Re-type password</b></label>
				<input type="password" name="password2" required>
			</p>
		</div>
		<div class = "grid-item">
			<button type="submit">Sign Up</button>
		</div>
		<div class = "grid-item">
			<strong>${message}</strong>
		</div>
	    
	</div>
</form>

</body>
</html>
