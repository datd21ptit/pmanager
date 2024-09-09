<?php

	require "connect.php";
	class Job{
		public $id;
		public $name;
		public $infor;
		public $userName;
		public $ddLine;
		public $isFinish;

	    public function __construct($id, $name, $infor, $userName, $ddLine, $isFinish) {
		    $this->name = $name;
		    $this->id = $id;
		    $this->infor = $infor;
		    $this->userName = $userName;
		    $this->ddLine = $ddLine;
		    $this->isFinish = $isFinish;
		}

	}

	$id_pj = $_POST['id_pj'];

	// $id_pj = '10';
	// $id_do = '44';
	// $name = 'Front-end';
	// $infor = 'Lam front-end cho trang web';
	// $deadline = '25/04/2024';
	// $isfinish = '0';

	if($conn->connect_error){
		die("connection failed");
	}

	$ArrayList =array();
	$query = "SELECT jobs.id_jb, jobs.name, jobs.info, jobs.ddline, jobs.isfinish, users.fullname FROM jobs JOIN users ON jobs.id_do = users.id WHERE FIND_IN_SET('$id_pj', id_pj)";
	$data = $conn->query($query);
	while($row = mysqli_fetch_assoc($data)){
		// array_push($ArrayList)
		array_push($ArrayList, new Job($row['id_jb'], $row['name'], $row['info'], $row['fullname'], $row['ddline'], $row['isfinish'], ));
	}

	die(json_encode($ArrayList, JSON_UNESCAPED_UNICODE));



?>