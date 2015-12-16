package cn.zmvision.ccm.factory.base.bo;

import java.io.Serializable;

import org.springframework.ui.Model;

public class JsonResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static String MODEL_ATTR_ERROR = "error";

	private int code;
	private String message;
	private Object data;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public JsonResult() {
		this(Message.SUCCESS);
		this.data = null;
	}

	public JsonResult(Object data) {
		this(Message.SUCCESS);
		this.data = data;
	}

	public JsonResult(Message message) {
		this.code = message.getCode();
		this.message = message.getText();
	}

	public void writeToRequest(Model model) {
		model.addAttribute(MODEL_ATTR_ERROR, this);
	}
}
