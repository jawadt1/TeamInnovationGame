<!doctype html>
<!--Displays detailed information about a specific animal-->
<html>
    <head>
        <meta charset="utf-8">
        <title>Aston Animal Sanctuary</title>
        <link rel="stylesheet" type="text/css" href="animal_sanctuary_style.css">
        <style>
            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            tr:hover{background-color:#f9b9e7}

        </style>
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

            <div id="mainText" style="border-style: none">
                <p style="padding-left: 10px; padding-right: 10px; text-align: center;">
                    <?php
                    $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
                    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                    $ani_lookup = $db->quote($_POST['animal']);

                    $quer = "SELECT * FROM animal WHERE name =" . $ani_lookup;

                    $quer_result = $db->query($quer);


                    echo "<table cellpadding='10' style=\"margin-left:auto; margin-right:auto; border-collapse: collapse; width:80%\">";

                    while ($row = $quer_result->fetch()) {

                        if ($row['species'] == 'Dog') {
                            $default_image = 'images/our_animals/default_dog.jpg';
                        } elseif ($row['species'] == 'Cat') {
                            $default_image = 'images/our_animals/default_cat.jpg';
                        } elseif ($row['species'] == 'Rabbit') {
                            $default_image = 'images/our_animals/default_rabbit.jpg';
                        } else {
                            $default_image = 'images/our_animals/default_other.jpg';
                        }
                        echo '<tr><th> Photograph: </th>';
                        echo '<td><img src="' . $row['photograph'] . "\" onerror=\"this.src='$default_image'\" alt=\"Sorry this picture is unavailable\" style=\"height:400px; margin-left:10px;\" >" . '</td></tr>';
                        echo '<tr><th>Name:</th><td>' . $row['name'] . '</td></tr>';
                        echo '<tr><th>Animal ID:</th><td>' . $row['animalID'] . '</td></tr>';
                        echo '<tr><th> Description: </th><td>' . $row['description'] . '</td></tr>';
                        echo '<tr><th>Date of Birth: </th><td>' . $row['dateofbirth'] . '</td></tr>';
                        $age = date_diff(date_create($row['dateofbirth']), date_create('now'))->y;
                        echo '<tr><th> Age: </th><td id=\"age\">' . $age . '</td></tr>';
                        echo '<tr><th>Adoption Status:</th><td>' . $row['adoptionStatus'] . '</td></tr>';
                        
                        if((strcasecmp($row['adoptionStatus'],'adopted')) != 0 ){
                            echo '<tr><td colspan="2"><form action="adoption_request.php" method="post">'
                            . '<input type="hidden" name="animalID" value="' . $row['animalID'] . '"<br/>'
                            . '<input type="submit" value="Request to adopt this animal">'
                            . '</form></td></tr>';
                            echo "</tr>";
                        } else {
                            echo '<tr>' .  '</tr>';
                        }
                    }
                    echo "</table>";
                    ?>
                </p>  
            </div> 
        </div>
    </body>
</html>

