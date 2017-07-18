<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>合同期查询页面</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/bootstrap.min.css"  />


<!-- 分页所需样式和JS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css" />
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.pagination.js"></script>


<script type="text/javascript">
$(function(){
	$("#pagenation").pagination(${pageinfo.dataCount},{
		items_per_page:${pageinfo.pageSize},
		current_page:${pageinfo.pageIndex} - 1,
		num_display_entries:6,
		next_text:"下一页>>",
		prev_text:"<<上一页",
		num_edge_entries:2,
		callback:handlePagenationClick			
	});				
});
	
function handlePagenationClick(new_page_index,pagenation_container)
{
	$("#queryForm").attr("action","${pageContext.request.contextPath }/tenant/contract?page="+(new_page_index + 1));
	$("#queryForm").submit();
}

function setIframeHeight(iframe) {
	if (iframe) {
	var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
	if (iframeWin.document.body) {
	iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
	}
	}
	};
	
	window.onload = function () {
	setIframeHeight(document.getElementById('external-frame'));
};

function changeIframe(src)
{
	if(src)
	{
		$("#external-frame").attr("src",src);	
	}
}

function prompt(){
	alert("该功能暂未开放，敬请期待！")
}

window.onload = function(){
	debugger
	var now = new Date();
	var currentYear = now.getFullYear();
	var currentMonth = now.getMonth() + 1;
	if(!!$("#hidYear").val()){
		currentYear = $("#hidYear").val();
	}
		
	BindSelect(document.getElementById('year'), currentYear - 1, currentYear + 5, currentYear);

	if(!!$("#hidMonth").val()){
		currentMonth = $("#hidMonth").val();
	}
	BindSelect(document.getElementById('month'), 1, 12, currentMonth);	
}

function BindSelect(selectObj, startValue, endValue, selectedValue){
	var i = 0;
	for(var val = startValue; val <= endValue; val++){
		selectObj.options.add(new Option(val , val));
		if(val == selectedValue)selectObj.options[i].selected = true;
		i++;
	}
}

// window.onload =function(){
// 	if(window.name == null || window.name == ""){
//  		window.name = "enter";
//  		window.location.href='${pageContext.request.contextPath }/tenant/contract?page=1';
// 	}else{
 
// 	}
// } 

</script>
</head>

<body>
  <div class="page-content">
  
    <div class="panel-head">
		<i class="icon-home home-icon"></i> 
		<a style='cursor: pointer' onclick="changeIframe('${pageContext.request.contextPath}/admin/index')">首页</a> 
		<strong>查询合同期</strong><br>
		<strong>当前街道:${street_name }</strong>
	</div>
	
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/tenant/contract?page=1" method="post">
			<div style="margin-top:10px;">
				<input type="hidden" name="street_name" value="${street_name }" />
				<select id="year" name="year" ></select>
				<select id="month" name="month" ></select>
				<input id="hidYear" value="${year }" type="hidden"/>
				<input id="hidMonth" value="${month }" type="hidden"/>
				<button class="btn btn-primary" type="submit" style="margin-top:-10px;margin-left:10px;">查询</button>
			</div>	
		</form>
	</div>
	
	<table class="table table-hover" >
		<tr>
		    <!--  <th>选择</th>-->
			<th>所属街道</th>
			<th>所属房间</th>
			<th>租户姓名</th>
			<th>电话</th>
			<th>合同期开始时间</th>
			<th>合同期结束时间</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${pageinfo.dataList == null || fn:length(pageinfo.dataList) == 0}">
				<tr>
				  <td colspan="5" align="center" style="font-weight: bold;">暂无查询记录</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${pageinfo.dataList }" var="tenant">
						<tr>
							<!-- 
							<td>
							  <input type="checkbox" name="id" value="${tenant.id }" />
							</td>
							 -->
							<td>${tenant.street_name }</td>
							<td>${tenant.room_name }</td>
							<td>${tenant.name }</td>
							<td>${tenant.phone }</td>
							<td>
							  <fmt:formatDate value="${tenant.contract_term_start }" pattern="yyyy-MM-dd" />
							</td>
							<td>
							  <fmt:formatDate value="${tenant.contract_term_end }" pattern="yyyy-MM-dd" />
							</td>
							<td>
							  <a style='cursor: pointer' class="button border-blue button-little" onclick="prompt()">续租</a>
							</td>
						</tr>
				</c:forEach>								
			</c:otherwise>
		</c:choose>
	</table>
					
	<div class="panel-foot text-center" id="pagenation"></div>

  </div>
</body>
</html>