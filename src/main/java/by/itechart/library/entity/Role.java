package by.itechart.library.entity;

import lombok.Getter;

public enum Role {
    ADMIN(1),
    USER(2);
    @Getter
    private int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }
}
