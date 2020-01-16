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
      
      <input name="searchMsg" id="searchMsg" value='${searchNewMsg}' type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">

<form action="/maintenance_system/MarSysController?action=updateMarInDB" method="post">
       <table class="myTable"> 
			<tr class="myTableRow">
				<th class="myTableHead" style="width: 20px; ">Select MAR</th>
				<th class="myTableHead" style="width: 123px; ">MAR Number</th>
				<td class="myTableHead" style="width: 123px; "><b>Assigned To</b></td>
				<td class="myTableHead" style="width: 123px; "><b>Assigned Date</b></td>
				<td class="myTableHead" style="width: 112px; "><b>Estimate</b></td>
				<td class="myTableHead" style="width: 112px; "><b>Urgency</b></td>
			</tr>
 		<c:forEach items="${MARSELECTED}" var="dums">
			<tr class="myTableRow">
			<td><input type="radio" name="marSelected" value="${dums.mar_number}" ></td>
			<td class="myTableCell" style="width: 105px; "><c:out value="${dums.mar_number}" /></td>
			<td><label>Repairer:</label><input type='text' value="<c:out value="${dums.assignedTo}" />" name=${"repairer".concat(dums.mar_number)} /></td>
			<td><label>Date:</label><input type='text' value="<c:out value="${dums.assignedDate}" />" name=${"mydate".concat(dums.mar_number)} /></td>
			<td>
			<label for="time">Time:</label>
			<select name=${"estimatetime".concat(dums.mar_number)}>
				<option>SELECT TIME</option>
				<option ${dums.estimate == "00:30 MINUTES" ? 'selected="selected"' : ''}>00:30 MINUTES</option>
				<option ${dums.estimate == "01:00 HOUR" ? 'selected="selected"' : ''}>01:00 HOUR</option>
				<option ${dums.estimate == "02:00 HOURS" ? 'selected="selected"' : ''}>02:00 HOURS</option>
				<option ${dums.estimate == "03:00 HOURS" ? 'selected="selected"' : ''}>03:00 HOURS</option>
				<option ${dums.estimate == "04:00 HOURS" ? 'selected="selected"' : ''}>04:00 HOURS</option>
				<option ${dums.estimate == "05:00 HOURS" ? 'selected="selected"' : ''}>05:00 HOURS</option>
			</select><br></td>	
			<td>
			<select name=${"newurgency".concat(dums.mar_number)}>
			<option>Select Urgency</option>
				<option ${dums.urgency == "Major" ? 'selected="selected"' : ''}>Major</option>
				<option ${dums.urgency == "Medium" ? 'selected="selected"' : ''}>Medium</option>
				<option ${dums.urgency == "Minor" ? 'selected="selected"' : ''}>Minor</option>	
				</select><br></td>
			</tr>
		</c:forEach>
 </table>
 
 <input name="action" value="" type="hidden">
<input type="submit" value="Select MAR to be edited">
</form>

</body>
</html>