package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.CategoryDTOIn;
import br.com.joelbrs.JFCatalog.dtos.CategoryDTOOut;

import br.com.joelbrs.JFCatalog.dtos.ProductDTOOut;
import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.model.Product;
import br.com.joelbrs.JFCatalog.repositories.CategoryRepository;
import br.com.joelbrs.JFCatalog.repositories.ProductRepository;
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
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTOOut> findAllPaged(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDTOOut::new);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTOOut findById(Long id) {
        return new CategoryDTOOut(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found, ID: " + id)));
    }

    @Override
    @Transactional
    public CategoryDTOOut insert(CategoryDTOIn dto) {
        Category category = new Category();

        dtoToEntity(dto, category);
        category.setCreatedAt(Instant.now());
        return new CategoryDTOOut(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryDTOOut update(Long id, CategoryDTOIn dto) {
        try {
            Category category = categoryRepository.getReferenceById(id);

            dtoToEntity(dto, category);
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

    private void dtoToEntity(CategoryDTOIn dto, Category category) {
        category.setName(dto.getName());

        for (Long categoryDto : dto.getProducts()) {
            try {
                Product product = productRepository.getReferenceById(categoryDto);

                category.addProduct(product);
            }
            catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException("Id Not Found: " + categoryDto);
            }
        }
    }
}








