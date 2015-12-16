/**
 * 系统登陆
 */

var Login = function() {
	var handleLogin = function() {
		$('.login-form').validate({
	        submitHandler:function(form){
	        	$("input[type=password]").each(function() {
					var val = $(this).val();
					$(this).val($.md5(val));
				});
				
	            form.submit();
	        }
	    });

		$('.login-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('.login-form').validate().form()) {
					$('.login-form').submit();
				}
				return false;
			}
		});

		// 刷新验证码
		$('#verifycodeImg').on(
				'click',
				function() {
					var d = new Date();
					$("#verifycodeImg").attr("src",
							_basePath + "system/auth/verifycode?" + d.getTime());
				});
		
	};

	return {
		// main function to initiate the module
		init : function() {
			handleLogin();
		}
	};
}();