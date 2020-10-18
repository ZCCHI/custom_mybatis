package com.risen.dao;

import com.risen.pojo.User;

import java.util.List;

public interface UserDao {

    public List<User> selectList() throws Exception;

    public User selectOneByCondtion(User user) throws Exception;
}
