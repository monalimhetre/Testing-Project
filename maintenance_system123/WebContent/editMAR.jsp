<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>

</head>
<body>
    
	<div class="header_resize">
      <div class="logo"><h1><a href=".">Create Maintenance Action Report</a></h1></div>
      <div class="menu_nav"></div>
	</div>
	
	 <input id="greetingField" value='${greetingField}' style ="background-color: white; border: none; width: 400px;" disabled="disabled" />
	 <br>
	 <p> Complete MAR form: </p>
	 <br>
	 <form name="createMar" action="/maintenance_system/MarSysController?editUserProfile" method="post">
	 <ul>
	 	<tr>
    		<td> Facility Type: </td>
    		<td><select name="facility_type">
  			<option value="room">Multipurpose Rooms</option>
  			<option value="inBasket">Indoor Basketball Courts</option>
  			<option value="inVolley">Indoor Volleyball Courts</option>
  			<option value="inSoccer">Indoor Soccer Gym</option>
  			<option value="racquet">Racquetball Courts</option>
  			<option value="badmin">Badminton Courts</option>
  			<option value="table">Table Tennis</option>
  			<option value="confer">Conderence Rooms</option>
  			<option value="outVolley">Outdoor Volleyball Courts</option>
  			<option value="outBasket">Outdoor Basketball Courts</option>
		</select></td>
    </tr><br><br>
    
    <tr>
    		<td> Facility Name: </td>
    		<td><select name="facility_name">
  			<option value="room1">MR 1</option>
  			<option value="room2">MR 2</option>
  			<option value="room3">MR 3</option>
  			<option value="room4">MR 4</option>
  			<option value="inBasket1">IBBC 1</option>
  			<option value="inBasket2">IBBC 2</option>
  			<option value="inBasket3">IBBC 3</option>
  			<option value="inBasket4">IBBC 4</option>
  			<option value="inBasket5">IBBC 5</option>
  			<option value="inVolley1">IVBC 1</option>
  			<option value="inVolley2">IVBC 2</option>
  			<option value="inVolley3">IVBC 3</option>
  			<option value="inVolley4">IVBC 4</option>
  			<option value="inVolley5">IVBC 5</option>
  			<option value="inVolley6">IVBC 6</option>
  			<option value="inVolley7">IVBC 7</option>
  			<option value="inVolley8">IVBC 8</option>
  			<option value="inVolley9">IVBC 9</option>
  			<option value="inSoccer1">SCG1</option>
  			<option value="racquet1">RBC 1</option>
  			<option value="racquet2">RBC 2</option>
  			<option value="racquet3">RBC 3</option>
  			<option value="racquet4">RBC 4</option>
  			<option value="racquet5">RBC 5</option>
  			<option value="badmin1">BMC 1</option>
  			<option value="badmin2">BMC 2</option>
  			<option value="badmin3">BMC 3</option>
  			<option value="badmin4">BMC 4</option>
  			<option value="badmin5">BMC 5</option>
  			<option value="badmin6">BMC 6</option>
  			<option value="badmin7">BMC 7</option>
  			<option value="badmin8">BMC 8</option>
  			<option value="badmin9">BMC 9</option>
  			<option value="badmin10">BMC 10</option>
  			<option value="table1">TT1</option>
  			<option value="confer1">CR 1</option>
  			<option value="confer2">CR 2</option>
  			<option value="confer3">CR 3</option>
  			<option value="confer4">CR 4</option>
  			<option value="confer5">CR 5</option>
  			<option value="outVolley1">OVBC 1</option>
  			<option value="outVolley2">OVBC 2</option>
  			<option value="outBasket1">OBBC 1</option>
  			<option value="outBasket2">OBBC 2</option>
		</select></td>
    </tr><br><br>
    
    <tr>
    		<td> Urgency: </td>
    		<td><select name="urgency">
  			<option value="un">Unusable</option>
  			<option value="maj">Major</option>
  			<option value="med">Medium</option>
  			<option value="min">Minor</option>
		</select></td>
    </tr><br><br>
    
    <tr>
    		<td> <label>Description: </label> <textarea rows="3" cols="20" maxlength="100" value='${UPDATEMAR.description}' type="text" name="description"></textarea> 
            <input value='${m_errorMsgs.descriptionError}' type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
 
    </tr><br><br>
    
    <tr>
    		<td> <label>Reported By: <select name="reported_by">
  			<option value="user1">user1</option>
  			<option value="user2">user2</option>
  			<option value="user3">user3</option>
  			<option value="user4">user4</option>
  			
		</select></label> </td>
    </tr><br><br>
    
    <tr>
    		<td> <label>Date: <input type="text" id="datepicker" value='${UPDATEMAR.date_value}' type="text" name="date_value"></label>
    		<input value='${m_errorMsgs.date_valueError}'type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr><br><br>
    
    <tr>
    <td> 
    <label>MAR Number:
	<textarea rows="3" cols="20" maxlength="20" id="my_div" value='${UPDATEMAR.mar_number}' type="text" name="mar_number"></textarea>
	</label>
	</td>
    </tr>
    <br>
    <br>
    <td> <label>Assigned Repairer: <input type="text" id="assigned_to" value='${UPDATEMAR.assigned_to}' type="text" name="assigned_to"></label>
    		<input value='${m_errorMsgs.assigned_toError}'type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr><br><br>
    <td> <label>Assigned Date: <input type="text" id="assigned_date" value='${UPDATEMAR.assigned_date}' type="text" name="assigned_date"></label>
    		<input value='${m_errorMsgs.assigned_dateError}'type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr><br><br>
    <td> <label>Estimate Time: <input type="text" id="estimate" value='${UPDATEMAR.estimate}' type="text" name="estimate"></label>
    		<input value='${m_errorMsgs.estimateError}'type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr><br><br>
    
    </ul>
    <input name="action" value="updateMarSysInDB" type="hidden">
    <input type="submit" value="Update Details">
    </form>
    
    

  <script>
  $(function myFunction() {
	  var letter="MAR"
	  var number = 1 + Math.floor(Math.random() * 1000);
	  var total=letter+number;
	  $('#my_div').text(total);
	},
	1000);
  </script>
  
  <script>
  $(document).ready(function(){
	  $('#facility_type').change(function(){
		 val el = $(this);
		 if(el.val()==="Multipurpose rooms"){
			 $('#facility_name').append("<option value="room1">MR 1</option>");
		 }
			 else if(el.val()==="outBasket"){
				 $('#facility_name option:last-child').remove();
			 }
		 
	  });
	  });
  </script>
  


</body>
</html>