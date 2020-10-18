package com.risen.sqlSession;

import com.risen.core.Conflguration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Conflguration conflguration;

    public DefaultSqlSessionFactory(Conflguration conflguration){
        this.conflguration = conflguration;
    }

    @Override
    public SqlSession openSession(Conflguration conflguration) {
        return new DefaultSqlSessoin(conflguration);
    }

    @Override
    public SqlSession openSession() {
        return this.openSession(conflguration);
    }
}
