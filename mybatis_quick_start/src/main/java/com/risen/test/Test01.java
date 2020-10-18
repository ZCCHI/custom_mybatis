package com.risen.test;

import com.risen.dao.UserDao;
import com.risen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 使用Mybatis
 */
public class Test01 {

    /**
     * 原生Mybatis方式 不使用接口的方式
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        //加载核心配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获取sqlSessonFactory 根据核心配置文件构建工厂类  构建者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //根据工厂获取SqlSession实例  工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession(); //true开启自动事务提交 默认不开启
        //不使用接口的方式  在UserMapper.xml中的namespace为user
        List<User> users = sqlSession.selectList("user.selectAll");
        System.out.println(users);

    }


     //Mybatis方式  使用接口的方式
    @Test
    public void test02() throws IOException {
        //加载核心配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获取sqlSessonFactory 根据核心配置文件构建工厂类  构建者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //根据工厂获取SqlSession实例  工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession(); //true开启自动事务提交 默认不开启
        //接口的方式  在UserMapper.xml中的namespace为 接口的全限定名称
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.selectAll();
        System.out.println(users);

    }

    //增加
    @Test
    public void test03() throws IOException {
        //加载核心配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获取sqlSessonFactory 根据核心配置文件构建工厂类  构建者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        //根据工厂获取SqlSession实例  工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession(); //true开启自动事务提交 默认不开启
        //接口的方式  在UserMapper.xml中的namespace为 接口的全限定名称
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User(4,"lalal");
        int count = userDao.insertUser(user);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

}
