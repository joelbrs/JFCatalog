package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.model.Product;
import br.com.joelbrs.JFCatalog.utils.DateControl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProductDTOOut implements Serializable {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.PATTERN_DATE, locale = "pt-BR", timezone = "America/Fortaleza")
    private Instant date;
    private Set<CategoryDTOOut> categories = new HashSet<>();

    public ProductDTOOut() {}

    public ProductDTOOut(Long id, String name, String description, BigDecimal price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTOOut(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
        this.date = product.getDate();
    }

    public ProductDTOOut(Product product, Set<Category> categories) {
        this(product);

        categories.forEach(c -> this.categories.add(new CategoryDTOOut(c)));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<CategoryDTOOut> getCategories() {
        return categories;
    }
}
