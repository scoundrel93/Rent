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
    	//window.location.href='${pageContext.request.contextPath}/tenant/list?page=1';
    	window.history.back(-1); 
    }
</script>
<title>修改租户信息页面</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">修改租户</h1><br>
	<form action="${pageContext.request.contextPath }/tenant/edit.do" method="post">
	    <input type="hidden" name="id" value="${tenantVo.id }">
	    <input type="hidden" name="street_name" value="${tenantVo.street_name }">
	    <input type="hidden" name="roomType_name" value="${tenantVo.roomType_name }">
	    <label class="cssp">所属街道:</label>
		  <input type="text" value="${tenantVo.street_name }" /><br><br>
		
		<label class="cssp">所属房间:</label>
		  <input type="text" value="${tenantVo.room_name }"/><br><br>
		  
		<label class="cssp">姓名:</label>
		  <input type="text" name="name" value="${tenantVo.name }" /><br><br>
		  
		<label class="cssp" >电话:</label>
		  <input type="text" name="phone" value="${tenantVo.phone }"/><br><br>
		  
		<label class="cssp" >月租金:</label>
		  <input type="text" name="m_rent" value="${tenantVo.m_rent }"/><br><br>
		  
		<label class="cssp">押金:</label>
		  <input type="text" name="cash_pledge" value="${tenantVo.cash_pledge }"/><br><br>
		  
		<label class="cssp" >管理费:</label>
		  <input type="text" name="management_cost" value="${tenantVo.management_cost }"/><br><br>
		 
		<label class="cssp" >卫生费:</label>
		  <input type="text" name="sanitation_fee" value="${tenantVo.sanitation_fee }"/><br><br>
		
		<label class="cssp" >排污费:</label>
		  <input type="text" name="sewage_fee" value="${tenantVo.sewage_fee }"/><br><br>
		      
		<label class="cssp" >交租日:</label>
		  <input type="text" name="rent_day" value="${tenantVo.rent_day }"/><br><br>
		  
        <label class="cssp" >备注:</label>
        	<textarea name="remark" rows="2" cols="2">${tenantVo.remark }</textarea>
        	
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary">修改</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回上一级</button>
		</div>
	</form>
 </div>	
</body>
</html>