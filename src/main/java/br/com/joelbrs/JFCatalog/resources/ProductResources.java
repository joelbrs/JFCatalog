package br.com.joelbrs.JFCatalog.resources;


import br.com.joelbrs.JFCatalog.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductResources {
    Page<ProductDTO> findAllPaged(Pageable pageable);
    ProductDTO findById(Long id);
    ProductDTO insert(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    void delete(Long id);
}
