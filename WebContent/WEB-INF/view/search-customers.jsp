<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
	<title>List Customer</title>
	
	<!-- reference the css -->
	<link type="text/css" rel="stylesheet" 
		  href="${pageContext.request.contextPath}/resources/css/style.css"/>
	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		
		<h3>Search Results</h3>
		
		<h4>Searched by: ${theSearchName}</h4>
	
		<div id="content">
			
			<!-- add a search box -->
			<form:form action="search" method="GET">
				Search Customer: <input type="text" placeholder="First or Last Name" name="theSearchName" />
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<!-- add our html table here -->		
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- Loop over and print customers -->
				<c:forEach var="tempCustomer" items="${customers}">
					
					<!-- construct an "update" link(variable) with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate" >
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<!-- construct an "delete" link(variable) with customer id -->
					<c:url var="deleteLink" value="/customer/delete" >
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
				   	<tr>
						<td> ${tempCustomer.firstName} </td>
					 	<td> ${tempCustomer.lastName} </td>
					 	<td> ${tempCustomer.email} </td>
					 	<td>
					 		<!-- display the update link -->
					 		<a href="${updateLink}">Update</a>
					 		|
					 		<a href="${deleteLink}"
					 		   onclick="if (!(confirm('Are you sure to delete this customer'))) return false">Delete</a>
					 	</td>
					</tr>
					
				</c:forEach>
				
			</table>	
			
			<!-- put new button: Back to main page -->
			
			<input type="button" value="Back" 
			    onclick="window.location.href='list'; return false;" 
			    class="add-button"/>
			
		</div>
	
	</div>

</body>

</html>