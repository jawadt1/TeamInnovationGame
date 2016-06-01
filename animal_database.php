<!doctype html>
<!--Displays a table of animals in the database that the user has permissions to see-->
<!--Non-staff users can only see available animals and animals with pending adoption requests-->
<!--Staff users can additionally see animals that have been adopted-->
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

            #age{
                text-align: right;
            }
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

                <form action="animal_database.php" method="post">
                    <label> Animal search:  </label>
                    <input type="text" name="criteria">
                    <input type="hidden" name="submitted" value="true">
                    <select name="search">
                        <option value="name"> Name</option>
                        <option value="age"> Age</option>
                        <option value="species"> Species</option>
                        <option value="animalID"> ID number</option>
                    </select>
                    <input type="submit" value="Search">

                </form>

                <?php
                $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
                $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                $quer = "SELECT * FROM animal";


                $quer_result = $db->query($quer);

                echo "<br/>";
                echo "<table cellpadding='10' style=\"margin-left:auto; margin-right:auto; border-collapse: collapse; width:80%\">";
                echo "<tr>"
                . "<th>Photograph</th>"
                . "<th>Name</th> "
                . "<th>Age (In Years)</th>";
                
                if(isset($_SESSION['name'])){
                    if ($_SESSION['usr_type'] == 1) {
                        echo "<th> Owner User ID </th>";
                    }
                }
                
                echo "<th> View Animal</th>";
                echo "</tr>";

                if (!isset($_POST['submitted'])) {
                    nonStaffTable($quer_result);
                } else {
                    searchTable($quer_result, $db);
                    
                }
                ?>
                </p>  
            </div>
        </div>
    </body>
</html>
<?php

// Generates the table entries using tableGen, but restricts which animals are shown for non-staff
function nonStaffTable($quer_result) {

    while ($row = $quer_result->fetch()) {
        if (isset($_SESSION['name'])) {
            if ($_SESSION['usr_type'] == 0) {
                if ((strcasecmp($row['adoptionStatus'], 'adopted')) != 0) {
                    tableGen($row);
                }
            } else {
                tableGen($row);
            }
        } else {
            if ((strcasecmp($row['adoptionStatus'], 'adopted')) != 0) {
                tableGen($row);
            }
        }
    }
    echo "</table>";
}
// Re-generates the table, but restricts entries to those which meet search criteria
function searchTable($quer_result, $db) {

    $option = $_POST['search'];
    
    $san_search = $db->quote($_POST['criteria']);


    while ($row = $quer_result->fetch()) {
        
        $disp_row = false;
        
        if ($option == 'age') {
            $age = date_diff(date_create($row['dateofbirth']), date_create('now'))->y;
            if ((strcasecmp($san_search, $db->quote($age))) == 0) {
                $disp_row = true;
            }
        } elseif ((strcasecmp($san_search, $db->quote($row[$option]))) == 0) {
            $disp_row = true;
        }
        
        if (isset($_SESSION['name'])) {
            if ($_SESSION['usr_type'] == 0) {
                if ((strcasecmp($row['adoptionStatus'], 'adopted')) == 0) {
                    $disp_row = false;
                }
            }
        } else {
            if ((strcasecmp($row['adoptionStatus'], 'adopted')) == 0) {
                $disp_row = false;
            }
        }
        if ($disp_row) {
                tableGen($row);
            }
            else{
            }
    }
    echo "</table>";
}
// Generates the table entries for all animals
function tableGen($row) {
    if ($row['species'] == 'Dog') {
        $default_image = 'images/our_animals/default_dog.jpg';
    } elseif ($row['species'] == 'Cat') {
        $default_image = 'images/our_animals/default_cat.jpg';
    } elseif ($row['species'] == 'Rabbit') {
        $default_image = 'images/our_animals/default_rabbit.jpg';
    } else {
        $default_image = 'images/our_animals/default_other.jpg';
    }
    echo '<tr><td>' . '<img src="' . $row['photograph'] . "\" onerror=\"this.src='$default_image'\" alt=\"Sorry this picture is unavailable\" style=\"height:150px; margin-left:10px;\" >" . '</td>';
    echo '<td>' . $row['name'] . '</td>';
    $age = date_diff(date_create($row['dateofbirth']), date_create('now'))->y;
    echo '<td id=\"age\">' . $age . '</td>';
    
    
    if (isset($_SESSION['name'])) {
        if ($_SESSION['usr_type'] == 1) {

            $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
            $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            
            $an_ID=$db->quote($row['animalID']);

            $quer="SELECT * FROM owns WHERE AnimalID=$an_ID";
            $quer_res=$db->query($quer);
            $res=$quer_res->fetch();
            $ownerID=$res['UserID'];
            
            echo "<td> $ownerID </td>";
        }
    }




    //Takes user to a page showing detailed information about the specific animal
    echo '<td><form action="animal_information.php" method="post">'
    . '<input type="hidden" name="animal" value="' . $row['name'] . '"<br/>'
    . '<input type="submit" value="View this animal">'
    . '</form></td></tr>';
}
