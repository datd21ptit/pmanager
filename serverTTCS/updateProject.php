<?php
	require "connect.php";
	$Local_Url = "localhost/serverTTCS/";

	$id = (int) $_POST['id'];
	$name = $_POST['name'];
	$deadline = $_POST['deadline'];
	$description = $_POST['description'];

	// $id = 10;
	// $name ='Web ban hang';
	// $deadline = '15/12/2024';
	// $description = 'Xay dung web ban hang cho anh N';

	$query = "UPDATE project SET name = '$name', deadline = '$deadline', info = '$description' WHERE FIND_IN_SET('$id', id_pj);";

	$data = $conn->query($query);
	// $query = "SELECT fullName FROM users WHERE FIND_IN_SET(29, id)"
	// $data = $conn->query($query);
	if($data){
		echo "Saved";
		// $row = mysql_fetch_assoc($data);
		// echo $row;
	}else{
		echo "F";
	}
?>