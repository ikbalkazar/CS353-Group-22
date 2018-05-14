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
    width: 20%;
    padding: 10px;
    height: 600px; /* Should be removed. Only for demonstration */
}

.column1 {
    float: left;
    width: 60%;
    padding: 10px;
    height: 600px; /* Should be removed. Only for demonstration */
    border: 2px solid black;
}

.column2 {
    float: left;
    width: 15%;
    padding: 10px;
    height: 290px; /* Should be removed. Only for demonstration */
}

.column3 {
    float: left;
    width: 70%;
    padding: 10px;
    height: 290px; /* Should be removed. Only for demonstration */
}
.column4 {
    float: left;
    width: 35%;
    height: 290px; /* Should be removed. Only for demonstration */
}
.column5 {
    float: left;
    width: 5%;
    height: 290px; /* Should be removed. Only for demonstration */
}
.column6 {
    float: left;
    width: 30%;
    height: 290px; /* Should be removed. Only for demonstration */
}
.column7 {
    float: left;
    width: 30%;
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
    width:870px;
    /*border:1px solid #000;*/
    height:100%;
}

.nameChangeBox{
    background-color: white;
    width: 300px;
    border: 3px solid black;
    padding: 30px;
    margin: 30px 0px;
}

.nameChangeBox2{
    background-color: white;
    width: 230px;
    border: 3px solid black;
    padding: 40px;
    margin: 30px 15px;
}

.nameChangeBox3{
    background-color: grey;
    width: 600px;
    border: 3px solid black;
    padding: 0px;
    margin: 20px 0px;
}
</style>
</head>

<body>
<table align=center>
    <tr>
    <td><b>BILPLAY</b></td>
    </tr>
</table>
<table align=center>
	<tr>
		<th><a href="/store">Store</a></th>
		<th><a href="/MyLibrary">My Library</a></th>
		<th><a href="/profile">Profile</a></th>
		<th><a href="/friends">Friends</a></th>
		<th><a href="/logout">Log Out</a></th>
	</tr>
</table>

<hr>
<div class = "column0" style = "background-color:white" ></div>
<div class = "column1" style = "background-color:orange" >

    <div class='wrap'>

        <div class = "blocks" style = "background-color:white; height:50%">
   	        <div class = "column2" style = "background-color:white;"></div>
   	        <div class = "column4" style = "background-color:white;">
                <div class = "nameChangeBox">
                    <form action="/nameChange" method="post">
                            <p><b>Change Name</b></p>
                            <label><b>First Name</b></label>
                            <input type="text" placeholder="Enter First Name" name="firstName" required>
                            <p></p>
                            <label><b>Last Name</b></label>
                            <input type="text" placeholder="Enter Last Name" name="lastName" required>

                             <button type="submit">Save Changes</button>

                    </form>
                </div>
            </div>
   	        <div class = "column5" style = "background-color:white;"></div>
   	        <div class = "column6" style = "background-color:white;">
   	            <div class = "nameChangeBox2">
   	            <p><b>Wallet</b></p>
   	            <p>Your Budget : ${budget}</p>

                <form action="/addBudget" method="post">
                    <input type=number step=0.01 placeholder="Enter amount" name="budget" required />
                    <button type="submit">Add Funds</button>
                </form>
                </div>
   	        </div>
   	        <div class = "column2" style = "background-color:white;"></div>
        </div>
   	    <div class = "blocks" style = "background-color:yellow; height:50%">
   	        <div class = "column2" style = "background-color:white;"></div>
   	        <div class = "column3" style = "background-color:white;">
   	            <div class = "nameChangeBox3">
                    <form action="/passwordChange" method="post">
                        <p align="center"><b>Change Password</b></p>
                        <p align="center"><b>Old Password</b>
                        <input type="password" placeholder="Enter Old Password" name="password" required>
                        </p>
                        <p align="center"><b>New Password</b>
                        <input type="password" placeholder="Enter New Password" name="newPassword" required>
                        </p>
                        <p align="center"><b>New Password again</b></label>
                        <input type="password" placeholder="Enter New Password again" name="reNewPassword" required>
                        </p>
                        <p align="center"><button type="submit" align="center">Save Changes</button>
                        </p>
                        <p align="center"><strong>${message}</strong></p>
                        </form>
                </div>
   	        </div>
   	        <div class = "column2" style = "background-color:white;"></div>
	    </div>
    </div>
</div>
<div class = "column8" style = "background-color:white" ></div>
</body>
</html>
