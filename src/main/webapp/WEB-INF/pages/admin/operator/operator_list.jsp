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
<title>后台管理</title>
<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />
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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/operator/list?page="+(new_page_index + 1));
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
		<i class="icon-home home-icon"></i> 
		<strong>日志列表</strong>
	</div>
	
	<div style="margin:10px 10px;">
		<form id="queryForm" action="${pageContext.request.contextPath }/operator/list?page=1" method="post">
			<table>
				<tr>
					<td>操作人：</td>
					<td><input type="text" name="operatorUser" value="${operatorUser}" /></td>
					<td><button class="btn btn-primary" type="submit" style="margin-left:10px;margin-top:-3px;">查询</button></td>
				</tr>
			</table>
		</form>
	</div>
	
	<table class="table table-hover">
		<tr>
			<th>操作人</th>
			<th>操作日期</th>
			<th>操作模块</th>
			<th>操作内容</th>
			<th>操作者IP</th>
		</tr>
		<c:choose>
			<c:when test="${pageinfo.dataList == null || fn:length(pageinfo.dataList) == 0}">
				<tr>
				  <td colspan="5" align="center" style="font-weight: bold;">暂无查询记录</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${pageinfo.dataList }" var="operator">
						<tr>
							<td>${operator.operatorUser }</td>
							<td>
							  <fmt:formatDate value="${operator.operatorDate }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${operator.operatorModule }</td>
							<td>${operator.operatorRemark }</td>
							<td>${operator.operatorIP }</td>
						</tr>
				</c:forEach>								
			</c:otherwise>
		</c:choose>
	</table>
					
	<div class="panel-foot text-center" id="pagenation"></div>

  </div>
</body>
</html>