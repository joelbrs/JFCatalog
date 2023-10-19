package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.utils.DateControl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public class CategoryDTO implements Serializable {
    private Long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.PATTERN_DATE, locale = "pt-BR", timezone = "America/Fortaleza")
    private Instant createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.PATTERN_DATE, locale = "pt-BR", timezone = "America/Fortaleza")
    private Instant updatedAt;

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
