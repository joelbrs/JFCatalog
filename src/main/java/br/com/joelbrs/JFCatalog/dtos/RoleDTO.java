package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.enums.Authority;
import br.com.joelbrs.JFCatalog.model.Role;

import java.io.Serializable;

public class RoleDTO implements Serializable {
    private Long id;
    private Authority authority;

    public RoleDTO() {}

    public RoleDTO(Long id, Long authority) {
        this.id = id;
        setAuthority(authority);
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Long authority) {
        this.authority = Authority.valueOf(authority);
    }
}
