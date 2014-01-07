<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<body>
	<h3>Message : ${message}</h3>	
	<h3>Username : ${username}</h3>	
 	<h3>Activated: ${verified}</h3>
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
 
 	<form action="./welcome" method="POST">
 		<input type="submit" name="sendActivationCode" />
 	</form>
 	
 	<form action="./welcome" method="POST">
 		<input type="text" name="activationNumber"/>
 		<input type="submit" name="activate" />
 	</form>
 	
 	<form action="./welcome" method="POST">
 		<input type="text" name="to" />
 		<input type="text" name="text" />
 		<input type="submit" name="makeCall"/>
 	</form>	
 	
 	<form action="./welcome" method="POST">
 		<input type="text" name="numbers" />
 		<input type="text" name="conferenceRoomName" />
 		<input type="submit" name="makeConferenceCall" />
 	</form>
</body>
</html>