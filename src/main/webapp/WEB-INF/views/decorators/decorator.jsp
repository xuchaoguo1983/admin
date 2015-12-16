<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${basePath}" />
<meta charset="utf-8" />
<title><sitemesh:write property='title' /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="点钞机数据维护平台" />
<meta content="" name="卓目科技" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="static/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="static/global/plugins/simple-line-icons/css/simple-line-icons.css"
	rel="stylesheet" type="text/css">
<link href="static/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="static/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css">	
<link rel="stylesheet" type="text/css"
	href="static/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN THEME STYLES -->
<link href="static/global/css/components-rounded.css"
	id="style_components" rel="stylesheet" type="text/css" />
<link href="static/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="static/layout/css/layout.css" rel="stylesheet"
	type="text/css" />
<link id="style_color" href="static/layout/css/themes/default.css"
	rel="stylesheet" type="text/css" />
<link href="static/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript">
<!--
	var _basePath = '${basePath}';
//-->
</script>
<sitemesh:write property="page.head" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body class="page-header-fixed page-sidebar-closed-hide-logo ">
	<!-- BEGIN HEADER -->
	<div class="page-header navbar navbar-fixed-top">
		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="/"> <img src="static/pages/img/logo.png" alt="logo"
					class="logo-default" style="width: 106px;" />
				</a>
				<div class="menu-toggler sidebar-toggler">
					<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
				</div>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="menu-toggler responsive-toggler"
				data-toggle="collapse" data-target=".navbar-collapse"> </a>
			<!-- END RESPONSIVE MENU TOGGLER -->

			<!-- BEGIN PAGE TOP -->
			<div class="page-top">
				<!-- BEGIN TOP NAVIGATION MENU -->
				<div class="top-menu">
					<ul class="nav navbar-nav pull-right">
						<!-- BEGIN USER LOGIN DROPDOWN -->
						<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
						<li class="dropdown dropdown-user dropdown-dark"><a
							href="javascript:;" class="dropdown-toggle"
							data-toggle="dropdown" data-hover="dropdown"
							data-close-others="true"> <span
								class="username username-hide-on-mobile"> <shiro:user>${sessionScope._user.name}</shiro:user>
							</span> <!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
						</a>
							<ul class="dropdown-menu dropdown-menu-default">
								<li><a href="#"> <i class="icon-user"></i> 个人信息
								</a></li>
								<li><a data-toggle="modal" href="#passwordModel"> <i
										class="icon-key"></i> 修改密码
								</a></li>
								<li class="divider"></li>
								<li><a href="system/auth/logout"> <i
										class="icon-logout"></i> 退出系统
								</a></li>
							</ul></li>
						<!-- END USER LOGIN DROPDOWN -->
					</ul>
				</div>
				<!-- END TOP NAVIGATION MENU -->
			</div>
			<!-- END PAGE TOP -->
		</div>
		<!-- END HEADER INNER -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
				<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
				<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
				<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
				<ul class="page-sidebar-menu " data-keep-expanded="false"
					data-auto-scroll="true" data-slide-speed="200">
					${sessionScope._menu }
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE HEADER-->
				<!-- BEGIN PAGE HEAD -->
				<div class="page-head">
					<!-- BEGIN PAGE TITLE -->
					<div class="page-title">
						<h1>
							<sitemesh:write property='title' />
						</h1>
					</div>
					<!-- END PAGE TITLE -->

				</div>
				<!-- END PAGE HEAD -->
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<sitemesh:write property="page.body" />
				<!-- END PAGE CONTENT-->
			</div>
			<!-- BEGIN CONTENT -->
		</div>
		<!-- END CONTENT -->
	</div>

	<!-- BEGIN MODAL DIALOGS -->
	<div class="modal fade" id="passwordModel" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<div class="alertContainer"></div>
					<form class="form-horizontal" role="form">
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-3 control-label">当前密码</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="password" class="form-control"
											placeholder="当前密码" name="password" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">新密码</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="password" class="form-control"
											placeholder="新密码" id="newpassword" name="newpassword"
											required minlength="6">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">确认新密码</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="password" class="form-control"
											placeholder="确认新密码" name="confirmpassword" required
											equalTo="#newpassword">
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn default" data-dismiss="modal">取消</button>
					<button type="button" class="btn blue submit">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- END MODAL DIALOS -->

	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="page-footer">
		<div class="page-footer-inner">
			<a href="http://zmvision.cn" title="http://zmvision.cn"
				target="_blank">2015 &copy; 卓目科技. </a>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
	<!-- END FOOTER -->
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
	<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="static/global/plugins/jquery-ui/jquery-ui.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="static/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="static/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="static/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<!-- END CORE PLUGINS -->
	<script
		src="static/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script
		src="static/global/plugins/jquery-validation/js/localization/messages_zh.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/bootbox/bootbox.min.js"
		type="text/javascript"></script>
	<script src="static/global/plugins/jquery.md5.js"
		type="text/javascript"></script>
	<script src="static/global/scripts/formautofill.js"
		type="text/javascript"></script>		
		
	<script src="static/global/scripts/formvalidate.js"
		type="text/javascript"></script>
	<script src="static/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="static/layout/scripts/layout.js" type="text/javascript"></script>
	<script src="static/global/scripts/datatable.js"></script>
	<script src="static/pages/scripts/helper.js" type="text/javascript"></script>
	<script src="static/pages/scripts/app.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			App.init();
		});
	</script>
	<!-- Add page script here -->
	<sitemesh:write property="page.footer" />
	<!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>