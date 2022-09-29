package thedevelopers.project.demo.domain;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

public enum RoleName {

    ADMIN("Admin"), OPERARIO("Operario");

    private String textName;
    RoleName(String textName){
        this.textName = textName;
    }

    public String getTextName(){
        return textName;
    }

    public static List<RoleName> getRoles(){
        return Arrays.asList(ADMIN, OPERARIO);
    }

}
