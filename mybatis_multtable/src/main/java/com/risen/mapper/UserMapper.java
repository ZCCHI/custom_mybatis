package com.risen.mapper;

import com.risen.pojo.Order;
import com.risen.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    //一对一的映射关系 one to one   查询所有的订单 及订单所属于的用户
    //xml方式
    public List<Order> selectOrderAndUser();

    //注解方式
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "ordertime",column = "ordertime"),
            @Result(property = "total",column = "total"),
            @Result(property = "user",column = "uid",javaType = User.class,
                    one=@One(select="com.risen.mapper.UserMapper.selectUserById"))
    })
    @Select("select * from orders")
    public List<Order> selectOrderAndUserByAn();

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    public User selectUserById(Integer id);





    //多对多  查询所有用户 及用户下所有的角色
    //xml方式
    public List<User> findUserAndRole();







    /**
     * 四种基础注解 CRUD
     * @param user
     */
    @Insert("insert into user values(#{id},#{username})")
    public void addUser(User user);

    @Update("update user set username = #{username} where id = #{id}")
    public int updateUser(User user);

    @Select("select * from user")
    public List<User> selectUser();

    @Delete("delete from user where id = #{id}")
    public int deleteUser(Integer id);
}
