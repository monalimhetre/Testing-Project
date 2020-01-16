<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Page</title>
</head>
<body>

	<div class="header_resize">
      <div class="logo"><h1><a href=".">UTA Maintenance System Home</a></h1></div>
      <div class="menu_nav"></div>
	</div>
	
	 <input id="greetingField" value='${greetingField}' style ="background-color: white; border: none; width: 400px;" disabled="disabled" />
	 <br>
	 <p> Choose among the options: </p>
	 <br>
	 
	 <ul>
	 	  <li><a href="/maintenance_system/UserController?action=searchMarAndRepairer"  target="_top"><span>Assign MAR to Repairer</span></a></li>
	 	  <li><a href=""  target="_top"><span>View My Profile</span></a></li>
	 	  <li><a href=""  target="_top"><span>View Available Facilities</span></a></li>
	 	  <li><a href=""  target="_top"><span>View Specific Facilities</span></a></li>
	 	  <li><a href=""  target="_top"><span>Add New Facility</span></a></li>
	 	  <li><a href=""  target="_top"><span>View Repairers Schedule</span></a></li>
	 	  <li><a href=""  target="_top"><span>View Repairers Details</span></a></li>
	 	  <li><a href="/maintenance_system/UserController?action=searchMarAndAssignedRepairer"  target="_top"><span>Edit MAR</span></a></li>
          <li><a href="/maintenance_system/UserController?action=logoutUser"  target="_top"><span>Log Out</span></a></li>
     </ul>
	 

</body>
</html>