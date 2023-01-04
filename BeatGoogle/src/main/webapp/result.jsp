<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import = "java.util.*" %>
<!--  To let jsp file knows our WebPage class -->
<%@ page import = "com.project.*" %>

<% ArrayList<WebTree> trees = (ArrayList<WebTree>) request.getAttribute("data"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Searching Result</title>
	<style type="text/css">
		h1{
			font-size: 30pt;
			font-family: Papyrus, sans-serif, tahoma; 
			color: #fbf8f0;
			position: relative;
			top: 20px;
		}
		table{
			width: 52%;
			margin-top: 30px;
			margin-left: auto;
  			margin-right: auto;
		}
		table, th, td{
			font-size: 16px;
			border: 1.2px solid black;
			border-radius: 5px;	
		}
		th, td{
			border-style: groove;
			background-color: #faf3d968;
		}
		th{
			font-size: 18px;
			color: #79390f;
		}
		a{
    		color:#2874A6;
		}
		*{
			margin: 0;
		}
		.bg{
			background-image: url("resultBg.jpg");
			height: 100vh;
			background-attachment: scroll;
			background-repeat: no-repeat;
			background-size: cover;
		}
	</style>
</head>
<body>
	<div class="bg">
	<h1 align="center"> Result Page</h1>
		<table>
			<tr>
				<th style="width:90%">Website</th>
				<th>Score</th>
			</tr>
			<%		
				for (WebTree t: trees) { 
			%>
			<tr>
				<td><a href=<%= t.root.webPage.url %> target="_blank"><%= t.root.webPage.name %></a></td>
				<td style="text-align: center"><%= t.root.nodeScore %></td>
			</tr>
			<% 
				} 
			%>
			
		</table>
	</div>

</body>
</html>