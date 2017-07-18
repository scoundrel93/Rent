<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/add/add.css">
<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    function returnlist(){
    	//window.location.href='${pageContext.request.contextPath }/role/list?page=1';
    	window.history.back(-1); 
    }
</script>
<title>修改管理员</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">修改管理员</h1><br>
	<form action="${pageContext.request.contextPath }/admin/edit.do" method="post">
	    <input type="hidden" name="id" value="${manager.id }">
	    <input type="hidden" value="${role1.id }">
		<label class="cssp" style="margin-left:20px">姓名:</label>
		  <input type="text" name="username" value="${manager.username }" /><br><br>
		  
	    <label class="cssp" >是否可用:&nbsp;&nbsp;</label>
		  <select name="isUsed" value="${manager.isUsed }">
			<option value="0" <c:if test="${'0' eq manager.isUsed}">selected</c:if> >是</option>
			<option value="1" <c:if test="${'1' eq manager.isUsed}">selected</c:if> >否</option>
		  </select><br><br>           
	
		<label class="cssp">所属角色:</label>
	    <select id="role" name="roleId" value="${role1.roleName }">
	        <option>请选择</option>  
	    	<c:forEach items="${roleList }" var="role">
				<option value="${role.id }" <c:if test="${role1.roleName eq role.roleName}">selected</c:if> >${role.roleName }</option>
			</c:forEach>
		</select><br><br>     
       
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary" >修改</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回上一层</button>
		</div>
	</form>
 </div>	
</body>
</html>