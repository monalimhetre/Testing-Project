<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Update Profile</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<form action="UserController?action=profile" method="post">
		<div class="container">
			<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"><br>
			
				<label for="username"><b>Username:</b></label><br>
				<input type="hidden" name="username" value="<c:out value='${user.username}'/>">
				<input type="text" disabled="disabled" value="<c:out value='${user.username}'/>"/><br>
				
				<label for="password"><b>Password:</b></label><br>
				<input type="password" placeholder="Enter your password" name="password" value="<c:out value='${update.password}'/>"><br>
				<input name="userPasswordError"  value="<c:out value='${userErrorMsgs.userPasswordError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>			
				
				
				<label for="firstName"><b>First Name:</b></label><br>
				<input type="text" placeholder="Enter your first name" name="firstName" value="<c:out value='${update.firstName}'/>"><br>
				<input name="userfirstNameError"  value="<c:out value='${userErrorMsgs.userfirstNameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="lastName"><b>Last Name:</b></label><br>
				<input type="text" placeholder="Enter your last name" name="lastName" value="<c:out value='${update.lastName}'/>"><br>
				<input name="userlastNameError"  value="<c:out value='${userErrorMsgs.userlastNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="email"><b>Email:</b></label><br>
				<input type="text" placeholder="Enter your email id" name="email" value="<c:out value='${update.email}'/>"><br>
				<input name="userEmailError"  value="<c:out value='${userErrorMsgs.userEmailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
					
				<label for="role"><b>Role:</b></label>
				<select name="role">
  				<option value="USER">USER</option>
  				<option value="MANAGER">MANAGER</option>
  				<option value="ADMIN">ADMIN</option>
  				<option value="REPAIRER">REPAIRER</option>
				</select><br>
				</br>		
					
				<label for="utaid"><b>UTA ID:</b></label><br>
				<input type="text" placeholder="Enter your UTA ID" name="utaid" value="<c:out value='${update.utaid}'/>"><br>
				<input name="utaIDerror"  value="<c:out value='${userErrorMsgs.utaIDerror}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"disabled="disabled" maxlength="60"><br>
				
				<label for="phone"><b>Phone:</b></label><br>
				<input type="text" placeholder="Enter your phone number" name="phone" value="<c:out value='${update.phone}'/>"><br>
				<input name="phoneError"  value="<c:out value='${userErrorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="streetaddress"><b>Address:</b></label><br>
				<input type="text" placeholder="Enter your street address" name="streetaddress" value="<c:out value='${update.streetaddress}'/>"><br>
				<input name="streetAddError"  value="<c:out value='${userErrorMsgs.streetAddError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="city"><b>City:</b></label></br>
				<input type="text" placeholder="Enter your city" name="city" value="<c:out value='${update.city}'/>"><br>
				<input name="cityError"  value="<c:out value='${userErrorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				
			  	<label for="zipcode"><b>Zip code:</b></label></br>
			    <input type="text" placeholder="Enter your zip code" name="zipcode" value="<c:out value='${update.zipcode}'/>"><br>
			  	<input name="zipCodeError"  value='${userErrorMsgs.zipCodeError}' type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60">		   
   
    
				<center>
					<button type="submit"><b>Update</b></button>
				</center>
			</div>	
		</form>
	</div>
</body>
</html>