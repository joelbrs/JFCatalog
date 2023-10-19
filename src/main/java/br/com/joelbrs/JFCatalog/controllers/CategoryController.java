package br.com.joelbrs.JFCatalog.controllers;

import br.com.joelbrs.JFCatalog.dtos.CategoryDTOIn;
import br.com.joelbrs.JFCatalog.dtos.CategoryDTOOut;
import br.com.joelbrs.JFCatalog.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTOOut>> findAllPaged(Pageable pageable) {
        return ResponseEntity.ok().body(categoryService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTOOut> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTOOut> insert(@RequestBody CategoryDTOIn dto) {
        return ResponseEntity.ok().body(categoryService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTOOut> update(@PathVariable Long id, @RequestBody CategoryDTOIn dto) {
        return ResponseEntity.ok().body(categoryService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
