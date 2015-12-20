<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<content tag="head">
<link
	href="static/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />
</content>
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
								<button data-toggle="modal" href="#roleModel" class="btn green">
									添加新角色 <i class="fa fa-plus"></i>
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
									<label for="name">角色名称</label> <input type="text"
										class="form-control form-filter" name="name"
										placeholder="角色名称">
								</div>
								<div class="form-group">
									<label for="status">状态</label> <select name="status"
										class="form-control form-filter" data-codemap="ENTITY_STATUS">

									</select>
								</div>
								<button type="button" class="btn btn-primary filter-submit">查询</button>
								<button type="button" class="btn btn-default filter-cancel">重置</button>
							</form>
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="roleTable">
							<thead>
								<tr role="row" class="heading">
									<th data-name="name" data-order="asc" data-sortable="true">角色名称</th>
									<th data-name="status" data-codemap="ENTITY_STATUS"
										data-sortable="true">状态</th>
									<th data-name="description" data-sortable="true">角色说明</th>
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

	<!-- 角色信息 -->
	<div class="modal fade" id="roleModel" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">角色编辑</h4>
				</div>
				<div class="modal-body">
					<div class="alertContainer"></div>
					<form class="form-horizontal" role="form">
						<div class="form-body">
							<input type="text" name="id" style="display: none" />

							<div class="form-group">
								<label class="col-md-3 control-label">角色名称</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="角色名称" name="name" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">状态</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <select name="status"
											class="form-control input-small" data-codemap="ENTITY_STATUS"
											required>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">角色说明</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="角色说明" name="description">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">关联菜单</label>
								<div class="col-md-9">
									<div data-name="menuId" data-type="checktree"
										data-ajax="system/menu/tree" data-multiple="true"></div>
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
		src="static/global/plugins/jstree/dist/jstree.min.js"></script> <script
		src="static/pages/scripts/crud.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			new CRUDPage().init('system/role', {
				reload : true,
				onLoadModalData : function(modal, data) {
					// set tree
					var menuIds = data.menuId;
					for (var i = 0; i < menuIds.length; i++) {
						var menuId = menuIds[i];
						var menuTree = $('[data-type="checktree"]', modal);
						var parent = menuTree.jstree("get_parent", menuId);
			
						// open 1st before select
						if (!menuTree.jstree('open_node', parent, function() {
							menuTree.jstree("select_node", menuId);
						})) {
							menuTree.jstree("select_node", menuId);
						}
					}
				}
			});
		});
	</script> </content>
</body>
</html>