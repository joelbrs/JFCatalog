package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.RoleDTO;
import br.com.joelbrs.JFCatalog.model.Role;
import br.com.joelbrs.JFCatalog.repositories.RoleRepository;
import br.com.joelbrs.JFCatalog.resources.GenericResource;
import br.com.joelbrs.JFCatalog.services.exceptions.DatabaseException;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleService implements GenericResource<RoleDTO, RoleDTO> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<RoleDTO> findAllPaged(Pageable pageable) {
        return roleRepository.findAll(pageable).map(RoleDTO::new);
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found: " + id));

        return new RoleDTO(role);
    }

    @Override
    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        return new RoleDTO(roleRepository.save(new Role(null, dto.getAuthority())));
    }

    @Override
    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        try {
            Role role = roleRepository.getReferenceById(id);

            role.setAuthority(dto.getAuthority());
            return new RoleDTO(roleRepository.save(role));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("ID Not Found: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }
}











