package com.risen.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.risen.core.Conflguration;
import com.risen.io.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 解析 sqlMapConfig.xml 解析器
 *  封装到Configuration类中
 */
public class XMLConfigerBuilder {

    private Conflguration conflguration;

    public XMLConfigerBuilder(Conflguration conflguration){
        this.conflguration = conflguration;
    }

    public Conflguration  parseConfiguration(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //利用dom4j解析
        Document document = new SAXReader().read(inputStream);
        //获取根节点
        Element rootElement = document.getRootElement();

        Properties properties = new Properties();
        //解析sqlMapConfig.xml
        //获取所有的property标签  获取数据源信息
        List<Element> list = rootElement.selectNodes("//property");
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        //根据数据源信息构建连接池的数据源
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        //将数据源配置到Configuration
        conflguration.setDataSource(comboPooledDataSource);

        //解析mapper.xml
        List<Element> mapperTagList = rootElement.selectNodes("//mapper");

        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(conflguration);

        for (Element element : mapperTagList) {
            //获取mapper文件路径
            String mapperPath = element.attributeValue("resource");
            //加载mapper配置文件到内存流
            InputStream mapperInputStream = Resources.getResourceAsStream(mapperPath);
            xmlMapperBuilder.parseMapper(mapperInputStream);
        }

        return conflguration;
    }

}