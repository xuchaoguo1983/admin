package cn.zmvision.ccm.factory.system.dao.model;

import java.io.Serializable;

public class RoleMenuKey implements Serializable {
    private Integer roleId;

    private String menuId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}