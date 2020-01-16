<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User page</title>
</head>
<body>
	
	<div class="header_resize">
      <div class="logo"><h1><a href=".">Maintenance System Home</a></h1></div>
      <div class="menu_nav"></div>
	 </div>
	 
	 <input id="greetingField1" value='${greetingField1}' style ="background-color: white; border: none; width: 400px;" disabled="disabled" /><br>
	 <input id="greetingField2" value='${greetingField2}' style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"/>
	 <br>
	 <p> Choose among the options: </p>
	 <ul>
          <li><a href="createMAR.jsp"  target="_top"><span>Create MAR</span></a></li>
          <li><a href="/maintenance_system/UserController?action=userEditProfile"  target="_top">View/Update Profile<span></span></a></li>
          <li><a href=""  target="_top"><span>Log Out</span></a></li>
     </ul>

</body>
</html>