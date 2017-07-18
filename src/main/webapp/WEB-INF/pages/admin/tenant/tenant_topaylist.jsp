<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />

<script src="${pageContext.request.contextPath }/js/jquery.js"></script>

<!-- 分页所需样式和JS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css" />
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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/tenant/topaylist?page="+(new_page_index + 1));
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

</script>

</head>
<body>
  <div class="page-content">
   <div class="panel-head">
		<i class="icon-user"></i> 
		<strong>待交房租列表</strong><br>
		<strong>当前街道:</strong>
		<strong>${street_name }</strong>
	</div>
  <div class="panel-head">
    <input type="hidden" name="street_name" value="${street_name }" />
	</div>
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/tenant/topaylist?page=1" method="post">
			<div style="margin-top:10px;">
			<input type="hidden" name="street_name" value="${street_name }" />
			<input type="hidden" name="id" value="${id }" />
			<input type="text" name="name" value="${name }" style="height:30px;" placeholder="请输入用户名" >
			
			<button class="btn btn-primary" type="submit" style="margin-top:-10px;margin-left:10px;">查询</button>
			</div>	
		</form>
	</div>
	
	<table class="table table-hover" >
		<tr>
		    <th>房间号</th>
		 	<th>姓名</th>
		 	<th>电话</th>
			<th>月租金</th>
			<th>水费</th>
			<th>电费</th>
			<th>杂费</th>
			<th>本月应交</th>
			<th>交租日</th>
			<th>备注</th>
			<th>编辑</th>
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
							<input type="hidden" name="id" value="${tenant.id}" />
							<td>${tenant.room_name }</td>
							<td>${tenant.name }</td>
							<td>${tenant.phone }</td>
							<td>${tenant.m_rent }</td>
							<td>${tenant.w_money }</td>
							<td>${tenant.e_money }</td>
							<td>${tenant.management_cost+tenant.sanitation_fee+tenant.sewage_fee }</td>
							<td>${tenant.m_money }</td>
							<td>${tenant.rent_day }</td>
							<td>${tenant.remark }</td>
							
							<td>
							  <c:if test="${tenant.time_end eq tenant.call_end}">
							    <a style='cursor: pointer' data-id="${tenant.id}" class="button border-red button-little call" >催租</a>
							  </c:if>
							  
							  <c:if test="${tenant.time_end ne tenant.call_end}">
							    <a class="button border-blue button-little">已催租</a>
							    <a style='cursor: pointer' data-id="${tenant.id}" class="button border-red button-little timeend" >标记为已交</a>
							  </c:if>
							
							</td>
						</tr>
				</c:forEach>								
			</c:otherwise>
		</c:choose>
	</table>
					
	<div class="panel-foot text-center" id="pagenation"></div>

</div>

<script>

$(".call").click(function(){
	debugger
	var aa = confirm("确定要催租吗？");
	if(aa)
	{
		var id = $(this).attr("data-id");
		location.href = "${pageContext.request.contextPath}/tenant/call?id=" + id;	
	}
})

$(".timeend").click(function(){
	debugger
	var aa = confirm("确定要标记为已交么");
	if(aa)
	{
		var id = $(this).attr("data-id");
		location.href = "${pageContext.request.contextPath}/tenant/time_end?id=" + id;	
	}
})
</script>
</body>
</html>