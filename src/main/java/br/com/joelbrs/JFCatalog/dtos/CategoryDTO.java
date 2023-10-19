package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.model.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private Long id;
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        setId(category.getId());
        setName(category.getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
