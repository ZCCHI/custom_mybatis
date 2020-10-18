package com.risen.sqlSession;

import com.risen.core.Conflguration;

public interface SqlSessionFactory {

    public SqlSession openSession(Conflguration conflguration);

    public SqlSession openSession();
}
