<!DOCTYPE html>
<html lang="en">
<head>
    <title>Playing ${game.name}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Playing ${game.name}</h1>
    <br>
    <a href="/leave/${session.id}">Leave</a>
    <br><br>
    <h2>Friends</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Username</th>
        </tr>
        </thead>
        <tbody>
            <#list friends as friend>
                <tr><td>${friend.user.username}
                    <td>
                        <#if session.creatorId == user.id>
                            <#if !friend.invited >
                                <a href="/invite/${session.id}/${friend.user.id}">Invite</a>
                            <#else>
                                Invited
                            </#if>
                        </#if>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    <br><br>
    <h2>Joined Users</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Username</th>
        </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr><td>${user.username}</td></tr>
            </#list>
        </tbody>
    </table>
</div>

</body>
</html>
