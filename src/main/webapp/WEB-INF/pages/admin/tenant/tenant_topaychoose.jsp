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
<title>街道选择页面</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/bootstrap.min.css"  />



<script type="text/javascript">
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
	<div>
		<form id="queryForm" action="${pageContext.request.contextPath }/tenant/topaylist?page=1" method="post">
			<div style="margin-top:10px;">
				<select name="street_name" >
					<c:forEach items="${streetList }" var="street">
						<option value="${street.street_name }">${street.street_name }</option>
					</c:forEach>
				</select>		
			<button class="btn btn-primary" type="submit" style="margin-top:-10px;margin-left:10px;">查询</button>
			</div>	
		</form>
	</div>
	

  </div>
</body>
</html>