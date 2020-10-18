package com.risen.test;


import com.risen.dao.UserDao;
import com.risen.dao.UserDaoImpl;
import com.risen.io.Resources;
import com.risen.pojo.User;
import com.risen.sqlSession.SqlSession;
import com.risen.sqlSession.SqlSessionFactory;
import com.risen.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestCustomMybatis {

    //无参查询
    @Test
    public void test01() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory builder =new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = builder.openSession();
        List<User> selectList = sqlSession.selectList("user.selectList", null);
        sqlSession.close();
        System.out.println(selectList);
    }
    //有参查询
    @Test
    public void test02() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory builder =new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = builder.openSession();
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("zhangssan");
        User user = sqlSession.selectOne("user.selectOne", user1);
        sqlSession.close();
        System.out.println(user);
    }
    //用实现了类查询
    @Test
    public void test03()throws Exception{
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.selectList();
        System.out.println(users);
    }

    //使用借口代理类
    @Test
    public void test04()throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory builder =new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = builder.openSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> users = mapper.selectList();
        System.out.println(users);
    }

}
