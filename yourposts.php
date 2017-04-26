<?php
$user_id = $_POST["user_id"];
//$country = $_POST["country"];
    $host_name  = "db674838216.db.1and1.com";
    $database   = "db674838216";
    $user_name  = "dbo674838216";
    $password   = "abcdef123";

    $connect  = mysqli_connect($host_name, $user_name, $password, $database);

  //  if(mysqli_connect_errno()) {
   //   echo '<p>Failed to connect to MySQL: '.mysqli_connect_error().'</p>';
   // } else {
    //  echo '<p>Connection to MySQL server successfully with ' . $database .'</p>';
  //  }

$sql = "SELECT * FROM `Posts` WHERE `user_id` LIKE \"$user_id\" ORDER BY date ASC;";
$query = mysqli_query($connect, $sql);
$response = array();

while ($row = mysqli_fetch_array($query)) {
$name = $row['name'];
$title = $row['title'];
$content = $row['content'];
$date = $row['date'];
$country = $row['country'];
$city = $row['city'];

array_push($response, array( "name"=>$name, "title"=>$title, "content"=>$content, "date"=>$date, "country"=>$country, "city"=>$city));
 }
echo json_encode($response);

    mysqli_close($connect);

?>
