/**
 * CRUD标准模版JS. 如果页面仅需要使用标准的CRUD操作，则可以直接使用此脚本; 所需参数仅为相对调用路径，例如:system/menu
 * 另外，相应JSP文件请务必按照规范编写，可参考系统管理相关功能模块
 * 
 * 本模版脚本增加了以下事件方便灵活使用（通过options注册）： 
 * 1. onShowModal －当模式对话框弹出时触发 
 * 2. onHideModal－当模式对话框关闭时触发 
 * 3. onSubmitModal － 当模式对话框提交时触发 
 * 4. onLoadModalData - 当加载模式对话框数据时触发 
 * 5. onUnhandleAction - 对于非edit和remove操作的触发
 */

var CRUDPage = function() {
	var _path = null;
	var _modal = null;
	var _table = null;
	var _dataTable = null;
	var _options = null;

	var initDom = function(path, options) {
		_path = path;
		_options = $.extend({}, {
			reload : false
		// 默认不重新加载
		}, options);

		_modal = _options.modal || $('.page-content .modal');
		_table = _options.table || $('.page-content table');

		_modal.on('show.bs.modal', function() {
			if (_options.onShowModal) {
				_options.onShowModal(null, _modal);
			}
		});

		_modal.on('hide.bs.modal', function() {
			if (_options.onHideModal) {
				_options.onHideModal(null, _modal);
			}
		});
	};

	var handleModal = function() {
		var modalId = "#" + _modal.attr("id");

		Helper.initModal(modalId, _path + "/save", function() {
			if (_options.onSubmitModal) {
				_options.onSubmitModal.call(null, _modal);
			}
		}, function() {
			if (_dataTable)
				_dataTable.reload();
		});
	};

	var handleDataTable = function() {
		_dataTable = Helper.initDataTable(_table, _path + "/page", function(
				dataTable, action, row) {
			switch (action) {
			case 'edit':
				if (_options.reload) {
					// 是否重新加载记录
					$.post(_path + '/query?id=' + row.id, function(res) {
						if (res.code == 0) {
							$("form", _modal).autofill(res.data);

							if (_options.onLoadModalData) {
								_options.onLoadModalData.call(null, _modal,
										res.data);
							}

							_modal.modal('show');
						}
					});
				} else {
					$("form", _modal).autofill(row);
					if (_options.onLoadModalData) {
						_options.onLoadModalData.call(null, _modal, row);
					}
					_modal.modal('show');
				}

				break;
			case 'remove':// 删除字典项
				bootbox.confirm("确定删除么？", function(result) {
					if (!result)
						return;
					$.post(_path + "/delete?id=" + row.id, function(res) {
						if (res.code != 0) {
							Metronic.alert({
								message : res.message
							});
						} else {
							_dataTable.reload();
						}
					});
				});
				break;
			default:
				if (_options.onUnhandleAction) {
					_options.onUnhandleAction.call(null, dataTable, action, row);
				}
				break;
			}
		});
	};

	return {
		init : function(path, options) {
			initDom(path, options);
			handleModal();
			handleDataTable();
			return this;
		},
		
		reloadDataTable : function() {
			_dataTable.reload();
		},

		getDataTable : function() {
			return _dataTable;
		}

	};

};