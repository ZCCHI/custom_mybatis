package com.risen.test;

import com.risen.mapper.OrderMapper;
import com.risen.mapper.UserMapper;
import com.risen.pojo.Order;
import com.risen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test01 {

    //一对一的映射关系
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Order> orders = mapper.selectOrderAndUser();

        System.out.println(orders);

    }

    //一对多的映射关系
    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<User> users = mapper.findAll();
        users.forEach((user)->{
            System.out.println(user);
        });
        //System.out.println(users);

    }

    //多对多的映射关系
    @Test
    public void test03() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findUserAndRole();
        users.forEach((user)->{
            System.out.println(user);
        });
        //System.out.println(users);

    }
}
