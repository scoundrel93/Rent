<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/add/add.css">
<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    function returnlist(){
    	//window.location.href='${pageContext.request.contextPath }/room/list?page=1';
    	window.history.back(-1); 
    }
    
    function findRoomType(){  
        //var street_id = $("#street").attr("value"); 
        var street_id = document.getElementById("street").value;  
        $.ajax({  
            url:"${pageContext.request.contextPath}/room/findRoomType",  
            type:"get",  
            timeout:"1000",  
            data:{street_id:street_id},  
            success:function(data){ 
                $("#roomType option").remove();  
                $("#roomType").append("<option value='0'>请选择</option>"); 
                if (data != 0) {  
                    for ( var i = 0; i < data.length; i++) {  
                        var roomType_id = data[i].roomType_id;  
                        var roomType_name = data[i].roomType_name;  
                        $("#roomType").append(  
                                "<option value="+roomType_id+">"  
                                        + roomType_name + "</option>");  
                    }  
                }
                console.log(data);
            },  
            error : function(XMLResponse) {  
                alert(XMLResponse.responseText);  
            }  
        }); 
    } 
    
</script>
<title>添加房间</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">添加房间</h1><br>
	<form action="${pageContext.request.contextPath }/room/add.do" method="post">
		<input type="hidden" name="room_id">
	    <label class="cssp">所属街道:</label>
	    <select id="street" name="street_id" onChange="findRoomType()">
	        <option>请选择</option>  
	    	<c:forEach items="${streetList }" var="street">
				<option value="${street.street_id }">${street.street_name }</option>
			</c:forEach>
		</select><br><br>
		
		<label class="cssp">所属栋号:</label>
	    <select id="roomType" name="roomType_id" >
		</select><br><br>			
				
		<label class="cssp">房间号:</label>
		  <input type="text" name="room_name" placeholder="请以大写字母ABCD开头，如A201"/><br><br>
		
		<label class="cssp">价格:</label>
		  <input type="text" name="price" /><br><br>
		
		<label class="cssp" >房间描述:</label>
		  <input type="text" name="roomDescription" /><br><br>
	
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary" >添加</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回上一级</button>
		</div>
	</form>
 </div>	
</body>
</html>