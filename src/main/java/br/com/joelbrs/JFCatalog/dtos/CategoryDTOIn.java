package br.com.joelbrs.JFCatalog.dtos;

import java.io.Serializable;

public class CategoryDTOIn implements Serializable {
    private String name;

    public CategoryDTOIn() {}

    public CategoryDTOIn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
