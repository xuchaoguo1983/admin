/**
 * App相关基础
 */

var App = function() {

	var handleGlobalInit = function() {
		/** AJAX 全局配置 */
		$.ajaxSetup({
			aysnc : false, // 默认同步加载
			type : "POST", // 默认使用POST方式
			success : function(res) {
				// 成功时默认弹出系统提示
				Metronic.alert({
					type : (res.code == 0 ? 'success' : 'danger'),
					icon : (res.code == 0 ? 'check' : 'warning'),
					message : res.message,
				});
			},
			error : function(jqXHR, textStatus, errorMsg) {
				// 出错时默认的处理函数
				Metronic.alert({
					message : '发送请求出错[' + jqXHR.status + ']：' + errorMsg
				});
			}
		});

		$(document).ajaxStart(function() {
			Metronic.startPageLoading({
				animate : true
			});
		});

		$(document).ajaxStop(function() {
			Metronic.stopPageLoading();
		});

		// 模式对话框栈式弹出的问题
		$(document).on(
				{
					'show.bs.modal' : function() {
						var zIndex = 10000 + (10 * $('.modal:visible').length);
						$(this).css('z-index', zIndex);
						setTimeout(function() {
							$('.modal-backdrop').not('.modal-stack').css(
									'z-index', zIndex - 1).addClass(
									'modal-stack');
						}, 0);
					},
					'hidden.bs.modal' : function() {
						if ($('.modal:visible').length > 0) {
							// restore the modal-open class to the body element,
							// so that scrolling works
							// properly after de-stacking a modal.
							setTimeout(function() {
								$(document.body).addClass('modal-open');
							}, 0);
						}
					}
				}, '.modal');

		// 自动绑定字典数据
		var codemaps = [];
		$(".page-content [data-codemap]").each(function() {
			var codemap = $(this).data("codemap");
			if ($.inArray(codemap, codemaps) == -1)
				codemaps.push(codemap);
		});

		Helper.initCodeMap(codemaps);
	};

	var handleMenuInit = function() {
		var url = window.location;
		var element = $('.page-sidebar-menu a').filter(function() {
			return this.href == url || url.href.indexOf(this.href) == 0;
		});

		if (element.length > 0) {
			var li = element.parent();
			li.addClass('active');

			li.parents("li").each(function() {
				$(this).addClass('active open');
			});
		}
	};

	var handleControlInit = function() {
		$(".page-content [data-type]").each(
				function() {
					var dataType = $(this).data("type");
					var dataName = $(this).data("name");
					var dataAjax = $(this).data("ajax");
					var multiple = $(this).data("multiple") || false;
					var required = $(this).attr("required") || false;
					if (dataType == 'checkbox') {
						// checkbox
						Helper.initCheckboxData($(this), dataName, dataAjax, {
							type : multiple ? 'checkbox' : 'radio',
							required : required ? "required" : ''
						});
					} else if (dataType == 'checktree') {
						// tree
						$(this).before(
								'<input type="hidden" name="' + dataName
										+ '" />');
						var tree = Helper.initCheckboxTreeData($(this),
								dataAjax, multiple);
						var that = this;
						tree.on('changed.jstree', function(e, data) {
							var selectedIds = $(this).jstree("get_selected",
									false);
							$(that).prev().val(
									selectedIds.join(","));
						});
					}
				});
	};

	var handlePasswordUpdate = function() {
		Helper.initModal('#passwordModel', 'main/setpassord', function(form) {
			$("input[type=password]", form).each(function() {
				var val = $(this).val();
				$(this).val($.md5(val));
			});
		}, null);
	};

	var handleUserInfoUpdate = function() {
		// TODO:
	};

	return {
		// main function to initiate the module
		init : function() {
			handleGlobalInit();// 全局设置
			handleMenuInit();// 菜单设置
			handleControlInit();// 控件初始化
			handlePasswordUpdate();// 密码修改
			handleUserInfoUpdate();// 用户信息修改
		}
	};
}();