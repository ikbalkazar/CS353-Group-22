<html>
<head>
<style>

::-webkit-scrollbar {
    width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
    background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
    background: #888;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555;
}

*{
    box-sizing: border-box;
}

.column0 {
    float: left;
    width: 10%;
    padding: 10px;
    height: 600px; /* Should be removed. Only for demonstration */
}

.column1 {
    float: left;
    width: 15%;
    padding: 10px;
    height: 600px; /* Should be removed. Only for demonstration */
    border: 2px solid black;
}

.column2 {
    float: left;
    width: 60%;
    padding: 10px;
    height: 600px; /* Should be removed. Only for demonstration */
}

.column3 {
    float: left;
    width: 30%;
    padding: 10px;
    height: 175px; /* Should be removed. Only for demonstration */
}
.column4 {
    float: left;
    width: 70%;
    height: 175px; /* Should be removed. Only for demonstration */
}
.column5 {
    float: left;
    width: 12%;
    height: 175px; /* Should be removed. Only for demonstration */
}
.column6 {
    float: left;
    width: 38%;
    height: 175px; /* Should be removed. Only for demonstration */
}
.column7 {
    float: left;
    width: 50%;
    height: 175px; /* Should be removed. Only for demonstration */
}
.column8 {
    float: left;
    width: 20%;
    height: 175px; /* Should be removed. Only for demonstration */
}

.button {
    background-color: #4CAF50;
    border: 2px solid black;
    color: black;
    padding: 50px 45px;
    text-align: center;
    font-size: 25px;
    cursor: pointer;
    margin: 10px 60px;
    background-color: white;
}

.button2 {
    background-color: #4CAF50;
    border: 2px solid black;
    color: black;
    padding: 10px 20px;
    text-align: center;
    font-size: 8px;
    cursor: pointer;
    margin: 15px 5px;
    background-color: white;
}

.button3 {
    background-color: #4CAF50;
    border: 2px solid black;
    color: black;
    padding: 10px 20px;
    text-align: center;
    font-size: 8px;
    cursor: pointer;
    margin: -5px 5px;
    background-color: white;
}

table, td, th {
    border: 1px solid black;
    border-collapse: collapse;
    height: 15px;
    width: 50%;
    padding: 20px;
    text-align: center;
    vertical-align: center;
}

.wrap{
    width:900px;
    /*border:1px solid #000;*/
    height:100%;
}

</style>
</head>

<body>
<table align=center>
	<tr>
		<th><a href="/games/1">Store</a></th>
		<th><a href="/MyLibrary">My Library</a></th>
		<th><a href="/profile">Profile</a></th>
		<th><a href="/friends">Friends</a></th>
		<th><a href="/logout">Log Out</a></th>
	</tr>
</table>

<hr>
<div class >
    <div class="column0"></div>
	<div class="column1" style = "background-color:white; overflow:scroll">
        <#list games as game>
            <p><a href="/MyLibrary?game_id=${game.id}"> <font size = "4">${game.name}</font> </a> </p>
        </#list>

    </div>
    <div class="column2" style = "background-color:purple;">
    	<div class='wrap'>
    		<div class = "blocks" style = "background-color:white; height:70%">

            </div>

    		<div class = "blocks" style = "background-color:white; height:30%">
    		    <div class = "column3" style = "background-color:orange;">
					<button class="button"><a href="/create_session/${game.id}">Play</a></button>
    		    </div>
    		    <div class = "column4" style = "background-color:white;">
    		        <div class = "column5" style = "background-color:white;"></div>
    		        <div class = "column6" style = "background-color:white;">
    		            <p><font size = "3"> Time Played : ${timePlayed} Hours</font></p>
    		        </div>
    		        <div class = "column7" style = "background-color:white;">
                        <form action="/postReview?game_id=${firstGame}" method="post">
                            <div class = "grid-container">
                                <div class = "grid-item">
                                    <p>
                                        <input type="text" name="review" maxlength="100" placeholder="Review" required>
                                    </p>
                                </div>

                                <div class = "grid-item">
                                    <p>
                                        <input type="number" name="rating" min="1" max="10" placeholder="Rating" required>
                                    </p>
                                </div>
                                <div class = "grid-item">
                                    <button class="button2" type="submit">Post Review</button>
                                </div>
                            </div>
                        </form>






    		        </div>
    		    </div>
            </div>
		</div>

    </div>
</div>

</body>
</html>
