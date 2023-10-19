package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.ProductDTO;
import br.com.joelbrs.JFCatalog.model.Product;
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

@Service
public class ProductService implements GenericResource<ProductDTO, ProductDTO> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        return productRepository.findAll(pageable).map(p -> new ProductDTO(p, p.getCategories()));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found, ID: " + id));

        return new ProductDTO(product, product.getCategories());
    }

    @Override
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        return new ProductDTO(productRepository.save(new Product(null, dto.getName(), dto.getName(), dto.getPrice(),dto.getImgUrl(), dto.getDate())));
    }

    @Override
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.getReferenceById(id);

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImgUrl(dto.getImgUrl());
            product.setDate(dto.getDate());
            return new ProductDTO(productRepository.save(product));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }
}
