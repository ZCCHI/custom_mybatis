package com.risen.core;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义mybatis  上下文配置信息
 */
public class Conflguration {

    //数据源信息
    private DataSource dataSource;

    //map集合 key: statementId=名称空间.id   Value: MappedStatement
    private Map<String, MappedStatement> mappedStatementMap = new ConcurrentHashMap<String, MappedStatement>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
