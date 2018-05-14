<html>
<body>
<h1>Playing ${game.name}</h1>
<h2>Friends</h2>
<#list friends as friend>
    <h3>${friend.username}</h3>
    <#if session.creatorId == user.id>
        <a href="/invite/${session.id}/${friend.id}">Invite</a>
    </#if>
</#list>
<h2>Joined Users</h2>
<#list users as user>
<h3>${user.username}</h3>
</#list>
<a href="/MyLibrary">Leave</a>
</body>
</html>