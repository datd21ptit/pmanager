<?php

	require "connect.php";
	class Job{
		public $id;
		public $name;
		public $infor;
		public $userName;
		public $ddLine;
		public $isFinish;

	    public function __construct($id,$name, $infor, $userName, $ddLine, $isFinish) {
		    $this->name = $name;
		    $this->infor = $infor;
		    $this->userName = $userName;
		    $this->ddLine = $ddLine;
		    $this->isFinish = $isFinish;
		    $this->id = $id;
		}

	}

	$id_pj = $_POST['id'];
	// $id_pj = '7';

	// $id_pj = '10';
	// $id_do = '44';
	// $name = 'Front-end';
	// $infor = 'Lam front-end cho trang web';
	// $deadline = '25/04/2024';
	// $isfinish = '0';

	if($conn->connect_error){
		die("connection failed");
	}

	
	$query = "UPDATE jobs set isfinish = 1 WHERE id_jb = '$id_pj'";
	$data = $conn->query($query);
	if($data){
		echo "true";
	}else{
		echo "false";
	}



?>