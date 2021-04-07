<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Body content</title>
<style>
div {
  border: 5px outset red;
  background-color: lightblue;
  text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" flush="false" />
	<h1>Body msg</h1>
	<p>${msg}</p>
	<jsp:include page="bottom.jsp" flush="false" />
</body>
</html>