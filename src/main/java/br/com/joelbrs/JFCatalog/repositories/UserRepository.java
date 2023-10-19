package br.com.joelbrs.JFCatalog.repositories;

import br.com.joelbrs.JFCatalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
