package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.model.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTOOut implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private final Set<RoleDTO> roles = new HashSet<>();

    public UserDTOOut() {}

    public UserDTOOut(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        user.getRoles().forEach(r -> roles.add(new RoleDTO(r)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
