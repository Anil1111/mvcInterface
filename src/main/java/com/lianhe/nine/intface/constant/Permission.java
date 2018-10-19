package com.lianhe.nine.intface.constant;

/**
 * @author : Rubi
 * @version : 2018-10-17 14:24
 */

public enum Permission {
    C("c","[c]"),
    R("r","[r]"),
    U("u","[u]"),
    D("d","[d]");

    private String name;
    private String buildedName;
    Permission(String name, String buildedName) {
        this.name=name;
        this.buildedName=buildedName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildedName() {
        return buildedName;
    }

    public void setBuildedName(String buildedName) {
        this.buildedName = buildedName;
    }
}
