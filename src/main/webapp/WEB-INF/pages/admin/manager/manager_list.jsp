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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/date/lyz.calendar.css">

<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath }/js/date/jquery-1.5.1.js"></script>
<script src="${pageContext.request.contextPath }/js/date/lyz.calendar.min.js"></script>

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
		$("#queryForm").attr("action","${pageContext.request.contextPath }/admin/list?page=" + (new_page_index + 1));
		$("#queryForm").submit();
	}
	
	 $(function () {
	        $("#txtBeginDate").calendar({
	            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
	            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
	            complement: true,                                    // 是否显示日期或年空白处的前后月的补充,默认：true
	            readonly: true,                                       // 目标对象是否设为只读，默认：true
	            upperLimit: NaN,                                      // 日期上限，默认：NaN(不限制)
	            lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
	            callback: function () {                               // 点击选择日期后的回调函数
	                //alert("您选择的日期是：" + $("#txtBeginDate").val());
	            }
	        });
	        $("#txtEndDate").calendar();
	    });
	
	 function selectAll(){
		    jQuery.noConflict();
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
		        if(confirm("确定删除所选项目?")){
		        var checkedList = new Array();
		        $("input[name='subcheck']:checked").each(function(){
		            checkedList.push($(this).val());
		        });
		        $.ajax({
		            type:"POST",
		            url:"${pageContext.request.contextPath}/admin/deleteList.action",
		            data:{"managerList":checkedList.toString()},
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
		
		function del(id){ 
			debugger
			if(confirm("确认删除吗")){ 
				window.location.href = "${pageContext.request.contextPath}/admin/delete?id="+id;
				alert("删除成功");
			} 
			else{ 
				//window.location.href = "${pageContext.request.contextPath}/admin/list?page=1";
			} 
		}
	
</script>
</head>

<body>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="panel admin-panel">
					<div class="panel-head">
						<i class="icon-home home-icon"></i>
						<strong>管理员列表</strong>
					</div>
					<div>
						<form id="queryForm"
							action="${pageContext.request.contextPath }/admin/list?page=1"
							method="post" class="form-horizontal" style="margin-top:10px;">
							<table>
								<tr>
									<td>用户名：</td>
									<td><input type="text" name="username" value="${username}" /></td>
									<td>所属角色：</td>
									<td>
										<select name="roleId">
												<option value="">全部</option>
											<c:forEach items="${roleList }" var="role">
												<option value="${role.id }">${role.roleName }</option>
											</c:forEach>
										</select>
									</td>
									<td>创建日期：</td>
									<td>从&nbsp;<input id="txtBeginDate" name="createTime1" /></td>
									<td>到&nbsp;<input id="txtEndDate" name="createTime2" /></td>
									<td>是否可用：</td>
									<td>
									<select name="isUsed">
									        <option value="">全部</option>
											<option value="0">是</option>
											<option value="1">否</option>
									</select>
									</td>

									<td><button class="btn btn-primary" type="submit" style="margin-left:10px;margin-top:-3px;">查询</button></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="padding border-bottom">
						<input type="checkbox" id="SelectAll" onclick="selectAll()"/>全选&nbsp;&nbsp;
						<input type="button" class="button button-small border-green" value="添加管理员" onclick="window.location.href='${pageContext.request.contextPath}/admin/add' "/>
						<input type="button" class="button button-small border-yellow" value="批量删除" onclick="batchDeletes()"/>
					</div>
					<table class="table table-hover">
						<tr>
							<th>选择</th>
							<th>用户名</th>
							<th>所属角色</th>
							<th>是否可用</th>
							<th>添加时间</th>
							<th>修改时间</th>
							<th>操作</th>
						</tr>
						<c:choose>
							<c:when test="${pageinfo.dataList == null || fn:length(pageinfo.dataList) == 0}">
								<tr><td colspan="5" align="center" style="font-weight: bold;">暂无查询记录</td></tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${pageinfo.dataList }" var="manager">
										<tr>
											<td>
												 <input type="checkbox" id="subcheck" name="subcheck" value="${manager.id }" />
											</td>
											<td>${manager.username }</td>
											<td>${manager.roleName }</td>
											<td>
								               <c:if test="${'0' eq manager.isUsed}">是</c:if>
											   <c:if test="${'1' eq manager.isUsed}">否</c:if>
								            </td>
											<td>
												<fmt:formatDate value="${manager.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<fmt:formatDate value="${manager.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
											  <a class="button border-blue button-little" href="${pageContext.request.contextPath}/admin/edit?id=${manager.id}">修改</a>
											  <a style='cursor: pointer' class="button border-yellow button-little" onclick="del('${manager.id}')">删除</a>
											</td>
										</tr>
								</c:forEach>								
							</c:otherwise>
						</c:choose>
						

					</table>
					<div class="panel-foot text-center" id="pagenation"></div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>