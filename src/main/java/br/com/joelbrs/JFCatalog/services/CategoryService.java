package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.CategoryDTO;

import br.com.joelbrs.JFCatalog.model.Category;
import br.com.joelbrs.JFCatalog.repositories.CategoryRepository;
import br.com.joelbrs.JFCatalog.resources.CategoryResource;
import br.com.joelbrs.JFCatalog.services.exceptions.DatabaseException;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        return new CategoryDTO(categoryRepository.save(new Category(null, dto.getName(), dto.getCreatedAt(), dto.getUpdatedAt())));
    }

    @Override
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            category.setName(dto.getName());

            return new CategoryDTO(categoryRepository.save(category));
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
