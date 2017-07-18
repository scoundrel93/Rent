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
	$("#queryForm").attr("action","${pageContext.request.contextPath }/role/list?page="+(new_page_index + 1));
	$("#queryForm").submit();
}

function del(id){ 
	debugger
	if(confirm("确认删除吗?该操作会同时删除该角色下的所有管理员！！！！")){ 
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

function selectAll(){
    if ($("#SelectAll").is(":checked")) {
        $(":checkbox").prop("checked", true);//所有选择框都选中
    } else {
        $(":checkbox").prop("checked", false);
    }
}

//批量删除
function batchDeletes(){
        //判断至少写了一项
        var checkedNum = $("input[name='subcheck']:checked").length;
        if(checkedNum==0){
            alert("请至少选择一项!");
            return false;
        }
        if(confirm("确定删除所选项目?该操作会同时删除该角色下的所有管理员！！！！")){
        var checkedList = new Array();
        $("input[name='subcheck']:checked").each(function(){
            checkedList.push($(this).val());
        });
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/role/deleteList.action",
            data:{"roleList":checkedList.toString()},
            datatype:"html",
            success:function(data){
            	debugger
            	$(":checkbox").attr("checked",false);
                alert('删除成功!');
                setTimeout("location.reload()",100);//页面刷新
            },
            error:function(data){
                alert('删除失败!');
            }
        });
        }
}
</script>
</head>

<body>
  <div class="page-content">
  
    <div class="panel-head">
		<i class="icon-home home-icon"></i> 
		<strong>角色列表</strong>
	</div>
	
	<div style="margin-top:10px;">
		<form id="queryForm" action="${pageContext.request.contextPath }/role/list?page=1" method="post">
			<table>
				<tr>
					<td>角色名：</td>
					<td><input type="text" name="roleName" value="${roleName}" /></td>
					<td><button class="btn btn-primary" type="submit" style="margin-left:10px;margin-top:-3px;">查询</button></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div class="padding border-bottom">
		<input type="checkbox" id="SelectAll" onclick="selectAll();"/>全选&nbsp;&nbsp;
		<input type="button" class="button button-small border-green" value="添加角色" onclick="window.location.href='${pageContext.request.contextPath}/role/add' "/> 
		<input type="button" class="button button-small border-yellow" value="批量删除" onclick="batchDeletes()"/>
	</div>
	
	<table class="table table-hover">
		<tr>
			<th>选择</th>
			<th>角色名</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>修改人</th>
			<th>修改时间</th>
			<th>是否可用</th>
		</tr>
		<c:choose>
			<c:when test="${pageinfo.dataList == null || fn:length(pageinfo.dataList) == 0}">
				<tr>
				  <td colspan="5" align="center" style="font-weight: bold;">暂无查询记录</td>
				</tr>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${pageinfo.dataList }" var="role">
						<tr>
							<td>
							  <input type="checkbox" id="subcheck" name="subcheck" value="${role.id }" />
							</td>
							<td>${role.roleName }</td>
							<td>${role.createUser }</td>
							<td>
							  <fmt:formatDate value="${role.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${role.modifyUser }</td>
							<td>
							  <fmt:formatDate value="${role.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							  <a class="button border-blue button-little" href="${pageContext.request.contextPath}/role/edit?id=${role.id}">修改</a>
							  <a style='cursor: pointer' class="button border-yellow button-little" onclick="del('${role.id}')">删除</a>
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