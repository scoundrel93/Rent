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
    	//window.location.href='${pageContext.request.contextPath}/user/list';
    	window.history.back(-1); 
    }
</script>
<title>修改用户</title>
</head>
<body>
  <div class = "cssbody">
    <h1 class="cssh1">修改用户</h1><br>
	<form action="${pageContext.request.contextPath }/user/edit.do" method="post">
	  <input type="hidden" name="id" value="${user.id }">
	  
	  <label class="cssp" >用户类型:&nbsp;&nbsp;</label>
		  <select name="userType" value="${user.userType }">
			<option value="0" <c:if test="${'0' eq user.userType}">selected</c:if> >普通用户</option>
			<option value="1" <c:if test="${'1' eq user.userType}">selected</c:if> >租户</option>
			<option value="2" <c:if test="${'2' eq user.userType}">selected</c:if> >其他</option>
		  </select><br><br>
		             
	  <label class="cssp" >姓名:</label>
		  <input type="text" name="name" value="${user.name }" /><br><br>
		
	  <label class="cssp" >电话:</label>
		  <input type="text" name="phone" value="${user.phone }"/><br><br>
		
	  <label class="cssp" >年龄:</label>
		  <input type="text" name="age" value="${user.age }"/><br><br>
		   
	  <label class="cssp" >性别:</label>
		  <select name="sex" value="${user.sex }">
			<option value="1"   <c:if test="${'1' eq user.sex}">selected</c:if> >男</option>
			<option value="2"   <c:if test="${'2' eq user.sex}">selected</c:if> >女</option>
			<option value="0"  <c:if test="${'0' eq user.sex}">selected</c:if> >保密</option>
		  </select><br><br>    
        
      <label class="cssp" >备注:</label>
        	<textarea name="remark" rows="2" cols="2">${user.remark }</textarea>
       
      <div class="form-actions">
		  <button type="submit" class="btn btn-primary">修改</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回上一层</button>
	  </div>
	</form>
 </div>	
</body>
</html>