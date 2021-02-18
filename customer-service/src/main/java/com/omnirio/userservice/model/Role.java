package com.omnirio.userservice.model;

public class Role {

    private String roleId;

    private String roleName;

    private String roleCode;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Role(String roleId, String roleName, String roleCode) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCode = roleCode;
    }
}
