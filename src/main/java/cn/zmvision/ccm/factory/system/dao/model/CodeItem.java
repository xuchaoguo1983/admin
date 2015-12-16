package cn.zmvision.ccm.factory.system.dao.model;

import java.io.Serializable;

public class CodeItem implements Serializable {
    private Integer id;

    private String code;

    private String codemap;

    private String name;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodemap() {
        return codemap;
    }

    public void setCodemap(String codemap) {
        this.codemap = codemap == null ? null : codemap.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}