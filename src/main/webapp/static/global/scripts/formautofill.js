/**
 * 自动填充表单
 * 
 * @param $
 */
(function($) {
	$.fn.extend({
		autofill : function(data, options) {
			var settings = {
				restrict : true
			}, self = this;

			if (options) {
				$.extend(settings, options);
			}

			return this.each(function() {
				$.each(data, function(k, v) {
					var selector, elt;

					selector = '[name="' + k + '"]';
					elt = (settings.restrict) ? self.find(selector)
							: $(selector);

					if (elt.length > 1) {
						// checkbox or radio
						elt.each(function() {
							var val = $(this).val();
							if (!$.isArray(v))
								$(this).prop('checked', v+'' == val);
							else {
								for (var i = 0; i < v.length; i++) {
									if (v[i]+'' == val) {
										$(this).prop("checked", true);
										$(this).uniform();
										return;
									}
								}

								$(this).prop('checked', false);
							}
							
							$(this).uniform();
						});
					} else {
						elt.val(v);
					}
				});
			});
		}
	});
})(jQuery);