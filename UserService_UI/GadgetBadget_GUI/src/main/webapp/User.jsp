<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
			<title>User Service</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/User.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>User Services</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>User Registration</b></legend>
					<form id="USER" name="USER" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">User Type:</label>
						    <input type="hidden" id="userID" name="userID">
						    <input type="text" id="userType" class="form-control" name="userType">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">User Name:</label>
						    <input type="text" id="userName" class="form-control" name="userName">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">User Email:</label>
						    <input type="text" id="userEmail" class="form-control" name="userEmail">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">User Password:</label>
						    <input type="text" id="userPassword" class="form-control" name="userPassword">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">userPhone:</label>
						    <input type="text" id="userPhone" class="form-control" name="userPhone">						    
						</div>
						 	
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example1" class="col-sm-2 col-form-label col-form-label-sm">User Address:</label>
						    <input type="text" id="userAddress" class="form-control" name="userAddress">						    
						</div>					
						<br> 
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						
					</form>
				
							
			</fieldset>
			
			<br> 
			
			<div class="container" id="UserGrid">
				<fieldset>
					<legend><b>View User Profile</b></legend>
					<form method="post" action="User.jsp" class="table table-striped">
						<%
							User viewUser = new User();
											out.print(viewUser.readuser());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>