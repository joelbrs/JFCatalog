package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.RoleDTO;
import br.com.joelbrs.JFCatalog.model.Role;
import br.com.joelbrs.JFCatalog.repositories.RoleRepository;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found: " + id));

        return new RoleDTO(role);
    }
}











