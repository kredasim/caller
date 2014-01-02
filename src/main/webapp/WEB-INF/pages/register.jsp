<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<body>

	<form:form action="./register" method="POST" modelAttribute="user">
		<form:input path="name" />
		<form:input path="number" />
		<form:password path="password" />
		<input type="submit" value="Register" />
	</form:form>
</body>
</html>