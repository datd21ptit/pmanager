<?php
	require "connect.php";
	$Local_Url = "localhost/serverTTCS/";

	$id = $_POST['id'];
	$fullName = $_POST['name'];
	$email = $_POST['email'];
	$img = $_POST['image'];
	// $id = 29;
	// $fullName = "Boladat";
	// $email = "Boladat@gmaihahahahah";
	// $img = "imghahah";

	$query = "UPDATE users SET fullName = '$fullName', email = '$email', image = '$img' WHERE FIND_IN_SET('$id', id);";

	$data = $conn->query($query);
	// $query = "SELECT fullName FROM users WHERE FIND_IN_SET(29, id)"
	// $data = $conn->query($query);
	if($data){
		echo "Saved";
		// $row = mysql_fetch_assoc($data);
		// echo $row;
	}
?>