package cn.zmvision.ccm.factory.base.bo;

import java.util.ResourceBundle;

public enum Message {
	SUCCESS(0), 
	ERROR(-1), 
	DATA_ERROR(-2), 
	DATA_MISSING(-3),

	LOGIN_ACCOUNT_INCORRECT(-10), 
	LOGIN_PASSOWRD_INCORRECT(-11), 
	LOGIN_ACCOUNT_LOCKED(-12), 
	LOGIN_VERIFY_CODE_INCORRECT(-13),
	LOGIN_FAILED(-19),
	
	MENU_PARENT_NOT_EXIST(-20),
	
	DICT_CODE_DUPLICATE(-30),

	;

	private int code;

	Message(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	private final static ResourceBundle bundle = ResourceBundle
			.getBundle("message");

	public String getText() {
		String key = this.name();
		try {
			return new String(bundle.getString(key).getBytes("ISO-8859-1"),
					"UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

}
