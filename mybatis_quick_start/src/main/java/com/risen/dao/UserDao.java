package com.risen.dao;

import com.risen.pojo.User;

import java.util.List;

public interface UserDao {

    public List<User> selectAll();

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUserById(Integer id);

    /**
     * 多条件组合查询
     * 动态sql  if标签
     * @param user
     * @return
     */
    public List<User> selectByCondition(User user);

    /**
     * 多值查询  foreach标签
     * @param ids
     * @return
     */
    public List<User> selectByIds(int[] ids);
}
