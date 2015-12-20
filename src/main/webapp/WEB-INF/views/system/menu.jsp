<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<content tag="head"></content>
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
								<button data-toggle="modal" href="#menuModel" class="btn green">
									添加新菜单 <i class="fa fa-plus"></i>
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
									<label for="menuName">菜单ID</label> <input type="text"
										class="form-control form-filter" name="id"
										placeholder="菜单ID">
								</div>
								<div class="form-group">
									<label for="menuName">菜单名称</label> <input type="text"
										class="form-control form-filter" name="name"
										placeholder="菜单名称">
								</div>
								<button type="button" class="btn btn-primary filter-submit">查询</button>
								<button type="button" class="btn btn-default filter-cancel">重置</button>
							</form>
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="menuTable">
							<thead>
								<tr role="row" class="heading">
									<th data-name="id" data-order="asc" data-sortable="true">菜单ID</th>
									<th data-name="name">菜单名称</th>
									<th data-name="url">URL</th>
									<th data-name="pid" data-sortable="true">父级菜单ID</th>
									<th data-name="icon">菜单Icon</th>
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
	
	<!-- 菜单信息 -->
	<div class="modal fade" id="menuModel" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">菜单编辑</h4>
				</div>
				<div class="modal-body">
					<div class="alertContainer"></div>
					<form class="form-horizontal" role="form">
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-3 control-label">菜单ID</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control" 
											placeholder="菜单ID" name="id" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">菜单名称</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="菜单名称" name="name"
											required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">URL</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="访问URL" name="url">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">父级菜单ID</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="父级菜单ID" name="pid">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">菜单Icon</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="菜单Icon" name="icon">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">菜单说明</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="菜单说明" name="description">
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
	
	<!-- END PAGE CONTENT-->
	</content>

	<content tag="footer"> <script
		src="static/pages/scripts/crud.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			new CRUDPage().init('system/menu');
		});
	</script> </content>
</body>
</html>