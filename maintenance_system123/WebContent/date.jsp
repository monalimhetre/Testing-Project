<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
  <script>
  $(function myFunction() {
	  var letter="MAR"
	  var number = 1 + Math.floor(Math.random() * 1000);
	  var total=letter+number;
	  $('#my_div').text(total);
	},
	1000);
  </script>
</head>
<body>
 <button onclick="myFunction()">MAR Number</button>
 <textarea rows="3" cols="20" maxlength="20" id="my_div" value="MAR"></textarea>
<p>Date: <input type="text" id="datepicker"></p>
 
 
</body>
</html>