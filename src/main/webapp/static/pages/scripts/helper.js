var Helper = function() {
	return {
		/**
		 * 模块弹出框标准化初始化方法
		 * modal － 模式对话框
		 * url - 数据提交接口
		 */
		initModal : function(modal, actionUrl, preSubmit, successHandle) {
			var _form = $('form', $(modal));
			
			// validator最多只有一个实例
			_form.validate().destroy();

			_form.validate({
				submitHandler : function(form) {
					if (preSubmit)
						preSubmit.call(null, _form);

					$.post(actionUrl, _form.serialize(), function(res,
							status) {
						Metronic.alert({
							container : modal + ' .alertContainer',
							type : (res.code == 0 ? 'success' : 'danger'),
							icon : (res.code == 0 ? 'check' : 'warning'),
							message : res.message,
						});

						if (res.code == 0) {
							if (successHandle)
								successHandle.call(null, res.data);

							$(modal).modal('hide');
						}
					});
				}
			});

			_form.keypress(function(e) {
				if (e.which == 13) {
					if (_form.validate().form()) {
						_form.submit();
					}
					return false;
				}
			});

			$(modal).on('hide.bs.modal', function() {
				_form.validate().resetForm();
				_form[0].reset();

				$('.alertContainer', $(this)).html('');
			});

			$('.submit', $(modal)).click(function() {
				_form.submit();
			});
		},

		/**
		 * 数据表格标准化初始化方法
		 * table - 表格对象
		 * pageUrl - Ajax数据分页查询接口
		 * rowActionHandler - 行操作回调（必须配合data-action属性操作）
		 */
		initDataTable : function(table, pageUrl, rowActionHandler) {
			var datatable = new Datatable();

			datatable.init({
				src : table,
				onRowAction : function(grid, action, row) {
					if (rowActionHandler)
						rowActionHandler(grid, action, row);
				},
				dataTable : {
					"ajax" : {
						"url" : pageUrl
					}
				}
			});

			return datatable;
		},

	};

}();
