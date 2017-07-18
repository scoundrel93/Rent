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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/user/list?page="+(new_page_index + 1));
	$("#queryForm").submit();
}

function del(id){ 
	debugger
	if(confirm("确认删除吗")){ 
		window.location.href = "${pageContext.request.contextPath}/user/delete?id="+id;
		alert("删除成功");
	} 
	else{ 
		//window.location.href = "${pageContext.request.contextPath}/user/list?page=1";
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
		<i class="icon-home home-icon"></i> 
		<a style='cursor: pointer' onclick="changeIframe('${pageContext.request.contextPath}/admin/index')">首页</a> 
		<strong>用户列表</strong>
	</div>
	
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/user/list?page=1" method="post">
			<div style="margin-top:10px;">
			<input type="text" name="name" value="${name }" style="height:30px;" placeholder="请输入用户名" >
			<button class="btn btn-primary" type="submit" style="margin-top:-10px;margin-left:10px;">查询</button>
			</div>	
		</form>
	</div>

	<table class="table table-hover">
		<tr>
			<th>用户类型</th>
			<th>用户名</th>
			<th>电话</th>
			<th>年龄</th>
			<th>性别</th>
			<th>备注</th>
			<th>创建时间</th>
			<th>修改时间</th>
		</tr>
		<c:choose>
			<c:when test="${pageinfo.dataList == null || fn:length(pageinfo.dataList) == 0}">
				<tr>
				  <td colspan="5" align="center" style="font-weight: bold;">暂无查询记录</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${pageinfo.dataList }" var="user">
						<tr>
							<td>
								<c:if test="${'0' eq user.userType}">普通用户</c:if>
								<c:if test="${'1' eq user.userType}">租户</c:if>
								<c:if test="${'2' eq user.userType}">其他</c:if>
						    </td>
							<td>${user.name }</td>
							<td>${user.phone }</td>
							<td>${user.age }</td>
							<td>
							    <c:if test="${'0' eq user.sex}">保密</c:if>
								<c:if test="${'1' eq user.sex}">男</c:if>
								<c:if test="${'2' eq user.sex}">女</c:if>
							</td>
							<td>${user.remark }</td>
							<td>
							  <fmt:formatDate value="${user.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							  <fmt:formatDate value="${user.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							  <a class="button border-blue button-little" href="${pageContext.request.contextPath}/user/edit?id=${user.id}">修改</a>
							  <a style='cursor: pointer' class="button border-yellow button-little" onclick="del('${user.id}')">删除</a>
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