package com.risen.io;

import java.io.InputStream;

/**
 * 加载配置类
 *  到InputStream流 到内存中
 */
public class Resources {

    public static InputStream getResourceAsStream(String path){
        //使用类加载器加载配置文件到内存中
        ClassLoader classLoader = Resources.class.getClassLoader();
        InputStream resource = classLoader.getResourceAsStream(path);
        return resource;
    }
}
