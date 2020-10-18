package com.risen.test;

import com.risen.mapper.UserMapper;
import com.risen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试：注解方式
 */
public class Test02 {

    private UserMapper mapper;

    @Before
    public void befor() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true); //开启自动提交
         mapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 测试基础注解的CRUD
     * 新增
     */
    @Test
    public void test01(){
        User user = new User();
        user.setId(4);
        user.setUsername("xiaoming");
        mapper.addUser(user);
    }

    /**
     * 测试基础注解的CRUD
     * 修改
     */
    @Test
    public void test02(){
        User user = new User();
        user.setId(4);
        user.setUsername("xiaoming");
        mapper.updateUser(user);
    }

    /**
     * 测试基础注解的CRUD
     * 查询
     */
    @Test
    public void test03(){
        List<User> users = mapper.selectUser();
        users.forEach((user)->{
            System.out.println(user);
        });
    }
    /**
     * 测试基础注解的CRUD
     * 删除
     */
    @Test
    public void test04(){
        mapper.deleteUser(4);
    }
}
