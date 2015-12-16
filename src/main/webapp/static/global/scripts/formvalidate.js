/**
 * 默认的表单验证样式
 */

jQuery.validator.setDefaults({
	errorElement: 'span', //default input error message container
    errorClass: 'help-block help-block-error', // default input error message class
    focusInvalid: false, // do not focus the last invalid input
    
	invalidHandler: function (event, validator) { //display error alert on form submit

    },

    errorPlacement: function (error, element) { // render error placement for each input type
        var icon = $(element).parent('.input-icon').children('i');
        icon.removeClass('fa-check').addClass("fa-warning");  
        icon.attr("data-original-title", error.text()).tooltip();
    },

    highlight: function (element) { // hightlight error inputs
        $(element)
            .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group   
    },

    unhighlight: function (element) { // revert the change done by hightlight
    	if (this.lastElement != null)
    		return;
    	$(element)
        .closest('.form-group').removeClass("has-success").removeClass('has-error');
    	
    	var icon = $(element).parent('.input-icon').children('i');
    	icon.removeClass("fa-warning");
    },

    success: function (label, element) {
        var icon = $(element).parent('.input-icon').children('i');
        $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
        icon.removeClass("fa-warning");
    },

    submitHandler: function (form) {
        form[0].submit(); // submit the form
    }
});
