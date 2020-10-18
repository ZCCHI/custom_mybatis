package com.risen.executor;

import com.risen.config.BoundSql;
import com.risen.core.Conflguration;
import com.risen.core.MappedStatement;
import com.risen.utils.GenericTokenParser;
import com.risen.utils.ParameterMapping;
import com.risen.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * sql 执行器 封装装了JDBC
 */
public class SimpleExecutor implements Executor{

    private Connection connection;

    @Override
    public <E> List<E> query(Conflguration conflguration, MappedStatement mappedStatement, Object... params) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {

        //获取连接
        connection  = conflguration.getDataSource().getConnection();
        //获取到原生sql语句
        String sql = mappedStatement.getSql();

        /**
         * 处理sql语句 #{xxx} 替换成 ?  并统计xxx放入到 ParamMappings集合中
         */
        BoundSql boundSql = getBoundSql(sql);

        //获取已经处理好的sql语句 如select * from tb_user where id = ? and username = ?
        String finalSql = boundSql.getSqlText();

        //获取参数类型
        Class<?> parmaterType = mappedStatement.getParmaterType();

        //获取sql执行器
        PreparedStatement preparedStatement = connection.prepareStatement(finalSql);

        //获取sql名称参数
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for(int i=0;i<parameterMappings.size();i++){
            //获取每一个参数
            ParameterMapping parameterMapping = parameterMappings.get(i);
            //获取到属性的名称
            String content = parameterMapping.getContent();

            //利用反射获取到 属性对象
            Field declaredField = parmaterType.getDeclaredField(content);
            //开启暴力访问
            declaredField.setAccessible(true);

            //获得参数的值 根据属性对象获取 到属性的对应得值
            Object o = declaredField.get(params[0]);

            //给占位符赋值
            preparedStatement.setObject(i+1,o);
        }

        //执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<E> results = new ArrayList<>();
        //获取返回值类型
        Class<?> resultType = mappedStatement.getResultType();

        while (resultSet.next()){
            ResultSetMetaData metaData = resultSet.getMetaData();

            //获取返回值的参数实例
            E e = (E)resultType.newInstance();

            int columnCount = metaData.getColumnCount();
            for (int i=1;i<=columnCount;i++){
                //列名
                String columnName = metaData.getColumnName(i);
                //值
                Object value = resultSet.getObject(columnName);

                //根据属性名和Class对象创建属性描述器 利用反射或者内省  获取获取属性的读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultType);

                //获取实例的写方法
                Method writeMethod = propertyDescriptor.getWriteMethod();
                //利用set写入值到e实例中
                writeMethod.invoke(e, value);
            }
            results.add(e);
        }
        return results;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * 解析sql语句
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql){

        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}",parameterMappingTokenHandler );
        //解析sql语句 将#{} 换成?  并将#{xx} 的xx取出放入到paramterMappings集合中
        String parse = genericTokenParser.parse(sql);

        //获取该sql语句对应得sql参数名称的集合
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        //将解析好的sql放入到和参数名称封装到BoundSql
        BoundSql boundSql = new BoundSql(parse, parameterMappings);

        return boundSql;
    }


}
