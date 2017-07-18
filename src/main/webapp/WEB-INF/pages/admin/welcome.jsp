<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"  />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/font/fontawesome-webfont.woff" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace-skins.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/js/respond.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>

<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				云尚公寓控制台111
				<small>
					<i class="icon-double-angle-right"></i>
					 查看
				</small>
			</h1>
		</div>
	</div>
	
	<div class="line-big">
    	<div class="xm3" style="margin:10px 50px">
        	<div class="panel border-back">
                <div class="panel-foot bg-back border-back">
                                                   您好，${admininfo.username }，
                                                   这是您第${admininfo.loginTimes }次登录，
                                                   最后一次登录时间为<fmt:formatDate value="${admininfo.lastloginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                </div>
            </div>
            <br />
        	<div class="panel">
            	<div class="panel-head"><strong>数据统计</strong></div>
                <ul class="list-group">
              <c:choose>
			  	<c:when test="${role.roleName eq '超级管理员'}">	
                	<li><span class="float-right badge bg-main" style="color:#b01528;font-size:20px;"><a href="${pageContext.request.contextPath}/room/freeroomchoose">${freeroomTotal}间</a></span>闲置房间</li>
                	<li><span class="float-right badge bg-main" style="color:#b01528;font-size:20px;"><a href="${pageContext.request.contextPath}/tenant/expirechoose">${expireTotal}间</a></span>本月到期房间</li>
        	 	</c:when>	
        	 	
			    <c:otherwise>
                	<li><span class="float-right badge bg-main" style="color:#b01528;font-size:20px;"><a href="${pageContext.request.contextPath}/room/statuslist?page=1&street_name=${street_name }&isRent=0">${freeroomTotal}间</a></span>闲置房间</li>
                	<li><span class="float-right badge bg-main" style="color:#b01528;font-size:20px;"><a href="${pageContext.request.contextPath}/tenant/expirelist?page=1&street_name=${street_name }&isRent=0">${expireStreetTotal}间</a></span>本月到期房间</li>
			    </c:otherwise>	
			 </c:choose>	
			 
                    <li><span class="float-right badge bg-main" style="color:#b01528;font-size:20px;">${roomTotal}间</span>已租房间</li>
                    <li><span class="float-right badge bg-main" style="color:#b01528;font-size:20px;">${mmoneyTotal }元</span>当前待交</li>
                </ul>
            </div>
            <br />
        </div>
        <div class="xm9" style="width:800px;margin:10px 10px">
        	<div class="alert alert-yellow">
        	  <strong>注意：请查看今日应抄水电的租户,</strong>
        	  <c:choose>
			  	<c:when test="${role.roleName eq '超级管理员'}">	
        	  		<a href="${pageContext.request.contextPath}/tenant/tocopyewchoose">点击查看</a>
        	 	</c:when>	
        	 	
			    <c:otherwise>
        	        <a href="${pageContext.request.contextPath}/tenant/tocopyewlist?page=1">点击查看</a>
			    </c:otherwise>	
			 </c:choose>	
        	</div>
        	
        	<div class="alert alert-red">    
        	  <strong>注意：请查看今日应交房租的租户，</strong>
        	  <c:choose>
			  	<c:when test="${role.roleName eq '超级管理员'}">	
        	 	  <a href="${pageContext.request.contextPath}/tenant/topaychoose">点击查看</a>  
        	 	</c:when>	
        	 	
			    <c:otherwise>
			  	  <a href="${pageContext.request.contextPath}/tenant/topaylist?page=1">点击查看</a>
			    </c:otherwise>	
			  </c:choose>	
        	</div>
        	
        	<div class="alert alert-white">    
        	  <strong style="font-size:20px">通知：各位管理员你们好，此系统目前处于测试阶段，并未上线，所以可能会存在一些问题，请各位在使用此系统时，遵循以下规则:
        	  	<br>1,发生异常请截图发给广达路公寓王浩，若当时王浩未回复，请重启浏览器，重新登录你的账号，进行当天你要做的任务，若错误依旧存在，请致电广达路公寓王浩
        	  	<br>2,此系统目前若想正常运转，必须遵循当日事，当日毕，所以请各位管理员务必监督好旗下经纪人将当天的事情处理完毕，将信息录入本系统
        	  	<br>3,水电：按照老板(李云仲先生)的要求，此系统是提前五天录入水电信息，即在当前日期往后推迟五天要交租的人，今天必须录入水电。
        	  	<br>4,交租：按照老板(李云仲先生)的要求，此系统是提前三天交租，即在当前日期往后推迟三天要交租的人，可以进行催租，前提：要想在本系统查看到交租人的信息，必须是前两天录过水电才行，否则查不到交租人的信息。
        	  	<br>5,信息纠正，在本系统上显示的交租日和你们之前记录的交租日如果隔了三天，请不要惊讶，事实上也是一样的，只不过本系统提前三天交租，而你们那个日期是最终交租日。
        	  	<br>5,短信通知，本系统目前签约时通知老板(李云仲先生)，催租时通知管理员(陶文斌先生)，请两位收到短信务必仔细核对相关数据，看是否有误差，以便修改。
        	  </strong>
        	</div>
        </div>
    </div>
</body>
</html>