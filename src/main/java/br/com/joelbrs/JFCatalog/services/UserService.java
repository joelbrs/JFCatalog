package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.UserDTOIn;
import br.com.joelbrs.JFCatalog.dtos.UserDTOOut;
import br.com.joelbrs.JFCatalog.model.User;
import br.com.joelbrs.JFCatalog.repositories.UserRepository;
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
public class UserService implements GenericResource<UserDTOOut, UserDTOIn> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserDTOOut> findAllPaged(Pageable pageable) {
        return userRepository.findAll(pageable).map(u -> new UserDTOOut(u, u.getRoles()));
    }

    @Override
    public UserDTOOut findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Not Found: " + id));

        return new UserDTOOut(user, user.getRoles());
    }

    @Override
    public UserDTOOut insert(UserDTOIn dto) {
        User user = new User(null, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());

        return new UserDTOOut(userRepository.save(user));
    }

    @Override
    public UserDTOOut update(Long id, UserDTOIn dto) {
        try {
            User user = userRepository.getReferenceById(id);

            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());

            return new UserDTOOut(userRepository.save(user));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation!");
        }
    }
}



















