<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAR page</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery-mockjax/1.5.1/jquery.mockjax.js"></script> 
    <script type="text/javascript" src="jquery.cascadingdropdown.js"></script> 
  
  </head>
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="/maintenance_system/${role == 'MANAGER' ? 'listManager.jsp' : 'listAdmin.jsp'}">Maintenance System</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>

     <div class="mainbar"><div class="submb"></div></div>
      
      <input name="searchMsg" id="searchMsg" value='${searchMsg}' type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">

<form action="/maintenance_system/UserController?action=assignMarToTheSelectedRepairer" method="post">
       <table class="myTable"> 
			<tr class="myTableRow">
				<th class="myTableHead" style="width: 20px; ">Select MAR</th>
				<th class="myTableHead" style="width: 123px; ">MAR Number</th>
				<td class="myTableHead" style="width: 123px; "><b>Assigned Date</b></td>
				<td class="myTableHead" style="width: 112px; "><b>Estimate</b></td>
				<td class="myTableHead" style="width: 112px; "><b>Urgency</b></td>
			</tr>
 		<c:forEach items="${MARS}" var="dum">
			<tr class="myTableRow">
			<td><input type="radio" name="marSelected" value="${dum.mar_number}"></td>
			<td class="myTableCell" style="width: 105px; "><c:out value="${dum.mar_number}" /></td>
			
			<td><label>Date:</label><input type='text'value="${dum.assignedDate}" name="mydate"/></td>
			<td>
			<label for="time">Time:</label>
			<select name="estimatetime" <c:out value="${dum.estimate}" />>
				<option>00:30 MINUTES</option>
				<option>01:00 HOUR</option>
				<option>02:00 HOURS</option>
				<option>03:00 HOURS</option>
				<option>04:00 HOURS</option>
				<option>05:00 HOURS</option>
			</select><br></td>	
			<!--<td><input type='text'<c:out value="${dum.urgency}" /> name="newurgency"/></td>-->
			<td><select name="newurgency" <c:out value="${dum.urgency}" />>
				<option>Major</option>
				<option>Medium</option>
				<option>Minor</option>	
				</select><br></td>
			</tr>	
			
		</c:forEach>
 </table>
 
 <table class="myTable"> 
			<tr class="myTableRow">
				<th class="myTableHead" style="width: 20px; ">Select User</th>
				<th class="myTableHead" style="width: 123px; ">First Name</th>
				<th class="myTableHead" style="width: 112px; ">Last Name</th> 
				<th class="myTableHead" style="width: 130px; ">UTA ID</th>
			</tr>

 		<c:forEach items="${REPAIRER}" var="rep">
			<tr class="myTableRow">
			<td><input type="radio" name="repSelected" value="${rep.username}"></td>
			<td class="myTableCell" style="width: 105px; "><c:out value="${rep.firstName}" /></td>
			<td class="myTableCell" style="width: 105px; "><c:out value="${rep.lastName}" /></td>
			<td class="myTableCell" style="width: 130px; "><c:out value="${rep.utaid}" /></td>
			</tr>
		</c:forEach>
 </table>
 
 <input name="action" value="" type="hidden">
<input type="submit" value="Select MAR to be assigned">
</form>

</body>
</html>