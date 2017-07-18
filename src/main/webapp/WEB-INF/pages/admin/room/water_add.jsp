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
<title>添加水电</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">添加水电</h1><br>
	<form action="${pageContext.request.contextPath }/water/add.do" method="post">
	   <input type="hidden" name="room_id" value="${roomVo.room_id }">
	    <!-- 街道表，以下字段用来回显 -->
	    <label class="cssp" >所属街道:</label>
		  <input type="text" name="street_name" value="${roomVo.street_name }" /><br><br>
		
		<!-- 房间表，有一个更新字段，以下两个字段用来回显 -->  
		<label class="cssp" >房间号:</label>
		  <input type="text" value="${roomVo.room_name }" /><br><br>
		  
		<label class="cssp">电表度数:</label>
		  <input type="text" name="electric" /><br><br>
		    
		<label class="cssp">水表度数:</label>
		  <input type="text" name="water" /><br><br>
		  
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary">添加</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回列表页面</button>
		</div>
	</form>
 </div>	
</body>
</html>