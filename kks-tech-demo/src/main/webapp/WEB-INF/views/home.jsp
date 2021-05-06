<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="http://localhost:8080/conntest">conn test</a>
<br><a href="http://localhost:8080/includetest">includetest</a>
<br><a href="http://localhost:8080/jqgrid">jqgrid</a>
<br><a href="http://localhost:8080/loadtest">loadtest</a>

</body>
</html>
