<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<jsp:include page="../common/jquery-common-lib.jsp"></jsp:include>
	<title>Home</title>
</head>
<body>
<h1>
	$.load test page  
</h1>

<div id=container>
</div>

<script type="text/javascript">
	$( document ).ready(function() {
		console.log("loadtest.jsp ready!");
	  $("#container").load("http://localhost:8080/resources/html/testfile.html");
	});
</script>

</body>
</html>
