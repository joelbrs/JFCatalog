package br.com.joelbrs.JFCatalog.model;

import br.com.joelbrs.JFCatalog.enums.Authority;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authority;

    public Role() {}

    public Role(Long id, Authority authority) {
        this.id = id;
        setAuthority(authority);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authority getAuthority() {
        return Authority.valueOf(authority);
    }

    public void setAuthority(Authority authority) {
        this.authority = authority.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
