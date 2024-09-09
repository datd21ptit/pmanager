<?php
	require "connect.php";


	class Project
	{
		public $Id;
		public $Name;
		public $Id_creator;
		public $Infor;
		public $Deadline;
		public $Progress;

		function __construct($Id, $Name, $Id_creator, $Infor, $Deadline, $Progress)
		{
			$this->Id = $Id;
			$this->Name = $Name;
			$this->Id_creator = $Id_creator;
			$this->Infor = $Infor;
			$this->Deadline = $Deadline;
			$this->Progress = $Progress;
		}
	}

	$Array = array();

    $query = "SELECT * FROM project";
    $data = $conn->query($query);



    while($row = mysqli_fetch_assoc($data)){
    	$id = (int)$row['id_pj'];
    	$tmp = "SELECT COUNT(*) AS 'notFinish' FROM jobs WHERE find_in_set('$id',id_pj) AND isfinish";

    	$tmp = $conn->query($tmp);
    	$tmp = mysqli_fetch_assoc($tmp);
    	$tmp = $tmp['notFinish'];
    	$tmp2 = "SELECT COUNT(*) AS 'number' FROM jobs WHERE find_in_set('$id',id_pj)";
    	$tmp2 = $conn->query($tmp2);
    	$tmp2 = mysqli_fetch_assoc($tmp2);
    	$tmp2 = $tmp2['number'];
    	$prog = 0;
    	if($tmp2 != 0){
    		$prog = (int)($tmp/$tmp2*100);
    	}
    	
        array_push($Array, new Project($row['id_pj'], $row['name'], $row['id_creator'], $row['info'], $row['deadline'], $prog));
        
    }
	die( json_encode($Array,JSON_UNESCAPED_UNICODE ) );

?>