/**
 * 用户管理
 */
var UserPage = function() {
	var grid = null;
	
	var initRoleControls = function() {
		$('#roleListdiv').html('');
		$.post('system/role/page', function(res) {
			if (res.code == 0) {
				var html = '';
				for (var i=0;i<res.data.length;i++) {
					var row = res.data[i];
					html += '<input name="roleId" type="checkbox" value="'+row.id+'">';
					html += '<span>' + row.name + '</span>';
				}
				$('#roleListdiv').html(html);
				$('#roleListdiv input[type=checkbox]').each(function(){
					$(this).uniform();
				});
			}
		});
	};
	
	var setUserRole = function(userId) {
		$('#roleListdiv input[type=checkbox]').each(function(){
			$(this).attr("checked",false);
			$(this).uniform();
		});
		
		if (userId == null)
			return;

		$.post('system/user/role?id=' + userId, function(res) {
			if (res.code == 0) {
				$('#roleListdiv input[type=checkbox]').each(function(){
					var val = parseInt($(this).val());
					if ($.inArray(val, res.data) != -1) {
						$(this).attr("checked", true);
						$(this).uniform();
					}
				});
			}
		});
	};

	var handleModal = function() {
		$("#btnNewUser").click(function() {
			setUserRole();
			$("#userModel").modal('show');
		});

		Helper.initModal("#userModel", 'system/user/save', function() {

		}, function() {
			if (grid)
				grid.reload();
		});
	};
	var handleDataTable = function() {
		grid = Helper.initDataTable($("#userTable"), "system/user/page",
				function(grid, action, row) {
					switch (action) {
					case 'edit':// 编辑
						var modal = $("#userModel");
						$("form", modal).autofill(row);
						setUserRole(row.id);
						modal.modal('show');
						break;
					case 'remove':// 删除
						bootbox.confirm("确定删除么？", function(result) {
							if (!result)
								return;
							$.post("system/user/delete/" + row.id,
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
			initRoleControls();
			handleDataTable();
			handleModal();
		}
	};
}();