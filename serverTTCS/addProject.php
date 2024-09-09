<?php
	require "connect.php";

    $name = $_POST['name'];
    $id_creator = (int)$_POST['id_creator'];
	$deadline = $_POST['deadline'];
    $description = $_POST['des'];

    // $name = "name";
    // $id_creator = 29;
	// $deadline = "deadline]";
    // $description = "asc";


    $query = "INSERT INTO project VALUES (null,'$name', '$id_creator', '$description','$deadline')";
    $data = $conn->query($query);

    if($data){
    	die("Successfull");
    }else{
    	die("Fail");
    }

?>