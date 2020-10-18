package com.risen.core;

/**
 * 每一个mapper文件 抽象成一个MappedStatement对象
 * 包含 sql语句的id、参数类型、返回值类型、具体的sql语句
 */
public class MappedStatement {

    /**
     * sql id
     */
    private String id;

    /**
     * 参数类型
     */
    private Class<?> parmaterType;

    /**
     * 返回值类型
     */
    private Class<?> resultType;

    /**
     * 执行的sql语句
     */
    private String sql;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getParmaterType() {
        return parmaterType;
    }

    public void setParmaterType(Class<?> parmaterType) {
        this.parmaterType = parmaterType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }


}
