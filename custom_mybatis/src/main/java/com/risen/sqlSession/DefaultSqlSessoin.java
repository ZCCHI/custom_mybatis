package com.risen.sqlSession;

import com.risen.core.Conflguration;
import com.risen.core.MappedStatement;
import com.risen.executor.Executor;
import com.risen.executor.SimpleExecutor;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSessoin implements SqlSession{

    private Conflguration conflguration;

    //执行器
    private Executor simpleExcutor = new SimpleExecutor();


    public DefaultSqlSessoin(Conflguration conflguration){
        this.conflguration =conflguration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = conflguration.getMappedStatementMap().get(statementId);
        List<E> query = simpleExcutor.query(conflguration, mappedStatement, params);
        return query;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> list = selectList(statementId, params);
        if(list.size() == 1){
            return (T)list.get(0);
        }else {
            throw new RuntimeException("返回结果多条");
        }
    }

    public void close() throws Exception {
        simpleExcutor.close();
    }

    //获取代理类 JDK动态代理
    @Override
    public <T> T getMapper(Class<?> Calzz) {

        Object proxyInstance = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Calzz}, new InvocationHandler() {
            // 根据不同情况，来调用selctList或者selectOne
            //  statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               //获取方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();

                //组装 statementId  接口的全限定名 + 方法名
                //方法名
                String methodName = method.getName();
                //接口的全限定名
                String className = method.getDeclaringClass().getName();

                //判断返回值类型是否加了泛型 暂且加了泛型的认为返回集合
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(className + "." + methodName, args);
                    return objects;
                }
                Object one = selectOne(className + "." + methodName, args);
                return one;
            }
        });
        return (T) proxyInstance;
    }


}
