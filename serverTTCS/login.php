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


    $username = $_POST['username'];
    $password = $_POST['password'];

    // $username = "dat1";
    // $password = "12345";
    $password = md5($password);
    
    if(strlen($username) >0 && strlen($password) >0){
        $ArrayUser = array();

        $query = "SELECT * FROM users JOIN login ON id_us = id WHERE FIND_IN_SET('$username',username) AND FIND_IN_SET('$password',password)";
        $data = $conn->query($query);

        while ($row = mysqli_fetch_assoc($data)) {
            array_push($ArrayUser, new LoginUser($row['id'], $row['fullname'], $row['email'], $row['role'], $row['image'],));
        }
        
        
        die( json_encode($ArrayUser));
        // if(!$data){
        //     die("Fail");
        // }
        // $row = mysqli_fetch_assoc($data)
        // $fir = $row['id_us'];
        //     //co du lieu
        
        // $query = "SELECT * FROM users WHERE FIND_IN_SET('$fir', id)";
        // $data = $conn->query($query);
        // $row = mysqli_fetch_assoc($data);
        // array_push($ArrayUser, new LoginUser($row['id'], $row['fullname'], $row['email'], $row['role'], $row['image'],));
        // die(json_encode($ArrayUser));
    }else{
        echo "NULL";
    }

?>