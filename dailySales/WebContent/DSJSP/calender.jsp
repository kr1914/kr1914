<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<div>
	<input type="date" name="calender" value="")>
	   <table>
		   <thead>
			   <tr>
			    	<th>일</th>
			    	<th>월</th>
			    	<th>화</th>
			    	<th>수</th>
			    	<th>목</th>
			    	<th>금</th>
			    	<th>토</th>
			    </tr>
		   </thead>
		   <tbody>
		   		<c:forEach var="1week" begin="0" end="4">
		   			<tr>
		   			<c:forEach var="1day" begin="0" end="6">
		   				<td>1</td>
		   			</c:forEach>
		   			</tr>
		   		</c:forEach>
		   </tbody>
		   
	   </table>
</div>
