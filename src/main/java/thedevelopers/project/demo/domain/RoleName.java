package thedevelopers.project.demo.domain;

import lombok.Data;

public enum RoleName {

    ADMIN("Admin"), OPERARIO("Operario");

    private String textName;
    RoleName(String textName){
        this.textName = textName;
    }

}
