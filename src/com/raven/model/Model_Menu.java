package com.raven.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu( String name, MenuType type) {
        
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {
    }

    
    private String name;
    private MenuType type;

    
    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
