/**
 * 角色管理
 */
var RolePage = function() {
	var grid = null;

	var initMenuTree = function() {
		$('#menutree').jstree({
			'core' : {
				"themes" : {
					"responsive" : false
				},
				// so that create works
				"check_callback" : true,
				'data' : {
					'url' : function(node) {
						return 'system/menu/tree';
					},
					'data' : function(node) {
						return {
							'id' : node.id
						};
					}
				}
			},

			'plugins' : [ "checkbox" ],

		});
	};

	/**
	 * 根据角色ID自动选中已关联菜单项
	 */
	var setMenuTree = function(roleId) {
		$('#menutree').jstree("deselect_all");

		if (roleId == null)
			return;

		$.post('system/role/menu?id=' + roleId, function(res) {
			if (res.code == 0) {
				for (var i = 0; i < res.data.length; i++) {
					var menuId = res.data[i];
					var parent = $('#menutree').jstree("get_parent", menuId);

					// open 1st before select
					if (!$('#menutree').jstree('open_node', parent, function() {
						$('#menutree').jstree("select_node", menuId);
					})) {
						$('#menutree').jstree("select_node", menuId);
					}
				}

			}
		});
	};

	var handleModal = function() {
		$("#btnNewRole").click(function() {
			setMenuTree();
			$("#roleModel").modal('show');
		});

		Helper.initModal("#roleModel", 'system/role/save', function() {
			var menuIds = $('#menutree').jstree("get_selected", false);
			// 删除重复元素
			$('#roleModel #menuIds').val(
					menuIds.join(",").match(/([^,]+)(?!.*\1)/ig));
		}, function() {
			if (grid)
				grid.reload();
		});
	};
	var handleDataTable = function() {
		grid = Helper.initDataTable($("#roleTable"), "system/role/page",
				function(grid, action, row) {
					switch (action) {
					case 'edit':// 编辑
						var modal = $("#roleModel");
						$("form", modal).autofill(row);
						modal.modal('show');
						setMenuTree(row.id);
						break;
					case 'remove':// 删除
						bootbox.confirm("确定删除么？", function(result) {
							if (!result)
								return;
							$.post("system/role/delete/" + row.id,
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
			initMenuTree();
		}
	};
}();