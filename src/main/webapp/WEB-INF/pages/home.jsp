<%@include file="./templateCommon1.jsp" %>

	<a href="<c:url value="/j_spring_security_logout" />" class="logoutLink">Logout</a>
	<fieldset class="label">
		<legend>Profile</legend>
		Username : ${username}
		</br>
		Activated: ${verified}
		
	</fieldset>

	<fieldset class="label" style="float: left;">
		<legend>Profile activation</legend>
		<form action="./welcome" method="POST">
			<input type="submit" name="sendActivationCode" value="Send activation code!" style="float: left;"/>
		</form>
		</br>
		
		<form action="./welcome" method="POST">
		<table>
			<tr>
				<td class="label">
					Enter activation code:
				</td>
				<td>
					<input type="text" name="activationNumber" />
				</td>
				<td>
				 	<input type="submit" name="activate" value="Activate profile!" />				
				</td>
			</tr>
		 </table>
		</form>
	</fieldset>

	<fieldset <c:if test="${verified == false}">class="disabled" disabled</c:if> 
				<c:if test="${verified == true}">class="label"</c:if> 
	>
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
