<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已禁用的租户页面</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />

<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>

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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/tenant/forbidlist?page="+(new_page_index + 1));
	$("#queryForm").submit();
}

function del(id){ 
	debugger
	if(confirm("确认删除吗")){ 
		window.location.href = "${pageContext.request.contextPath}/tenant/delete?id="+id;
		alert("删除成功");
	} 
	else{ 
		//window.location.href = "${pageContext.request.contextPath}/tenant/forbidlist";
	} 
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
		<strong>已禁用的租户</strong><br>
		<strong>当前街道:${street_name }</strong>
	</div>
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/tenant/forbidlist?page=1" method="post">
			<div style="margin-top:10px;">
			<input type="hidden" name="id" value="${tenant.id }" />
			<input type="hidden" name="street_name" value="${street_name }" />
			<input type="text" name="name" value="${name }" style="height:30px;" placeholder="请输入用户名" >
			<button class="btn btn-primary" type="submit" style="margin-top:-10px;margin-left:10px;">查询</button>
			</div>	
		</form>
	</div>
	
	<table class="table table-hover" >
		<tr>
		    <th>所属街道</th>
			<th>所属房间</th>
			<th>姓名</th>
			<th>电话</th>
			<th>交租日</th>
			<th>禁用原因</th>
			<th>禁用人</th>
			<th>禁用时间</th>
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
							<input type="hidden" name="id" value="${tenant.id }" />
							<td>${tenant.street_name }</td>
							<td>${tenant.room_name }</td>
							<td>${tenant.name }</td>
							<td>${tenant.phone }</td>
							<td>${tenant.rent_day }</td>
							<td>${tenant.reason }</td>
							<td>${tenant.modifyUser }</td>
							<td>
							  <fmt:formatDate value="${tenant.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							  <a style='cursor: pointer' class="button border-yellow button-little" onclick="del('${tenant.id}')">永久删除</a>
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