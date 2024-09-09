<?php

	require "connect.php";
	
	$role = $_POST['role'];
	$name = $_POST['name'];
	$username = $_POST['username'];
	$password = $_POST['password'];
	$email = $_POST['email'];

	// $name = "Nguyen tran dat";
	// $username = "datt123";
	// $password = "12345";
	// $email = "datmail";
	// $role = "MANAGER";


	$password = md5($password);

	if($conn->connect_error){
		die("connection failed");
	}

	if(strlen($username) > 0 && strlen($password) > 0 && strlen($name) > 0 && strlen($email) > 0 && strlen($role) > 0){

		//  bat dau mot transactrion de neu co loi se undo insert;
		$conn->begin_transaction();
		try{
			// insert thong tin nguoi dung vao bang users.
			$query = "INSERT INTO users VALUES(null,'$name' ,'$email', '$role',null)";
			$data = $conn->query($query);
			if(!$data){
				throw new Exception("Error inserting data: " . $conn->error);
			}

			$row = $conn->query("SELECT id from users WHERE FIND_IN_SET('$email', email)");
			
			if(!$row->num_rows>0){
				throw new Exception("Error inserting data: " . $conn->error);
			}
			$id = $row->fetch_assoc()['id'];
			//Insert thong tin dang nhap vao bang login
			$query2 = "INSERT INTO login VALUES('$id', '$username', '$password')";
			$data = $conn->query($query2);
			if(!$data){
				throw new Exception("Error inserting data: " . $conn->error);
			}
			// Xac nhan transaction da xong
			$conn->commit();
			die($id);
		}catch (Exception $e) {
		    // Huy transaction khong hoan thanh
		    $conn->rollback();
		    
		    echo "Transaction failed: " . $e->getMessage();
		}
	}
?>