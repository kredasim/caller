<%@include file="./templateCommon1.jsp" %>

	<a href="<c:url value="/j_spring_security_logout" />" class="logoutLink">Logout</a>
	<fieldset>
		<legend>Profile</legend>
		Username : ${username}
		</br>
		Activated: ${verified}
		
	</fieldset>

	<fieldset>
		<legend>Profile activation</legend>
		<form action="./welcome" method="POST">
			<input type="submit" name="sendActivationCode" value="Send activation code!"/>
		</form>
		
		<form action="./welcome" method="POST">
		Enter activation number:
		<input type="text" name="activationNumber" />
		 <input type="submit" name="activate" value="Activate profile!" />
		</form>
	</fieldset>

	<fieldset <c:if test="${verified == false}">disabled</c:if> >
		<legend>Call to number:</legend>
		<form action="./welcome" method="POST">
			Number to call:
			<input type="text" name="to" /> 
			Text to say:
			<input type="text" name="text" /> 
			<input type="submit" name="makeCall" value="Call" />
		</form>
	</fieldset>

<%@ include file="./templateCommon2.jsp" %>
