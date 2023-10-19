package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.CategoryDTO;

import br.com.joelbrs.JFCatalog.repositories.CategoryRepository;
import br.com.joelbrs.JFCatalog.resources.CategoryResource;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService implements CategoryResource {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        return new CategoryDTO(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found, ID: " + id)));
    }
}
