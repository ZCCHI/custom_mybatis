package com.risen.sqlSession;

import com.risen.config.XMLConfigerBuilder;
import com.risen.core.Conflguration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.Connection;

public class SqlSessionFactoryBuilder {

    private Conflguration conflguration;


    public SqlSessionFactoryBuilder(){
        this.conflguration = new Conflguration();
    }


    public  SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        //1、利用dom4j解析sqlMapConfig.xml 和 mapper.xml 配置到Configuration
        XMLConfigerBuilder xmlConfigerBuilder = new XMLConfigerBuilder(conflguration);
        Conflguration conflguration = xmlConfigerBuilder.parseConfiguration(inputStream);

        //2、创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(conflguration);

        return sqlSessionFactory;
    }





}
