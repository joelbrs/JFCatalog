package br.com.joelbrs.JFCatalog.controllers;

import br.com.joelbrs.JFCatalog.dtos.RoleDTO;
import br.com.joelbrs.JFCatalog.services.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAllPaged(Pageable pageable) {
        return ResponseEntity.ok().body(roleService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(roleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO dto) {
        return ResponseEntity.ok().body(roleService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody RoleDTO dto) {
        return ResponseEntity.ok().body(roleService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
