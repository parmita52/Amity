<?php
$user_id = $_POST["user_id"];
$name = $_POST["name"];
$title = $_POST["title"];
$content = $_POST["content"];
$country = $_POST["country"];
$city = $_POST["city"];
    $host_name  = "db674838216.db.1and1.com";
    $database   = "db674838216";
    $user_name  = "dbo674838216";
    $password   = "abcdef123";

    $connect  = mysqli_connect($host_name, $user_name, $password, $database);   

if (mysqli_connect_errno()){
echo "connection failed";
}
$sql =  "INSERT INTO `Posts` (" .
 "`user_id`, `name`, `title`, `content`, `date`,`country`, `city` ) "
 . "VALUES (\"$user_id\", \"$name\", \"$title\",  \"$content\",NOW(), \"$country\", \"$city\");";
if (mysqli_query($connect, $sql)){
echo "Data insertion success";
}
else    {
echo "Error while insertion...";
}
mysqli_close($connect);

?>
