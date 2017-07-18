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
    	//window.location.href='${pageContext.request.contextPath}/tenant/list';
    	window.history.back(-1); 
    }
</script>
<title>禁用租户</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">请填写禁用原因</h1><br>
	<form action="${pageContext.request.contextPath }/tenant/forbid.do" method="post">
	<input type="hidden" name="id" value="${tenantVo.id }">
	<input type="hidden" name="street_name" value="${tenantVo.street_name }">
		<p class="cssp" >要禁用的租户姓名:</p>
		  <input type="text" name="name" value="${tenantVo.name }" /><br><br>
        <label class="cssp" >禁用原因:</label>
        	<textarea name="reason"></textarea>
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary">确认禁用</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回列表页面</button>
		</div>
	</form>
 </div>	
</body>
</html>