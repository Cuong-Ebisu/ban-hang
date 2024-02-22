<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="addcate" method = "post">
		<label>
			Nhập category name:
		</label>
		<input type = "text" name = "cateName">
		
		<label>
			Nhập link icon:
		</label>
		<input type = "text" name = "images">
		
		<input type = "submit" value = "Add category">
	</form>
</body>
</html>