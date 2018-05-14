<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#chat').animate({scrollTop: $('#chat').height()}, 0);
            var fn = function() {
                setTimeout(function() {
                    if ($('#message').is(':focus')) {
                        fn();
                    } else {
                        window.location.reload();
                    }
                }, 1000);
            };
            fn();
        });
    </script>
</head>
<body>
    <h1>Chat with ${friend.username}</h1>
    <div id="chat" style="overflow:scroll; height:400px;">
        <#list messages as message>
            <#if message.userId == friend.id>
                <p>@${friend.username}: ${message.content}</p>
            <#else>
                <p>@Me: ${message.content}</p>
            </#if>
        </#list>
    </div>
    <form action="/message/${friend.id}" method="post">
        <input id="message" type="text" name="message" required>
        <button type="submit">Send</button>
    </form>
</body>
</html>