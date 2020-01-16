
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<script type="text/javascript">
    function Random() 
      {
        return Math.floor(Math.random() * 10);
      } 
</script>   

<form name="f1">
    a=      <input type="text" name="field1" /></br>
    b=      <input type="text" name="field2" /></br>
    Answer: <input type="text" name="ansfield" />

<input type="button" value="Fill"  onload()="document.getElementById('field1').value=Random()"/>

</form>
  
    <script type="text/javascript">
        function Random() {
            return Math.floor(Math.random() * 10);
        }

        document.getElementById('field1').value = Random()
    </script>
</body>
</html>