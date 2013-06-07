<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ranking</title>
</head>
<body>
	<h1>Ranking Data</h1>
	<form:form action="ranking.do" method="POST" commandName="ranking">

		<table border="1px solid">
			<tr>
				<th>Data</th>
				<th>Jogador</th>
				<th>Assassinatos</th>
				<th>Mortes</th>
				
			</tr>
			
			<c:forEach items="${rankingMap.playerList}" var="myMap">
				<tr>
					<td>
						<fmt:formatDate type="both" timeStyle="short" 
						pattern="dd-MM-yyyy HH:mm:ss" value="${myMap.value.gameDate}" />
            		</td>
					<td>${myMap.value.name}</td>
					<td>${myMap.value.deaths.size()}</td>
					<td>${myMap.value.kills.size()}</td>
				</tr>
			</c:forEach>
			
		</table>
	</form:form>

</body>
</html>