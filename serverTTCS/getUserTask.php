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
	// $id_pj = 54;

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
	$query = "SELECT jobs.id_jb, jobs.name, jobs.info, jobs.ddline, jobs.isfinish, project.name as name2 FROM jobs JOIN project ON jobs.id_pj = project.id_pj WHERE FIND_IN_SET('$id_pj', jobs.id_do)";
	$data = $conn->query($query);
	while($row = mysqli_fetch_assoc($data)){

		array_push($ArrayList, new Job($row['id_jb'], $row['name'], $row['info'], $row['name2'], $row['ddline'], $row['isfinish'], ));

	}

	die(json_encode($ArrayList, JSON_UNESCAPED_UNICODE));



?>