<!doctype html>
<!--Allows staff to upload the details and an image of a new animal to the database-->
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
                        Add a new animal to the database

                    <form action="add_new_animal.php" method="post" enctype="multipart/form-data">

                        <label> Animal name:</label><br/>
                        <input type="text" name="name"><br/>
                        <label> Date of Birth:</label><br/>
                        <input type="text" name="dob" placeholder="dd-mm-yyyy"><br/>
                        <label> Description:</label><br/>
                        <input type="text" name="description" id="description"><br/>
                        <label> Photograph Upload:</label><br/>
                        <input type="file" name="animal_photo" id="photo"><br/>
                        <label> Species:</label><br/>
                        <select name="species">
                            <option value="Dog"> Dog</option>
                            <option value="Cat"> Cat</option>
                            <option value="Rabbit"> Rabbit</option>
                            <option value="Other"> Other</option>
                        </select>
                        <input type="hidden" name="submitted" value="true"><br/>
                        <input type="submit" value="Add New Animal">

                    </form>
                    <?php
                    /* Verifies if the image a user is trying to upload is acceptable
                     * Specifically the code checks that:
                     * The file is actually an image
                     * The image is not already present in the images folder
                     * The size of the image is not larger than ~1MB
                     * The image is of an acceptable file type
                     */
                    try {
                        if (isset($_POST['submitted'])) {

                            $upload_directory = "images/our_animals/";
                            $file_to_upload = $upload_directory . basename($_FILES["animal_photo"]["name"]);

                            $successful = true;
                            $imageFileType = pathinfo($file_to_upload, PATHINFO_EXTENSION);


                            if (isset($_POST["submit"])) {
                                $check = getimagesize($_FILES["animal_photo"]["tmp_name"]);
                                if ($check !== false) {
                                    echo "File is an image - " . $check["mime"] . ".";
                                    $successful = false;
                                } else {
                                    echo "File is not an image.";
                                    $successful = false;
                                }
                            }

                            if (file_exists($file_to_upload)) {
                                echo "That file is already present in the image folder";
                                $successful = false;
                            }

                            if ($_FILES["animal_photo"]["size"] > 1024000) {
                                echo "Sorry, your file is too large. Files must be under 1mb size.";
                                $successful = false;
                            }
                            if ($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg" && $imageFileType != "gif") {
                                echo "Sorry, that file format is not allowed, please use: JPG, JPEG, PNG or GIF";
                                $successful = false;
                            }
                            //If the image passed all checks, it is uploaded to the images folder
                            if (!$successful) {
                                echo "The upload process has been cancelled, please try again";
                            } else {
                                if (move_uploaded_file($_FILES["animal_photo"]["tmp_name"], $file_to_upload)) {
                                    echo "Animal photograph was successfully uploaded";
                                } else {
                                    echo "File upload was not successful";
                                }
                            }
                            
                            // If the image was successfully uploaded, the new animal information is passed to the database
                            if ($successful) {
                                $db = new PDO("mysql:dbname=delanach_db;host=localhost", "delanach", "leas45loll");
                                $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);


                                $animal_name = $_POST['name'];
                                $DoB = $_POST['dob'];
                                $sql_dob = strtotime($DoB);
                                $dob_format = $db->quote(date('o-m-d', $sql_dob));



                                if ($_POST['description'] != null) {
                                    $description = $_POST['description'];
                                    $san_desc = $db->quote($description);
                                } else {
                                    $san_desc = " ";
                                }

                                if ($_FILES['animal_photo'] != null) {
                                    $san_photo = $db->quote($file_to_upload);
                                } else {
                                    $san_photo = "images/our_animals/default_" . $_POST['species'];
                                }

                                $species = $_POST['species'];
                                $san_species = $db->quote($species);

                                $san_name = $db->quote($animal_name);

                                $db->exec("INSERT INTO animal (name, dateofbirth, description, photograph, species, adoptionStatus) VALUES ($san_name, $dob_format, $san_desc, $san_photo, $san_species, 'Available')");

                                $quer = "SELECT * FROM animal WHERE animalID=(SELECT MAX(animalID) FROM animal)";
                                $quer_result = $db->query($quer);
                                $return_row = $quer_result->fetch();
                                $id_to_add = $return_row['animalID'];

                                $sesh_name = $db->quote($_SESSION['name']);
                                $userID = "SELECT * FROM user WHERE email = $sesh_name";
                                $quer_res = $db->query($userID);
                                $res = $quer_res->fetch();
                                $idNum = $res['UserID'];

                                $db->exec("INSERT INTO owns (userID, animalID) VALUES ($idNum, $id_to_add)");
                                echo '<br/>New animal successfully added!';
                            }
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

