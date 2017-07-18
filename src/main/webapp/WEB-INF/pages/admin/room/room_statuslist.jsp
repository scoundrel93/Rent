<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间列表</title>

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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/room/statuslist?page="+(new_page_index + 1));
	$("#queryForm").submit();
}

function del(id){ 
	debugger
	if(confirm("确认删除吗")){ 
		window.location.href = "${pageContext.request.contextPath}/role/delete?id="+id;
		alert("删除成功");
	} 
	else{ 
		//window.location.href = "${pageContext.request.contextPath}/role/list";
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
		<i class="icon-home"></i> 
		<strong>当前街道：</strong>
		<strong>${street_name }</strong>
	</div>
  <div class="panel-head">
    <input type="hidden" name="street_name" value="${street_name }" />
    <input type="hidden" name="isRent" value="${isRent }" />
    <!--  <a class="btn btn-danger" href="${pageContext.request.contextPath}/room/statuslist?page=1&street_name=${street_name }&isRent=0" style="margin-left:30px;">闲置房</a>-->
	<!-- 已租满三个月的房间 <a class="btn btn-success" href="${pageContext.request.contextPath}/room/returnlist?page=1&street_name=${street_name }&isRent=1" style="margin-left:30px;">转租房</a>-->
	<!--  <a class="btn" href="${pageContext.request.contextPath}/room/list?page=1&street_name=${street_name }&roomType_name=A栋" style="margin-left:30px;">返回列表页</a>-->
	</div>
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/room/statuslist?page=1" method="post">
			<div style="margin-top:10px;">
			<input type="hidden" name="street_name" value="${street_name }" />
			<input type="hidden" name="isRent" value="${isRent }" />
			<input type="hidden" name="id" value="${id }" />
			<input type="text" name="room_name" value="${room_name }" style="height:30px;" placeholder="请输入房间号" >
			
			<button class="btn btn-primary" type="submit" style="margin-top:-10px;margin-left:10px;">查询</button>
			</div>	
		</form>
	</div>
	
	<table class="table table-hover" >
		<tr>
			<th>房间状态</th>
		    <th>房间号</th>
		 	<th>价格</th>
			<th>房间描述</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>修改人</th>
			<th>修改时间</th>
			<th>编辑</th>
		</tr>
		<c:choose>
			<c:when test="${pageinfo.dataList == null || fn:length(pageinfo.dataList) == 0}">
				<tr>
				  <td colspan="5" align="center" style="font-weight: bold;">暂无查询记录</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${pageinfo.dataList }" var="room">
						<tr>
							<input type="hidden" name="room_id" value="${room.room_id }" />
							<td>
							     <c:if test="${'1' eq room.isRent}">已租</c:if>
								 <c:if test="${'0' eq room.isRent}">闲置</c:if>
							</td>
							<td>${room.room_name }</td>
							<td>${room.price }</td>
							<td>${room.roomDescription }</td>
							<td>${room.createUser }</td>
							<td>
							  <fmt:formatDate value="${room.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${room.modifyUser }</td>
							<td>
							  <fmt:formatDate value="${room.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							  <a class="button border-blue button-little" href="${pageContext.request.contextPath}/room/edit?room_id=${room.room_id}">修改</a>
							  <c:if test="${'0' eq room.isRent}">
							      <a class="button border-yellow button-little" href="${pageContext.request.contextPath}/tenant/sign?room_id=${room.room_id}">签约</a>
							  </c:if>
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