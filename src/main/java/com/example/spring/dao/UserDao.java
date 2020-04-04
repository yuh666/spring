package com.example.spring.dao;

import com.example.spring.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from user where id = #{id}")
    @Result(column = "total_balance", property = "totalBalance")
    User get(@Param("id") long id);
}
