<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>云尚公寓后台管理-管理员登录</title>
    
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/pintuer.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/admin.css'/>">
   
    <script src="<c:url value='/js/jquery.js'/>"></script>
    <script src="<c:url value='/js/pintuer.js'/>"></script>
    
   <script type="text/javascript">
	$("#passcode").on('click',function(){
		changeCode();
	})
	
	function changeCode(){
		$("#codeImg").attr("src","${pageContext.request.contextPath}/code/code?t="+ <%=Math.random() %>);
	}
	</script>
</head>
<body>
<div class="container">
    <div class="line">
        <div class="xs6 xm4 xs3-move xm4-move">
            <br /><br />
            <div class="media media-y">
                <a href="#" target="_blank"><img src="<c:url value='/images/logo.png'/>" class="radius" alt="后台管理系统" /></a>
            </div>
            <br /><br />
            <form action="<c:url value='/admin/doLogin'/>" method="post">
            <div class="panel">
                <div class="panel-head"><strong>登录云尚公寓后台管理系统</strong></div>
                <div class="panel-body" style="padding:30px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input" name="username" placeholder="登录账号" data-validate="required:请填写账号" />
                            <span class="icon icon-user"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
                            <span class="icon icon-key"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input" name="passcode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                            <img id="passcode" src="${pageContext.request.contextPath}/code/code?t="<%=Math.random() %> width="80" height="32" class="passcode" />
                        </div>
                    </div>
                    <font color="red">${requestScope.errMsg }</font>
                </div>
                <div class="panel-foot text-center"><button class="button button-block bg-main text-big">立即登录后台</button></div>
            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    if(window != top){
    	alert("当前会话已过期，请重新登录");
        top.location.href = location.href; 
    } 
</script>
</body>
</html>