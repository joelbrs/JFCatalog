package br.com.joelbrs.JFCatalog.dtos;

import br.com.joelbrs.JFCatalog.enums.AuthorityEnum;
import br.com.joelbrs.JFCatalog.model.Role;

import java.io.Serializable;

public class RoleDTO implements Serializable {
    private Long id;
    private AuthorityEnum authorityEnum;

    public RoleDTO() {}

    public RoleDTO(Long id, Long authority) {
        this.id = id;
        setAuthority(authority);
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authorityEnum = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityEnum getAuthority() {
        return authorityEnum;
    }

    public void setAuthority(Long authority) {
        this.authorityEnum = AuthorityEnum.valueOf(authority);
    }
}
