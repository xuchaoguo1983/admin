<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<content tag="head"> </content>
</head>
<body>
	<content tag="body">
	<div class="row">
		<div class="col-md-12">
			<!-- Begin: life time stats -->
			<div class="portlet light">
				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div class="btn-group">
								<button id="btnNewUser" class="btn green">
									添加新用户 <i class="fa fa-plus"></i>
								</button>
							</div>
						</div>

					</div>
				</div>

				<div class="portlet-body">
					<div class="table-container">
						<div class="table-actions-wrapper">
							<form class="form-inline">
								<div class="form-group">
									<label for="name">用户姓名</label> <input type="text"
										class="form-control form-filter" name="name"
										placeholder="用户名称">
								</div>
								<div class="form-group">
									<label for="name">登录账户</label> <input type="text"
										class="form-control form-filter" name="username"
										placeholder="登录名">
								</div>
								<div class="form-group">
									<label for="status">状态</label> <select name="status"
										class="form-control form-filter" data-codemap="USER_STATUS">
									</select>
								</div>

								<button type="button" class="btn btn-primary filter-submit">查询</button>
								<button type="button" class="btn btn-default filter-cancel">重置</button>
							</form>
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="userTable">
							<thead>
								<tr role="row" class="heading">
									<th data-name="name">用户姓名</th>
									<th data-name="username">登录账户</th>
									<th data-name="contact">联系方式</th>
									<th data-name="status" data-codemap="USER_STATUS"
										class="sortable">状态</th>
									<th data-name="createtime" data-order="desc" class="sortable">创建时间</th>
									<th data-action="edit,remove" width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- End: life time stats -->
		</div>
	</div>

	<!-- 用户信息 -->
	<div class="modal fade" id="userModel" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">用户编辑</h4>
				</div>
				<div class="modal-body">
					<div class="alertContainer"></div>
					<form class="form-horizontal" role="form">
						<div class="form-body">
							<input type="text" name="id" style="display: none" />

							<div class="form-group">
								<label class="col-md-3 control-label">用户姓名</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="用户名称" name="name" required>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">登录账户</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="登录账户" name="username" required>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">联系方式</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="联系方式" name="contact">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">邮件地址</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="邮件地址" name="email" email>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">用户备注</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i>
										<textarea class="form-control" rows="3" placeholder="用户备注"
											name="description"></textarea>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">状态</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <select name="status"
											class="form-control input-small" data-codemap="USER_STATUS"
											required>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联角色</label>
								<div class="col-md-9">
									<div id="roleListdiv" class="checkbox-list"></div>
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

	<!-- END PAGE CONTENT--> </content>

	<content tag="footer"> <script
		src="static/pages/scripts/system/user.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			UserPage.init();
		});
	</script> </content>
</body>
</html>