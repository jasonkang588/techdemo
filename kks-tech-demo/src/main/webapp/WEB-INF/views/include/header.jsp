<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	List<String> list = (List<String>)request.getAttribute("list");
%>

<div>
	<h1>Header msg</h1>
	<p>${msg}</p>
</div>

