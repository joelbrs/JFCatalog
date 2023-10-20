package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.UserDTOIn;
import br.com.joelbrs.JFCatalog.dtos.UserDTOOut;
import br.com.joelbrs.JFCatalog.model.Role;
import br.com.joelbrs.JFCatalog.model.User;
import br.com.joelbrs.JFCatalog.repositories.RoleRepository;
import br.com.joelbrs.JFCatalog.repositories.UserRepository;
import br.com.joelbrs.JFCatalog.resources.GenericResource;
import br.com.joelbrs.JFCatalog.services.exceptions.DatabaseException;
import br.com.joelbrs.JFCatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements GenericResource<UserDTOOut, UserDTOIn> {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserDTOOut> findAllPaged(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTOOut::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTOOut findById(Long id) {
        return new UserDTOOut(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found: " + id)));
    }

    @Override
    @Transactional
    public UserDTOOut insert(UserDTOIn dto) {
        User user = new User();

        dtoToEntity(dto, user);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return new UserDTOOut(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDTOOut update(Long id, UserDTOIn dto) {
        try {
            User user = userRepository.getReferenceById(id);

            dtoToEntity(dto, user);
            return new UserDTOOut(userRepository.save(user));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation!");
        }
    }

    private void dtoToEntity(UserDTOIn dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        user.clearRoles();
        for (Long roleDTO : dto.getRoles()) {
            try {
                Role role = roleRepository.getReferenceById(roleDTO);

                user.addRole(role);
            }
            catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException("Role Not Found, ID: " + roleDTO);
            }
        }
    }
}



















