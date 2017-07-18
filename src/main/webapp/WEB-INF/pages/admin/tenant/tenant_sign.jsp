<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/add/add.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/date/lyz.calendar.css">

<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath }/js/date/jquery-1.5.1.js"></script>
<script src="${pageContext.request.contextPath }/js/date/lyz.calendar.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    function returnlist(){
    	//window.location.href='${pageContext.request.contextPath}/tenant/list';
    	window.history.back(-1); 
    }
    
    $(function () {
        $("#txtBeginDate").calendar({
            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                                    // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                                       // 目标对象是否设为只读，默认：true
            upperLimit: NaN,                                      // 日期上限，默认：NaN(不限制)
            lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
            callback: function () {                               // 点击选择日期后的回调函数
                //alert("您选择的日期是：" + $("#txtBeginDate").val());
            }
        });
        $("#txtEndDate").calendar();
    });
    
    function getRent_day(){
    	var date = document.getElementById("txtBeginDate").value;//得到合同期开始框中选择的时间，此时为string类型
    	var d = new Date(date.replace(/-/g,"/"));//将上面得到的时间转化为date型   
    	var myDate=new Date(d.getTime()-24*60*60*3000);//将该时间往前推三天，加入选择25号，则前推以后为22号  
    	var rent_day = myDate.getDate();
    	document.getElementById("r_day").value = rent_day;
    }
    
    function getD_day(){
    	var date = new Date();
    	var day = date.getDate();
    	var b_date = document.getElementById("txtBeginDate").value;
    	var bg_date = b_date.substring(8,10);
    	var temp_day = parseInt(bg_date)-parseInt(day);
    	var m_rent = document.getElementById("m_rent").value;
    	var d_rent = (m_rent/30)*temp_day;
    	document.getElementById("d_rent").value = d_rent >> 0;
    }
    
    function getTotal(){
    	var m_rent = document.getElementById("m_rent").value;
    	var d_rent = document.getElementById("d_rent").value;
    	var m_cost = document.getElementById("m_cost").value;
    	var st_cost = document.getElementById("st_cost").value;
    	var sw_cost = document.getElementById("sw_cost").value;
    	var c_pled = document.getElementById("c_pled").value;
    	var incidental = document.getElementById("incidental").value;
    	var total = parseInt(m_rent)+parseInt(d_rent)+parseInt(m_cost)+parseInt(st_cost)+parseInt(sw_cost)+parseInt(c_pled)+parseInt(incidental);
    	document.getElementById("total").value = total;
    }
</script>
<title>录入房客信息</title>
</head>
<body>
  <div class="cssbody">
    <h1 class="cssh1">录入房客信息</h1><br>
	<form action="${pageContext.request.contextPath }/tenant/sign.do" method="post">
	   <input type="hidden" name="room_id" value="${roomVo.room_id }">
	   <input type="hidden" name="roomType_name" value="${roomVo.roomType_name }">
	    <!-- 街道表，以下字段用来回显 -->
	    <label class="cssp" >所属街道:</label>
		  <input type="text" name="street_name" value="${roomVo.street_name }" /><br><br>
		
		<!-- 房间表，有一个更新字段，以下两个字段用来回显 -->  
		<label class="cssp" >房间号:</label>
		  <input type="text" name="room_name" value="${roomVo.room_name }" /><br><br>
		  
		<label class="cssp" >房间描述:</label>
		  <input type="text" name="roomDescription" value="${roomVo.roomDescription }" /><br><br>  
		  
		<label class="cssp">姓名:</label>
		  <input type="text" name="name" /><br><br>
		  
		<label class="cssp">电话:</label>
		  <input type="text" name="phone" /><br><br>
		  
	    <label class="cssp" >合同期开始时间:</label>
		  <input id="txtBeginDate" name="contract_term_start" onblur="getRent_day()"/><br><br>
		  
		<label class="cssp" >合同期结束时间:</label>
		  <input id="txtEndDate" name="contract_term_end" /><br><br>  
	
		<label class="cssp" >交租日:</label>
		  <input id="r_day" type="text" name="rent_day" placeholder="无须自己填，填入合同期后自动生成"/><br><br>
		 
		<label class="cssp" style="color:red">月租金:</label>
		  <input id="m_rent" type="text" name="m_rent" onblur="getD_day()"/><br><br> 
		  
	    <label class="cssp" style="color:red">押金:</label>
		  <input id="c_pled" type="text" name="cash_pledge" /><br><br>
		   
		<label class="cssp" style="color:red">管理费:</label>
		  <input id="m_cost" type="text" name="management_cost" /><br><br>
		  
		<label class="cssp" style="color:red">卫生费:</label>
		  <input id="st_cost" type="text" name="sanitation_fee" /><br><br>  
		  
		<label class="cssp" style="color:red">排污费:</label>
		  <input id="sw_cost" type="text" name="sewage_fee" /><br><br>    
		  
		<label class="cssp" style="color:red">其他押金:</label>
		  <input id="incidental" type="text" name="incidental" placeholder="包括门禁卡和空调遥控器押金" onblur="getTotal()"/><br><br>
		
		<label class="cssp" style="color:red">合同期前租金:</label>
		  <input id="d_rent" type="text" name="d_rent" placeholder="无须自己填，填入合同期和月租金后自动生成"/><br><br>       
		  
		<label class="cssp" >合计:</label>
		  <input id="total" type="text" name="total" placeholder="无须自己填，输入其他金额后，自动生成"/><br><br>  
		         
        <label class="cssp" >备注:</label>
        	<textarea name="remark" rows="2" cols="2"></textarea>
        	
        <div class="form-actions">
		  <button type="submit" class="btn btn-primary">添加</button>
		  <font color="red">${requestScope.fail }</font>
		  <button type="button" class="btn" onclick="returnlist()">返回列表页面</button>
		</div>
	</form>
 </div>	
</body>
</html>