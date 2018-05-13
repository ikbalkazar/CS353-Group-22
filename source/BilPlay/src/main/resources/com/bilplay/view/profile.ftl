<html>
<body>
    <h1><b>Your Budget : ${budget}</b></h1>

    <form action="/addBudget" method="post">
        <div class="container">
            <input type=number step=0.01 placeholder="Enter amount" name="budget" required />
            <button type="submit">Add Funds</button>

        </div>
    </form>

    <form action="/nameChange" method="post">
        <div class="container">
            <label><b>First Name</b></label>
            <input type="text" placeholder="Enter First Name" name="firstName" required>

            <label><b>Last Name</b></label>
            <input type="text" placeholder="Enter Last Name" name="lastName" required>

            <button type="submit">Save Changes</button>
        </div>
    </form>

    <form action="/passwordChange" method="post">
        <div class="container">
            <label><b>Change Password</b></label>
            <label><b>Old Password</b></label>
            <input type="password" placeholder="Enter Old Password" name="password" required>

            <label><b>New Password</b></label>
            <input type="password" placeholder="Enter New Password" name="newPassword" required>

            <label><b>New Password again</b></label>
            <input type="password" placeholder="Enter New Password again" name="reNewPassword" required>


            <button type="submit">Save Changes</button>

            <p></p>

            <strong>${message}</strong>

        </div>
    </form>

</body>
</html>