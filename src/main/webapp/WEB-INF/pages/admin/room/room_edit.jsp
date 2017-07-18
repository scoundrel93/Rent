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
    	//window.location.href='${pageContext.request.contextPath }/room/list?page=1';
    	window.history.back(-1); 
    }
</script>
<title>修改房间信息</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">修改房间信息</h1><br>
	<form action="${pageContext.request.contextPath }/room/edit.do" method="post">
	    <input type="hidden" name="room_id" value="${roomVo.room_id }">
	    <input type="hidden" name="street_name" value="${roomVo.street_name }">
	    <input type="hidden" name="roomType_name" value="${roomVo.roomType_name }">
	    <label class="cssp">所属街道:</label>
		  <input type="text" value="${roomVo.street_name }" /><br><br>
		<label class="cssp" style="margin-left:41px">价格:</label>
		  <input type="text" name="price" value="${roomVo.price }" /><br><br>
		
		<label class="cssp" >房间描述:</label>
		  <input type="text" name="roomDescription" value="${roomVo.roomDescription }" /><br><br>
	
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary" >修改</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回上一级</button>
		</div>
	</form>
 </div>	
</body>
</html>