package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.repositories.CategoryRepository;
import br.com.joelbrs.JFCatalog.resources.CategoryResource;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryResource {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category findAll() {
        return null;
    }
}
