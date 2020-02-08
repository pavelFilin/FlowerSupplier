package ru.filin.HavachMVC.model.userManagement;

public enum RoleConstant {
    ADMIN(1), USER(2);

    private int code;

    private RoleConstant(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
