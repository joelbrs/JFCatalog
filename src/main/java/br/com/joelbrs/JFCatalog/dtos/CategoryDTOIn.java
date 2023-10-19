package br.com.joelbrs.JFCatalog.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CategoryDTOIn implements Serializable {
    private String name;

    private Set<Long> products = new HashSet<>();

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

    public Set<Long> getProducts() {
        return products;
    }

    public void setProducts(Set<Long> products) {
        this.products = products;
    }
}
