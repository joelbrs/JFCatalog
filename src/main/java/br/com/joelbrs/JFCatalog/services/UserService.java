package br.com.joelbrs.JFCatalog.services;

import br.com.joelbrs.JFCatalog.dtos.UserDTOIn;
import br.com.joelbrs.JFCatalog.dtos.UserDTOOut;
import br.com.joelbrs.JFCatalog.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserDTOOut> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(u -> new UserDTOOut(u, u.getRoles()));
    }
}



















