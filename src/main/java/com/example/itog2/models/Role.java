package com.example.itog2.models;


public enum Role {
    ADMIN,
    MANAGER,
    EMPLOYEE;

    // Метод для проверки, есть ли у роли определённое название (опционально)
    public static boolean isValidRole(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return true;
            }
        }
        return false;
    }
}


