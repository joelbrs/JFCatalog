package br.com.joelbrs.JFCatalog.enums;

public enum Authority {
    ROLE_OPERATOR(1L),
    ROLE_ADMIN(2L);

    private Long code;

    Authority(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public static Authority valueOf(Long code) {
        for (Authority authority : values()) {
            if (code.equals(authority.getCode())) {
                return authority;
            }
        }
        return null;
    }
}
