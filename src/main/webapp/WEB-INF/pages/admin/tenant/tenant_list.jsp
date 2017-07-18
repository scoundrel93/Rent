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

<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>

<!-- 分页所需样式和JS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css" />
<script src="${pageContext.request.contextPath }/js/jquery.pagination.js"></script>

<style type="text/css">
	.panel-head a:visited{color:red;}
    .panel-head a:link{ color:blue;} 
    
    .visited {color:red;}
    .link{ color:blue;}
</style>

<script type="text/javascript">
$(document).ready(function () {
    $('.panel-head a').each(function () {
        if ($($(this))[0].href == String(window.location))
            $(this).addClass('currentA').attr('href', 'javascript:void(0);');
        
    	debugger
    	//改变颜色
    	var currentTypeName = $("#btnRoomTypeName").val();
    	if($(this).text() == currentTypeName)
    	{
    		$(this).addClass("visited");
    	}
    });
    
})

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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/tenant/list?page="+(new_page_index + 1));
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
		<i class="icon-user"></i> 
		<strong>租户列表</strong><br>
		<strong>当前街道:</strong>
		<strong>${street_name }</strong>
	</div>
  <div class="panel-head">
    <input type="hidden" name="street_name" value="${street_name }" />
    <input type="hidden" id="btnRoomTypeName" name="roomType_name" value="${roomType_name }" />
	<a class="currentA" data-type="a"  style="margin-left:30px;cursor: pointer"">A栋</a>
	<a data-type="b" style="margin-left:30px;cursor: pointer">B栋</a>
	<a data-type="c" style="margin-left:30px;cursor: pointer"">C栋</a>
	<a data-type="d" style="margin-left:30px;cursor: pointer"">D栋</a>
	</div>
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/tenant/list?page=1" method="post">
			<div style="margin-top:10px;">
			<input type="hidden" name="street_name" value="${street_name }" />
			<input type="hidden" name="roomType_name" value="${roomType_name }" />
			<input type="hidden" name="id" value="${id }" />
			<input type="text" name="room_name" value="${room_name }" style="height:30px;" placeholder="请输入房间号" >
			
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
			<th>合同期前租金</th>
			<th>工具押金</th>
			<th>房间押金</th>
			<th>杂费</th>
			<th>交租日</th>
			<th>备注</th>
			<th>创建时间</th>
			<th>创建人</th>
			<th>修改时间</th>
			<th>修改人</th>
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
							<td>${tenant.room_name }</td>
							<td>${tenant.name }</td>
							<td>${tenant.phone }</td>
							<td>${tenant.m_rent }</td>
							<td>${tenant.d_rent }</td>
							<td>${tenant.incidental }</td>
							<td>${tenant.cash_pledge }</td>
							<td>${tenant.management_cost+tenant.sanitation_fee+tenant.sewage_fee }</td>
							<td>${tenant.rent_day }</td>
							<td>${tenant.remark }</td>
							<td>
							  <fmt:formatDate value="${tenant.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${tenant.createUser }</td>
							<td>
							  <fmt:formatDate value="${tenant.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${tenant.modifyUser }</td>
							<td>
							  <a class="button border-blue button-little" href="${pageContext.request.contextPath}/tenant/edit?id=${tenant.id}">修改</a>
							  <a class="button border-yellow button-little" href="${pageContext.request.contextPath}/tenant/forbid?id=${tenant.id}">禁用</a>
							</td>
						</tr>
				</c:forEach>								
			</c:otherwise>
		</c:choose>
	</table>
					
	<div class="panel-foot text-center" id="pagenation"></div>

</div>
<script >
	$('.panel-head a').on("click",function(){
		var type = $(this).attr("data-type");
		
		var roomType_name = "";
		switch (type)
		{
			case "a": roomType_name = "A栋";break;
			case "b": roomType_name = "B栋";break;
			case "c": roomType_name = "C栋";break;
			case "d": roomType_name = "D栋";break;
			default : roomType_name= "A栋";
		}
		
		location.href = "${pageContext.request.contextPath}/tenant/list?page=1&street_name=${street_name }&roomType_name=" + roomType_name;
	})

</script>
</body>
</html>