package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.utils.DateControl;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProductDTOIn implements Serializable {

    @NotBlank(message = "Required Field")
    @Size(min = 5, max = 60, message = "Fields's length should be between 5 and 60 characters")
    private String name;
    private String description;

    @Positive
    @NotBlank(message = "Required Field")
    private BigDecimal price;
    private String imgUrl;

    @PastOrPresent(message = "Field should not be in future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.PATTERN_DATE, locale = "pt-BR", timezone = "America/Fortaleza")
    private Instant date;
    private Set<Long> categories = new HashSet<>();

    public ProductDTOIn() {}

    public ProductDTOIn(String name, String description, BigDecimal price, String imgUrl, Instant date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
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

    public Set<Long> getCategories() {
        return categories;
    }

    public void setCategories(Set<Long> categories) {
        this.categories = categories;
    }
}
