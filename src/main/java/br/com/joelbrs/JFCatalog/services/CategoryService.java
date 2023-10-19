package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.CategoryDTOIn;
import br.com.joelbrs.JFCatalog.dtos.CategoryDTOOut;

import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.repositories.CategoryRepository;
import br.com.joelbrs.JFCatalog.resources.GenericResource;
import br.com.joelbrs.JFCatalog.services.exceptions.DatabaseException;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class CategoryService implements GenericResource<CategoryDTOOut, CategoryDTOIn> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTOOut> findAllPaged(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(c -> new CategoryDTOOut(c, c.getProducts()));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTOOut findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found, ID: " + id));

        return new CategoryDTOOut(category, category.getProducts());
    }

    @Override
    @Transactional
    public CategoryDTOOut insert(CategoryDTOIn dto) {
        return new CategoryDTOOut(categoryRepository.save(new Category(null, dto.getName(), Instant.now(), null)));
    }

    @Override
    @Transactional
    public CategoryDTOOut update(Long id, CategoryDTOIn dto) {
        try {
            Category category = categoryRepository.getReferenceById(id);

            category.setName(dto.getName());
            category.setUpdatedAt(Instant.now());
            return new CategoryDTOOut(categoryRepository.save(category));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }
}
