<%@page import="com.kkscom.demo.constant.CommonConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
	<head>
	    <title id="Description">Using jqxGrid with JSP and MySQL..</title>
	    <link type="text/css" rel="Stylesheet" href="<%=CommonConst.JQ_LIB_PATH %>/styles/jqx.base.css" />
	    <jsp:include page="../common/jquery-common-lib.jsp"></jsp:include>
		<jsp:include page="../common/jqwidget-common-lib.jsp"></jsp:include>
	    <script type="text/javascript">
	        $(document).ready(function () {
	            var source = {
	                datatype: "json",
	                datafields: [{
	                    name: 'FirstName',
	                    type: 'string'
	                }, {
	                    name: 'LastName',
	                    type: 'string'
	                }, {
	                    name: 'Title',
	                    type: 'string'
	                }, {
	                    name: 'BirthDate',
	                    type: 'date'
	                }],
	                id: 'EmployeeID',
	                url: 'jsp/select-data.jsp',
	                async: true
	            };
	            var dataAdapter = new $.jqx.dataAdapter(source);
	
	            $("#jqxgrid").jqxGrid({
	                width: 550,
	                autoheight: true,
	                source: dataAdapter,
	                columns: [{
	                    text: 'First Name',
	                    datafield: 'FirstName',
	                    width: 100
	                }, {
	                    text: 'Last Name',
	                    datafield: 'LastName',
	                    width: 100
	                }, {
	                    text: 'Title',
	                    datafield: 'Title',
	                    width: 180
	                }, {
	                    text: 'Birth Date',
	                    datafield: 'BirthDate',
	                    cellsformat: 'd',
	                    align: 'right',
	                    cellsalign: 'right'
	                }]
	            });
	        });
	    </script>
	</head>
	<body>
	    <div id="jqxgrid"></div>
	</body>
</html>
