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
<title>添加租户</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">添加角色</h1><br>
	<form action="${pageContext.request.contextPath }/role/add.do" method="post">
		<label class="cssp" style="margin-left:20px">角色名:</label>
		  <input type="text" name="roleName" value="${role.roleName }" /><br><br>
       
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary" >添加</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回上一层</button>
		</div>
	</form>
 </div>	
</body>
</html>