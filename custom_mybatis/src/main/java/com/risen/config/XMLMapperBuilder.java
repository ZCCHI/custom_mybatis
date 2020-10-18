package com.risen.config;

import com.risen.core.Conflguration;
import com.risen.core.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * mapper.xml 解析器
 */
public class XMLMapperBuilder {
    private Conflguration conflguration;

    public XMLMapperBuilder(Conflguration conflguration){
        this.conflguration = conflguration;
    }

    /**
     * 解析mapper.xml
     * @param inputStream
     */
    public void parseMapper(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        //获取mapper文件的命名空间
        String namespace = rootElement.attributeValue("namespace");

        List<Element> selectTaglist = rootElement.selectNodes("select");
        for (Element element : selectTaglist) {
            String id = element.attributeValue("id");
            //返回值类型
            String resultType = element.attributeValue("resultType");
            //参数类型
            String paramterType = element.attributeValue("paramterType");
            //sql语句
            String sqlText = element.getTextTrim();


            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParmaterType(getClassType(paramterType));
            mappedStatement.setResultType(getClassType(resultType));
            mappedStatement.setSql(sqlText);

            String key = namespace +"."+ id;
            conflguration.getMappedStatementMap().put(key,mappedStatement);
        }

    }


    private Class<?> getClassType(String params){
        try {
            if(params == null){
                return null;
            }
            Class<?> aClass = Class.forName(params);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("mapper.xml配置参数未找到");
        }
    }

}
