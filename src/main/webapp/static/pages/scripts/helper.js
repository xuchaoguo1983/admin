var Helper = function() {
	return {
		_codeMap : {},// 保存数据字典
		/**
		 * 模块弹出框标准化初始化方法 modal － 模式对话框 url - 数据提交接口
		 */
		initModal : function(modal, actionUrl, preSubmit, successHandle) {
			var _form = $('form', $(modal));

			// validator最多只有一个实例
			_form.validate().destroy();

			_form.validate({
				submitHandler : function(form) {
					if (preSubmit)
						preSubmit.call(null, _form);

					$.post(actionUrl, _form.serialize(), function(res, status) {
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

			$(modal).on(
					'hide.bs.modal',
					function() {
						_form.validate().resetForm();
						_form[0].reset();
						// 将uniform的控件重置
						var ufNodes = $(
								"input[type=checkbox], input[type=radio]",
								_form);
						if (ufNodes.size() > 0) {
							ufNodes.each(function() {
								$(this).show();
								$(this).uniform();
							});
						}
						// 将树勾选重置
						var trees = $('[data-type="checktree"]', _form);
						if (trees.size() > 0) {
							trees.each(function() {
								$(this).jstree("deselect_all");
							});
						}

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
		/**
		 * 字典数据初始化
		 */
		initCodeMap : function(req) {
			if (!$.isArray(req)) {
				alert('输入参数应为数组');
				return false;
			}

			if (req.length == 0)
				return false;

			var the = this;

			the._codeMap = {};

			$.ajax({
				url : 'system/dict/code/map',
				data : 'list=' + req.join(','),
				async : false,// 同步执行
				success : function(res) {
					if (res.code == 0) {
						for (var i = 0; i < res.data.length; i++) {
							var codeItem = res.data[i];

							var itemArr = the._codeMap[codeItem.codemap];
							if (itemArr == null) {
								itemArr = [];
								the._codeMap[codeItem.codemap] = itemArr;
							}

							itemArr.push({
								'code' : codeItem.code,
								'name' : codeItem.name
							});
						}

						the.initSelectData();
					}
				}
			});
		},

		getCodeData : function(codemap, key) {
			var items = this._codeMap[codemap];
			if (items == null) {
				alert('字典' + codemap + '不存在');
				return key;
			}

			for (var i = 0; i < items.length; i++) {
				var item = items[i];
				if (item.code == key)
					return item.name;
			}

			return key;
		},

		/**
		 * 初始化Select下拉列表字典数据
		 */
		initSelectData : function() {

			var the = this;
			$('select[data-codemap]').each(
					function() {
						var codemap = $(this).attr("data-codemap");
						var items = the._codeMap[codemap];
						if (items == null) {
							alert('字典' + codemap + '不存在');
							return false;
						}

						$(this).empty();
						$(this).append("<option value=''>请选择</option>");

						for (var i = 0; i < items.length; i++) {
							var item = items[i];

							$(this).append(
									"<option value='" + item.code + "'>"
											+ item.name + "</option>");
						}
					});
		},

		/**
		 * 初始化checkbox或radio button
		 */
		initCheckboxData : function(container, field, url, options) {
			$.ajax({
				url : url,
				async : false,// 同步执行
				success : function(res) {
					if (res.code == 0) {
						var type = options.type || 'checkbox';
						var require = options.required || '';
						var key = options.key || 'id';
						var value = options.value || 'name';
						var html = '';
						for (var i = 0; i < res.data.length; i++) {
							var row = res.data[i];
							html += '<input name="' + field + '" type="' + type
									+ '" ' + require + ' value="' + row[key]
									+ '">';
							html += '<span>' + row[value]
									+ '</span>&nbsp;&nbsp;';
						}

						container.html(html);

						$('input[type=checkbox]', container).each(function() {
							$(this).uniform();
						});
					}
				}
			});
		},

		/**
		 * 初始化JsTree树
		 */
		initCheckboxTreeData : function(container, url, multiple) {
			var chkOptions = {};
			if (!multiple) {
				chkOptions.three_state = false;
				chkOptions.cascade = 'undetermined';
			}

			return container.jstree({
				'core' : {
					"themes" : {
						"responsive" : false
					},
					// so that create works
					"check_callback" : true,
					"multiple" : multiple,
					'data' : {
						'url' : function(node) {
							return url;
						},
						'data' : function(node) {
							return {
								'id' : node.id
							};
						}
					}
				},

				'checkbox' : chkOptions,
				'plugins' : [ "checkbox" ],

			});
		}
	};

}();