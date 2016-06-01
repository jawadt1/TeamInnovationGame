<?php
//Destroys all session information for user, effectively logging them out
session_start();
session_destroy();

header("Location: homepage.php");
?>