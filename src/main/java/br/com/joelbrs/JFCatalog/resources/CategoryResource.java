package br.com.joelbrs.JFCatalog.resources;

import br.com.joelbrs.JFCatalog.dtos.CategoryDTO;
import br.com.joelbrs.JFCatalog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryResource {
    Page<CategoryDTO> findAllPaged(Pageable pageable);
    CategoryDTO findById(Long id);
}
