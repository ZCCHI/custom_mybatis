package com.risen.sqlSession;

import java.util.List;

public interface SqlSession {

        public <E> List<E> selectList(String statementId, Object...params)throws Exception;

        public <T> T selectOne(String statementId,Object...params) throws Exception;

        /**
         * 关闭连接
         * @throws Exception
         */
        public void close()throws Exception;

        /**
         * 获取代理对象
         * @param <T>
         * @return
         */
        public <T> T getMapper(Class<?> Calzz);
}
