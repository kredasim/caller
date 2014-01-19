<%@ include file="./templateCommon1.jsp" %>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
				<td>
					<div class="label">
					User:
					</div>
				</td>
				<td><input type='text' name='j_username' placeholder="username">
				</td>
			</tr>
			<tr>
				<td>
					<div class="label">
						Password:
					</div>
				</td>
				<td><input type='password' name='j_password' placeholder="password"/> 
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
		</table>
 
	</form>
	
<%-- 		<form action="<c:url value='j_spring_security_check' />"> --%>
	
<!-- 			<input type="text" name="j_username" placeholder="username"> -->
			
<!-- 			<input type="password" name="j_password" placeholder="password"> -->
			
<!-- 			<input type="submit" name="submit" value="log in"> -->
	
<!-- 		</form> -->

<%@ include file="./templateCommon2.jsp" %>