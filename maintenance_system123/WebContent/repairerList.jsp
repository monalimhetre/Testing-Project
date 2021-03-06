<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Users List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="/maintenance_system/${role == 'MANAGER' ? 'listManager.jsp' : 'listAdmin.jsp'}">Maintenance System</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>

     <div class="mainbar"><div class="submb"></div></div>
      
      <input name="searchMsg" id="searchMsg" value='${searchMsg}' type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
       <form action="" method="post">
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
<input type="submit" value="Assign MAR">
</form>
</body>
</html>