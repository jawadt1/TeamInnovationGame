<!doctype html>
<!--Confirms if an adoption request was made, or tells non-logged-in users that they must log in to make adoption requests-->
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

            <div id="mainText">
                <p style="padding-left: 10px; padding-right: 10px; text-align: center;">
                    <?php
                    if (isset($_SESSION['name'])) {
                        $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
                        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                        $animal = $_POST['animalID'];

                        $sesh_name = $db->quote($_SESSION['name']);

                        $userID = "SELECT * FROM user WHERE email = $sesh_name";

                        $quer_res = $db->query($userID);

                        $res = $quer_res->fetch();

                        $idNum = $res['UserID'];

                        $db->exec("INSERT INTO adoptionrequest (userID, animalID, approved) VALUES ($idNum, $animal, 'Pending')");
                        $db->exec("UPDATE animal SET adoptionStatus='Pending' WHERE animalID = $animal");
                        echo "Your adoption request has been put through.";
                    } else {
                        echo "You must be registered and logged in before you can make an adoption request.";
                    }
                    ?>
                </p>  
            </div>


        </div>
    </body>
</html>
