package cn.zmvision.ccm.factory.base.bo;


/**
 * 配合前端JsTree树的数据对象
 * 
 * @author xuchaoguo
 * 
 */
public class JsTreeElement {
	private String id;
	private String text;
	private String parent;
	private String icon;
	private JsTreeStatus status;
	private Object li_attr;
	private Object a_attr;

	public JsTreeElement() {
		status = new JsTreeStatus();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public JsTreeStatus getStatus() {
		return status;
	}

	public void setStatus(JsTreeStatus status) {
		this.status = status;
	}

	public Object getLi_attr() {
		return li_attr;
	}

	public void setLi_attr(Object li_attr) {
		this.li_attr = li_attr;
	}

	public Object getA_attr() {
		return a_attr;
	}

	public void setA_attr(Object a_attr) {
		this.a_attr = a_attr;
	}

	public class JsTreeStatus {
		private boolean opened = false;
		private boolean disabled = false;
		private boolean selected = false;

		public boolean isOpened() {
			return opened;
		}

		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

	}
}
