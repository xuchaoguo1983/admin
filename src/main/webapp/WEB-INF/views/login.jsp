<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<base href="${basePath}" />
<meta charset="utf-8" />
<title>点钞机数据维护平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="点钞机数据维护平台" name="description" />
<meta content="卓目科技" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="static/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="static/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="static/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="static/pages/css/login.css" rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="static/global/css/components-rounded.css"
	id="style_components" rel="stylesheet" type="text/css" />
<link href="static/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="static/layout/css/layout.css" rel="stylesheet"
	type="text/css" />
<link href="static/layout/css/themes/default.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="static/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript">
<!--
	var _basePath = '${basePath}';
//-->
</script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
	<div class="menu-toggler sidebar-toggler"></div>
	<!-- END SIDEBAR TOGGLER BUTTON -->
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img alt="" src="static/pages/img/logo.png">
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="system/auth/login" method="post">
			<h3 class="form-title">点钞机数据维护系统</h3>
			<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span>请输入正确的登陆信息. </span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="input-icon right">
					<i class="fa"></i> <input
						class="form-control form-control-solid placeholder-no-fix"
						type="text" autocomplete="off" placeholder="用户名" name="username"
						value="${username }" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon right">
					<i class="fa"></i><input
						class="form-control form-control-solid placeholder-no-fix"
						type="password" autocomplete="off" placeholder="密码"
						name="password" required />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">验证码</label>
				<div class="input-group verifycode">
					<div class="input-icon right">
						<i class="fa"></i> <input
							class="form-control form-control-solid placeholder-no-fix"
							type="text" autocomplete="off" placeholder="验证码"
							name="verifycode" required />
					</div>
					<img id="verifycodeImg" title="点击刷新" src="system/auth/verifycode" />
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-success">登陆</button>
				<label class="rememberme check"> <input type="checkbox"
					name="remember" value="Y" />记住我
				</label>
			</div>

		</form>
		<!-- END LOGIN FORM -->

	</div>
	<div class="copyright">2014 © 卓目科技.</div>
	<!-- END LOGIN -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="static/global/plugins/respond.min.js"></script>
<script src="static/global/plugins/excanvas.min.js"></script> 
<![endif]-->
	<script src="static/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<script
		src="static/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script
		src="static/global/plugins/jquery-validation/js/localization/messages_zh.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/jquery.md5.js"
		type="text/javascript"></script>
	<script src="static/global/scripts/formvalidate.js"
		type="text/javascript"></script>
	<script src="static/global/scripts/metronic.js" type="text/javascript"></script>
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="static/pages/scripts/login.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			Login.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->

	<!-- error handling -->
	<c:if test="${not empty error}">
		<script type="text/javascript">
			if ('${error.code}' != 0) {
				$('.alert-danger span').html('${error.message}');
				$('.alert-danger').show();
			}
		</script>
	</c:if>
</body>
<!-- END BODY -->
</html>
