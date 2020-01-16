<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Update Manager Profile</title>
</head>
<body>
<div class="header_resize">
      <div class="logo"><h1><a href="/maintenance_system/listManager.jsp">Maintenance System Home</a></a></h1></div>
      <div class="menu_nav">
      </div>
	
	<input name="errMsg"  value='${errorMsgs.errorMsg}' type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
	<form name="userForm" action="/maintenance_system/UserController?editUserProfile" method="post">
	
	
	<table style="width: 1200px; ">
	
    <tr>
    <td> User Role (*): </td>
    <td><input name="role" value='${UPDATEUSER.role}' readonly type="text" maxlength="45"> </td>
    </tr>
    
    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    
    </table>
    <input name="action" value="updateUserInDB" type="hidden">
    <input type="submit" value="Update Details">
	
	</form>
</body>
</html>