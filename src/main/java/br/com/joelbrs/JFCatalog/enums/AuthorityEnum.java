package br.com.joelbrs.JFCatalog.enums;

public enum AuthorityEnum {
    ROLE_OPERATOR(1L),
    ROLE_ADMIN(2L);

    private Long code;

    AuthorityEnum(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public static AuthorityEnum valueOf(Long code) {
        for (AuthorityEnum authorityEnum : values()) {
            if (code.equals(authorityEnum.getCode())) {
                return authorityEnum;
            }
        }
        return null;
    }
}
