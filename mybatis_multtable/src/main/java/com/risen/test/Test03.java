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

/**
 * 测试注解方式
 * 多关系映射
 */
public class Test03 {

     //one to one 注解方式
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true); //开启自动提交
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Order> orders = mapper.selectOrderAndUserByAn();
        orders.forEach((order)->{
            System.out.println(order);
        });
    }

    //one to mutl
    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true); //开启自动提交
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<User> users = mapper.findAllByAn();
        users.forEach((user)->{
            System.out.println(user);
        });
    }

}
