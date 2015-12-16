package cn.zmvision.ccm.factory.system.dao.model;

import java.io.Serializable;

public class CodeMap implements Serializable {
    private String code;

    private String name;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}