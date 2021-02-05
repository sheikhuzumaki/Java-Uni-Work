<!doctype html>
<html lang="en">
  <head>
    <title>Index</title>
  </head>
  <script type="text/javascript">
      function check() {
      if(document.form.item.value == ""){
      alert("Please Fill The Blank")
    return false ;
}
}
    </script>
  <body>
      <h1>Search</h1>
      <form name="form" method="post" action="Process.jsp" onsubmit="return check()" >
          <label>Item Name</label>
          <input type="text" name="item">
        <br>
          <input type="submit" name="submit" class="btn btn-success">
      </form>
  </body>
</html>