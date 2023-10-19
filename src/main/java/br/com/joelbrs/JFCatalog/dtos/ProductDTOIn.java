package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.utils.DateControl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class ProductDTOIn implements Serializable {
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.PATTERN_DATE, locale = "pt-BR", timezone = "America/Fortaleza")
    private Instant date;

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
}
