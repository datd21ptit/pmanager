<?php
    require "connect.php";

    class LoginUser {
        public $Id;
        public $Fullname;
        public $Email;
        public $Role;
        public $Image;

        public function __construct($Id, $Fullname, $Email, $Role, $Image) {
            $this->Id = $Id;
            $this->Fullname = $Fullname;
            $this->Email = $Email;
            $this->Role = $Role;
            $this->Image = $Image;
        }
    }

	$Array = array();

    $query = "SELECT * FROM users";
    $data = $conn->query($query);

    while($row = mysqli_fetch_assoc($data)){
        array_push($Array, new LoginUser($row['id'], $row['fullname'], $row['email'], $row['role'], $row['image']));
        
    }
	die( json_encode($Array,JSON_UNESCAPED_UNICODE ) );

?>