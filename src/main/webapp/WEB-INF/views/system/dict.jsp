<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>
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
								<button data-toggle="modal" href="#dictModel" class="btn green">
									添加新字典 <i class="fa fa-plus"></i>
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
									<label for="id">字典代码</label> <input type="text"
										class="form-control form-filter" name="id"
										placeholder="字典代码">
								</div>
								<button type="button" class="btn btn-primary filter-submit">查询</button>
								<button type="button" class="btn btn-default filter-cancel">重置</button>
							</form>
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="dictTable">
							<thead>
								<tr role="row" class="heading">
									<th data-name="id" data-order="asc" class="sortable">字典代码</th>
									<th data-name="name">字典名称</th>
									<th data-action="list,edit,remove" width="10%">操作</th>
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

	<!-- 字典信息 -->
	<div class="modal fade" id="dictModel" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">字典编辑</h4>
				</div>
				<div class="modal-body">
					<div class="alertContainer"></div>
					<form class="form-horizontal" role="form">
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-3 control-label">字典代码</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="字典代码" name="id" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典名称</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="字典名称" name="name" required>
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

	<!-- 字典项列表 -->
	<div class="modal fade" id="dictCodeListModel" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">字典项列表</h4>
				</div>
				<div class="modal-body">
					<div class="table-toolbar">
						<div class="row">
							<div class="col-md-6">
								<div class="btn-group">
									<button data-toggle="modal" href="#dictCodeModel"
										class="btn green">
										添加新字典项 <i class="fa fa-plus"></i>
									</button>
								</div>
							</div>

						</div>
					</div>
					<div class="portlet-body">
						<div class="table-container">
							<div class="table-actions-wrapper">
								<input type="hidden" name="codemap" class="form-filter" />
							</div>
							<table class="table table-striped table-bordered table-hover"
								id="dictCodeTable">
								<thead>
									<tr role="row" class="heading">
										<th data-name="code">字典项代码</th>
										<th data-name="name">字典项名称</th>
										<th data-name="sort" data-order="asc" class="sortable">排序</th>
										<th data-action="edit,remove" width="10%">操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</div>
	<!-- 字典项信息 -->
	<div class="modal fade" id="dictCodeModel" tabindex="-1"
		data-focus-on="input:first" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">字典项编辑</h4>
				</div>
				<div class="modal-body">
					<div class="alertContainer"></div>
					<form class="form-horizontal" role="form">
						<input type="hidden" name="codemap" /> 
						<input type="text" name="id" style="display: none" />

						<div class="form-body">
							<div class="form-group">
								<label class="col-md-3 control-label">字典项代码</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="字典项代码" name="code" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">字典名称</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="字典项名称" name="name" required>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">排序</label>
								<div class="col-md-9">
									<div class="input-icon right">
										<i class="fa"></i> <input type="text" class="form-control"
											placeholder="排序" name="sort" required digits>
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

	<!-- END PAGE CONTENT--> </content>

	<content tag="footer"> <script
		src="static/pages/scripts/crud.js" type="text/javascript"></script> <script>
			jQuery(document).ready(function() {
				var mapPage = new CRUDPage();
				var codePage = null;
				mapPage.init('system/dict/map', {
					modal : $('#dictModel'),
					table : $('#dictTable'),
					onUnhandleAction : function(grid, action, row) {
						if (action == 'list') {
							// 设置codemap
							$(".modal input[name='codemap']").each(function() {
								$(this).val(row.id);
							});
							
							if (codePage == null) {
								codePage = new CRUDPage();
								codePage.init('system/dict/code', {
									modal : $('#dictCodeModel'),
									table : $('#dictCodeTable'),
								});
							} else {
								codePage.reloadDataTable();
							}
							
							$("#dictCodeListModel").modal('show');
						}

					}
				});
			});
		</script> </content>
</body>
</html>