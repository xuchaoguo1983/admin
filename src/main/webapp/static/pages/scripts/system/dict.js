/**
 * 数据字典
 */
var DictPage = function() {
	var mapGrid = null;
	var codeGrid = null;

	var handleModal = function() {
		Helper.initModal("#dictModel", "system/dict/map/save", null,
				function() {
					if (mapGrid)
						mapGrid.reload();
				});

		Helper.initModal("#dictCodeModel", "system/dict/code/save", null,
				function() {
					if (codeGrid)
						codeGrid.reload();
				});
	};
	var handleDataTable = function() {
		mapGrid = Helper.initDataTable($("#dictTable"), "system/dict/map/page",
				function(grid, action, row) {
					switch (action) {
					case 'list':
						// 设置codemap
						$(".modal input[name='codemap']").each(function() {
							$(this).val(row.code);
						});

						if (codeGrid == null)
							codeGrid = Helper.initDataTable(
									$("#dictCodeTable"),
									"system/dict/code/page", function(grid,
											action, row) {
										switch (action) {
										case 'edit'://编辑字典项
											var modal = $("#dictCodeModel");
											$("form", modal).autofill(row);
											modal.modal('show');
											break;
										case 'remove'://删除字典项
											bootbox.confirm("确定删除么？", function(result) {
												if (!result)
													return;
												$.post("system/dict/code/delete/" + row.id,
													function(res) {
														if (res.code != 0) {
															Metronic.alert({
																message : res.message
															});
														} else {
															codeGrid.reload();
														}
													});
											});
											break;
										}
									});
						else
							codeGrid.reload();

						$("#dictCodeListModel").modal('show');
						break;
					case 'edit':// 编辑
						var modal = $("#dictModel");
						$("form", modal).autofill(row);
						modal.modal('show');
						break;
					case 'remove':// 删除
						bootbox.confirm("确定删除么？", function(result) {
							if (!result)
								return;
							$.post("system/dict/map/delete/" + row.code,
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