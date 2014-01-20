<%@ include file="./templateCommon1.jsp"%>

<div class="centerAlignDiv">
	<form:form action="./register" method="POST" modelAttribute="user">
		<table>
			<tr>
				<td class="label">Name:</td>
				<td><form:input path="name" /> </td>
			</tr>

			<tr>
				<td class="label">Phone number:</td>
				<td><form:input path="number" /> </td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><form:password path="password" /></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="Register" />
				</td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="./templateCommon2.jsp"%>
