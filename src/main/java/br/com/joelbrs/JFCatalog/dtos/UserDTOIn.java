package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.model.Role;
import br.com.joelbrs.JFCatalog.model.User;

import java.io.Serializable;
import java.util.Set;

public class UserDTOIn implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserDTOIn() {}

    public UserDTOIn(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserDTOIn(User user, Set<Role> roles) {
        this(user);

        roles.forEach(r -> new RoleDTO(r));
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}