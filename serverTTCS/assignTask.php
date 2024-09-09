<?php

	require "connect.php";
	
	$id_pj = $_POST['id_pj'];
	$id_do = $_POST['id_do'];
	$name = $_POST['name'];
	$infor = $_POST['infor'];
	$deadline = $_POST['deadline'];
	$isfinish = false;

	// $id_pj = '10';
	// $id_do = '44';
	// $name = 'Front-end';
	// $infor = 'Lam front-end cho trang web';
	// $deadline = '25/04/2024';
	// $isfinish = '0';

	if($conn->connect_error){
		die("connection failed");
	}


	$query = "INSERT INTO jobs VALUES (null,'$id_pj', '$id_do', '$name', '$infor', '$deadline', '$isfinish')";
	$data = $conn->query($query);
	if($data){
		echo "Inserted";
	}else{
		echo "Failed";
	}


?>