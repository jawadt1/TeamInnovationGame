<!doctype html>
<!--Allows a user to register an account for the website so that they may log in-->
<html>
    <head>
        <meta charset="utf-8">
        <title>Aston Animal Sanctuary</title>
        <link rel="stylesheet" type="text/css" href="animal_sanctuary_style.css">
    </head>
    <body>
        <div id="page_content">

            <div id="banner">
                <h1 id ="Company_name" > Aston Animal Sanctuary </h1>
                <p id ="site_links"> Links: 
                    <a href="homepage.php" id="home"> Home </a> 
                    <a href="animal_database.php" id="animalList"> Animal List </a> 
                    <a href="about_us.php" id="aboutUs"> About Us </a> 
                    <?php
                    session_start();
                    //Determines if user is logged in and what permissions they have, displays relevant links in hotbar
                    if (isset($_SESSION['name'])) {
                        
                        echo "<a href=\"logout.php\" style=\"text-decoration: none; color:#FFFFFF; float:right;\"> Logout </a>";
                        if ($_SESSION['usr_type'] == 1) {
                            echo "<a href=\"request_list.php\" style=\"text-decoration: none; color:#FFFFFF;\"> Adoption Requests </a>";
                            echo "<a href=\"add_new_animal.php\" style=\"text-decoration: none; color:#FFFFFF; margin-left:10px;\"> Add a new animal </a>";
                        } else {
                            echo "<a href=\"request_list.php\" style=\"text-decoration: none; color:#FFFFFF;\"> Your Adoption Requests </a>";
                        }
                    } else {
                        echo "<a href=\"login_page.php\" style=\"text-decoration: none; color:#FFFFFF; float:right;\"> Login/Register </a>";
                    }
                    ?>
                </p>
            </div>

            <div id="images">
                <img src="images/frenchies.jpg" alt="Sorry this picture is unavailable"
                     style="height:200px; margin-top:10px; margin-left: 25px;">
                <img src="images/bunny.jpg" alt="Sorry this picture is unavailable"
                     style="height:200px; margin-top:10px">
                <img src="images/kitten.jpg" alt="Sorry this picture is unavailable"
                     style="height:200px; margin-top:10px">
                <img src="images/parrots.jpg" alt="Sorry this picture is unavailable"
                     style="height:200px; margin-top:10px">
            </div>

            <div id="side_banner">
                <p id="news_tag"> Aston Animal Sanctuary News </p>
                <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco 
                    laboris nisi ut aliquip ex ea commodo consequat. 
                    Duis aute irure dolor in reprehenderit in voluptate velit 
                    esse cillum dolore eu fugiat nulla pariatur.
                </p>
                <img src="images/cat.png" alt="Sorry this picture is unavailable"
                     style="width: 180px; margin-left:10px; margin-right:10px; ">
            </div>

            <div id="login_area">
                <div id="login_text">
                    <p style="padding-left: 10px; padding-right: 10px; text-align: center;">
                        Aston Animal Sanctuary Registration Page.

                    <form id="registration_form" action="registration_page.php" method="post">

                        <label> Email Address:</label><br/>
                        <input type="text" name="email"><br/>
                        <label> Password:</label><br/>
                        <input type="password" name="password"><br/>
                        <input type="hidden" name="submitted" value="true"><br/>
                        <input type="submit" value="Register">

                    </form>
                    <?php
                    try {
                        if (isset($_POST['submitted'])) {
                            $email = $_POST['email'];
                            $password = $_POST['password'];

                            $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
                            $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                            $san_email = $db->quote($email);
                            $hashed_pass = md5($password);
                            $san_password = $db->quote($hashed_pass);


                            $db->exec("INSERT INTO user (staff, password, email) VALUES ('0', $san_password, $san_email)");

                            echo '<br/>Registration was successful!';
                            echo '<form action="login_page.php" method="post">';
                            echo '<input type="submit" value="Go to login page"></form>';
                        }
                    } catch (PDOException $ex) {
                        ?>
                        <p>An error has occurred with the database, please try again or contact your system administrator</p>
                        <p>(Error details: <?= $ex->getMessage() ?>)</p>
                        <?php
                    }
                    ?>


                    </p>
                    <br/>
                </div>
            </div>
        </div>
    </body>
</html>
