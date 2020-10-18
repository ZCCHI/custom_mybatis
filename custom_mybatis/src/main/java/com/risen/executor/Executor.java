package com.risen.executor;

import com.risen.core.Conflguration;
import com.risen.core.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * 执行器
 */
public interface Executor {

    public <E> List<E> query(Conflguration conflguration, MappedStatement mappedStatement,Object...params) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException;

    public void close()throws Exception;

}
