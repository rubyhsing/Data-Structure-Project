<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    
<%@ page import = "java.util.*" %>
<!--  To let jsp file knows our WebPage class -->
<%@ page import = "com.project.*" %>

<% ArrayList<WebTree> trees = (ArrayList<WebTree>) request.getAttribute("data"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<h1> Result Page right here </h1>
	<table>
	<tr>
		<th>
		WebSite Name
		</th>
		<th>
		Weight
		</th>
	</tr>
	<%		
	for (WebTree t: trees) { 
	%>
	<tr>
		<td><%= t.root.webPage.name %></td>
		<td><%= t.root.webPage.score %></td>
	</tr>
	<%
	}
	%>
	</table>
</body>
</html>