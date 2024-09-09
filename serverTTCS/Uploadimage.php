<?php
	require "connect.php";
	$Local_Url = "localhost/serverTTCS/";

	// $id = 32;

	$file_path = "image/";
	$file_path = $file_path.basename($_FILES['uploaded_file']['name']);
	
	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)){

		// $file_path = $Local_Url.file_path;

		echo $_FILES['uploaded_file']['name'];
	}else{
		echo "FAILED";
	}


?>