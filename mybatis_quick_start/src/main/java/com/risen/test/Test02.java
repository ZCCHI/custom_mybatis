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

public class Test02 {

    /**
     * 动态sql  if
     * 多条件组合查询
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        //加载核心配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession(); //true开启自动事务提交 默认不开启

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User(null,"lalal");

        List<User> users = userDao.selectByCondition(user);

        System.out.println(users);
        sqlSession.close();
    }

    /**
     * 动态sql  foreach
     * 多值查询
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        //加载核心配置文件
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession(); //true开启自动事务提交 默认不开启

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int[] ids = {1,2};
        List<User> users = userDao.selectByIds(ids);

        System.out.println(users);
        sqlSession.close();
    }

}
