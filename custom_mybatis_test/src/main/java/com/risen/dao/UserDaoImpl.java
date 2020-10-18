package com.risen.dao;

import com.risen.io.Resources;
import com.risen.pojo.User;
import com.risen.sqlSession.SqlSession;
import com.risen.sqlSession.SqlSessionFactory;
import com.risen.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UserDaoImpl implements UserDao{


    public List<User> selectList() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = builder.openSession();
        List<User> selectList = sqlSession.selectList("user.selectList",null);
        sqlSession.close();
        return selectList;
    }

    public User selectOneByCondtion(User user) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory builder =new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = builder.openSession();
        User user1 = sqlSession.selectOne("user.selectOne", user);
        sqlSession.close();
        return user1;
    }
}
