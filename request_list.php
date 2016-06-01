<!doctype html>
<!--To a non-staff user, displays a table of their adoption requests-->
<!--To a staff user, shows all requests by all users, allows them to approve or deny those requests-->
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

                    <?php
                    if (isset($_SESSION['name'])) {
                        if ($_SESSION['usr_type'] == 1) {
                            staff();
                        } else {
                            nonStaff();
                        }
                    } else {
                        echo "You must be logged in to an account in order to see your adoption requests.";
                    }
                    ?>
                </p>  
            </div>
        </div>
    </body>
</html>

<?php
//Displays page for non-staff users, shows their adoption requests and the status of those requests
function nonStaff() {

    $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $quer = "SELECT * FROM animal";
    $quer_result = $db->query($quer);

    echo "<br/>";
    echo "<table cellpadding='10' style=\"margin-left:auto; margin-right:auto; border-collapse: collapse; width:80%\">";
    echo "<caption> Your Adoption Requests </caption> <tr>"
    . "<th>Photograph</th>"
    . "<th>Name</th> "
    . "<th>Request Status</th>"
    . "<th> View Animal</th>"
    . "</tr>";

    while ($row = $quer_result->fetch()) {

        $dispRow = false;

        $sesh_name = $db->quote($_SESSION['name']);
        $userID = "SELECT * FROM user WHERE email = $sesh_name";
        $quer_res = $db->query($userID);
        $res = $quer_res->fetch();
        $idNum = $res['UserID'];

        $quer_2 = "SELECT * FROM adoptionrequest WHERE userID=$idNum";
        $quer_result_2 = $db->query($quer_2);

        if ((strcasecmp($row['adoptionStatus'], 'available')) != 0) {
            while ($owns = $quer_result_2->fetch()) {
                if ($owns['animalID'] == $row['animalID']) {
                    $dispRow = true;
                }
                if ($dispRow) {
                    nonStaffTable($row, $owns);
                }
            }
        }
    }
    echo "</table>";
}

//Displays the version of the page staff see, all adoption requests, allows them to approve or deny those requests
//Bug present when approving/denying requests, completes all actions at once instead of per animal
function staff() {
    $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $quer = "SELECT * FROM animal";
    $quer_result = $db->query($quer);

    echo "<br/>";
    echo "<table cellpadding='10' style=\"margin-left:auto; margin-right:auto; border-collapse: collapse; width:80%\">";
    echo "<caption> Adoption Requests </caption> <tr>"
    . "<th>Photograph</th>"
    . "<th>Name</th> "
    . "<th>Request Status</th>"
    . "<th> User ID </th>"
    . "<th> View Animal</th>"
    . "<th> Update adoption request </th>"
    . "</tr>";

    while ($row = $quer_result->fetch()) {

        $quer_2 = "SELECT * FROM adoptionrequest";
        $quer_result_2 = $db->query($quer_2);

        if ((strcasecmp($row['adoptionStatus'], 'available')) != 0) {
            while ($owns = $quer_result_2->fetch()) {
                if ($owns['animalID'] === $row['animalID']) {
                    staffTable($row, $owns, $db);
                }
            }
        }
    }

    echo "</table>";
}

// Generates the animal table for non-staff users
function nonStaffTable($row, $row2) {
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
    echo '<td>' . $row2['approved'] . '</td>';
    echo '<td><form action="animal_information.php" method="post">'
    . '<input type="hidden" name="animal" value="' . $row['name'] . '"<br/>'
    . '<input type="submit" value="View this animal">'
    . '</form></td></tr>';
}


//Generates the animal table for staff users
function staffTable($row, $row2, $db) {
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
    echo '<td>' . $row2['approved'] . '</td>';
    echo '<td>' . $row2['userID'] . '</td>';
    echo '<td><form action="animal_information.php" method="post">'
    . '<input type="hidden" name="animal" value="' . $row['name'] . '"<br/>'
    . '<input type="submit" value="View this animal">'
    . '</form></td>';

    if ((strcasecmp($row2['approved'], 'approved') != 0) && (strcasecmp($row2['approved'], 'denied') != 0) ) {
        
        echo '<td><form action="" method="post">'
        . '<input type="hidden" name="submitted" value="true">'
        . '<select name="update">'
        . '<option value="Approved"> Approve</option>'
        . '<option value="Denied"> Reject</option>'
        . '</select>'
        . '<input type="submit" value="Update Request">'
        . '</form></td></tr>';

        if (isset($_POST['submitted'])) {

            $update = $db->quote($_POST['update']);
            $adop_ID = $db->quote($row2['adoptionID']);
            $animalID = $db->quote($row['animalID']);
            $adop_stat = $db->quote('Pending');
            $user_ID=$db->quote($row2['userID']);
            $change_owner=false;

            if ((strcasecmp($update, $db->quote('approved'))) == 0) {
                $adop_stat = $db->quote('Adopted');
                $change_owner=true;
            } elseif ((strcasecmp($update, $db->quote('denied')) == 0)) {
                $adop_stat = $db->quote('Available');
            }
            

            $db->exec("UPDATE adoptionrequest SET approved=$update WHERE adoptionID=$adop_ID");
            $db->exec("UPDATE animal SET adoptionStatus=$adop_stat WHERE animalID=$animalID");
            if($change_owner){
                $db->exec("UPDATE owns SET UserID=$user_ID WHERE AnimalID=$animalID");
            }
            
            
            echo "<meta http-equiv=\"Refresh\" content=\"0\" URL=\"request_list.php\">";
            
        }
    }
}
?>

