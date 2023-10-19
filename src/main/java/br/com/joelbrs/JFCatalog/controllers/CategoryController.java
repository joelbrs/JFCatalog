package br.com.joelbrs.JFCatalog.controllers;

import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.resources.CategoryResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController implements CategoryResource {

    @GetMapping
    @Override
    public Category findAll() {
        return null;
    }
}
