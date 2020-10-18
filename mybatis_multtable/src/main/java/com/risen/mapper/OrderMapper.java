package com.risen.mapper;

import com.risen.pojo.Order;
import com.risen.pojo.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    //一对多 查询所有用户下订单信息
    //xml方式
    public List<User> findAll();

    //注解方式
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "orderList",column = "id",javaType = List.class,
            many = @Many(select = "com.risen.mapper.OrderMapper.selectOrderByUid"))
    })
    public List<User> findAllByAn();

    @Select("select * from orders where uid = #{uid}")
    public Order selectOrderByUid(Integer uid);

}
