/**
 * 菜单管理
 */
var MenuPage = function() {
	var grid = null;

	var handleModal = function() {
		Helper.initModal("#menuModel", 'system/menu/save', null, function() {
			if (grid)
				grid.reload();
		});
	};
	var handleDataTable = function() {
		grid = Helper.initDataTable($("#menuTable"), "system/menu/page",
				function(grid, action, row) {
					switch (action) {
					case 'edit':// 编辑
						var modal = $("#menuModel");
						$("form", modal).autofill(row);
						modal.modal('show');
						break;
					case 'remove':// 删除
						bootbox.confirm("确定删除么？", function(result) {
							if (!result)
								return;
							$.post("system/menu/delete/" + row.id,
									function(res) {
										if (res.code != 0) {
											Metronic.alert({
												message : res.message
											});
										} else {
											grid.reload();
										}
									});
						});
						break;
					}

				});
	};

	return {
		// main function to initiate the module
		init : function() {
			handleDataTable();
			handleModal();
		}
	};
}();