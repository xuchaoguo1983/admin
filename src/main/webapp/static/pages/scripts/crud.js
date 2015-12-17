/**
 * CRUD标准模版JS. 如果页面仅需要使用标准的CRUD操作，则可以直接使用此脚本; 所需参数仅为相对调用路径，例如:system/menu
 * 另外，相应JSP文件请务必按照规范编写，可参考系统管理相关功能模块
 */

var CRUDPage = function() {
	var _path = null;
	var _modal = null;
	var _dataTable = null;
	var _dataGrid = null;

	var initDom = function(path) {
		_path = path;

		var page = $(".page-content");
		_modal = $('.modal', page);
		_dataTable = $('table', page);
	};

	var handleModal = function() {
		var modalId = "#" + _modal.attr("id");

		Helper.initModal(modalId, _path + "/save", null, function() {
			if (_dataGrid)
				_dataGrid.reload();
		});
	};

	var handleDataTable = function() {
		_dataGrid = Helper.initDataTable(_dataTable, path + "/page", function(
				grid, action, row) {
			switch (action) {
			case 'edit':// 编辑字典项
				$("form", _modal).autofill(row);
				_modal.modal('show');
				break;
			case 'remove':// 删除字典项
				bootbox.confirm("确定删除么？", function(result) {
					if (!result)
						return;
					$.post(_path + "/delete/" + row.id, function(res) {
						if (res.code != 0) {
							Metronic.alert({
								message : res.message
							});
						} else {
							_dataGrid.reload();
						}
					});
				});
				break;
			}
		});
	};

	return {
		init : function(path) {
			initDom(path);
			handleModal();
			handleDataTable();
		}

	};

}();