<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
	
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>500</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="点钞机数据维护平台" name="description" />
<meta content="卓目科技" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<%=basePath%>/static/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="<%=basePath%>/static/pages/css/error.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=basePath%>/static/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>/static/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<%=basePath%>/static/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>/static/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="<%=basePath%>/favicon.ico"/>
</head>
<body class="page-500-full-page">
<div class="row">
	<div class="col-md-12 page-500">
		<div class=" number">
			 500
		</div>
		<div class=" details">
			<h3>Oops! 系统出错了.</h3>
			<p>
				 我们会尽快解决!<br/>
				请稍后访问或联系<a href="mailto:admin@zmvision.cn">管理员</a>.<br/><br/>
			</p>
		</div>
	</div>
</div>

</body>
<!-- END BODY -->
</html>