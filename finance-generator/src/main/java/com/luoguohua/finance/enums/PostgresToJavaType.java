package com.luoguohua.finance.enums;


import java.sql.Types;


/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/10 19:02
 * Content:
 */
public enum PostgresToJavaType {

    /**
     * postgre->java
     */
    VARCHAR(Types.VARCHAR, "String"),
    CHAR(Types.CHAR, "String"),
    TEXT(Types.LONGVARCHAR, "String"),
    BOOLEAN(Types.BOOLEAN, "Boolean"),
    BYTEA(Types.BINARY, "byte[]"),
    SMALLINT(Types.SMALLINT, "Short"),
    INTEGER(Types.INTEGER, "Integer"),
    BIGINT(Types.BIGINT, "Long"),
    REAL(Types.REAL, "Float"),
    DOUBLE(Types.DOUBLE, "Double"),
    NUMERIC(Types.NUMERIC, "BigDecimal"),
    DATE(Types.DATE, "LocalDateTime"),
    TIME(Types.TIME, "LocalDateTime"),
    TIMESTAMP(Types.TIMESTAMP, "LocalDateTime"),
    JSONB(Types.OTHER, "String");

    private final int sqlType;
    private final String javaType;

    PostgresToJavaType(int sqlType, String javaType) {
        this.sqlType = sqlType;
        this.javaType = javaType;
    }

    public static String getJavaType(int sqlType) {
        for (PostgresToJavaType mapping : PostgresToJavaType.values()) {
            if (mapping.sqlType == sqlType) {
                return mapping.javaType;
            }
        }
        return "Object";
    }
}
