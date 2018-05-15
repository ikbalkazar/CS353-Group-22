<!DOCTYPE html>
<html lang="en">
<head>
    <title>Friends</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        th {
            width: 100px;
        }
    </style>
</head>
<body>
<table align=center>
    <tr>
        <td><b>BILPLAY</b></td>
    </tr>
</table>
<table align=center cellpadding="10">
    <tr>
        <th><a href="/store">Store</a></th>
        <th><a href="/MyLibrary">My Library</a></th>
        <th><a href="/profile">Profile</a></th>
        <th><a href="/friends">Friends</a></th>
        <th><a href="/logout">Log Out</a></th>
    </tr>
</table>
<div class="container">
    <h2>Friends</h2>
    <h3>Add Friend</h3>
    <form action="/add_friend" method="post">
        <label>Enter username</label>
        <input type="text" name="username">
        <button type="submit">Add</button>
    </form>
    <br><br>
    <h2>Current Friends</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Username</th>
        </tr>
        </thead>
        <tbody>
            <#list friends as friend>
                <tr>
                    <td>${friend.username}</td>
                    <td>
                        <form action="/delete_friend/${friend.id}" method="post">
                            <button type="submit">Delete</button>
                        </form></td>
                    <td><a href="/chat/${friend.id}">Chat</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
    <br><br>
    <h2>Invitations</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Username</th>
            <th>Game</th>
        </tr>
        </thead>
        <tbody>
        <#list invites as invite>
            <tr>
                <td>${invite.inviter.username}</td>
                <td>${invite.game.name}</td>
                <td><a href="/join/${invite.invite.sessionId}">Accept</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>
