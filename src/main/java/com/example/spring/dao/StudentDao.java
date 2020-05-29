package com.example.spring.dao;


import com.example.spring.po.Query;
import com.example.spring.po.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 1.JDK8参数可以不写@Param直接取到参数名
// 2.单个传list的话必须用list取
// 3.多层的话才用list的变量名
// 4.总结就是 取传过来的的内部的第一层
@Repository
public interface StudentDao {

    long insert(Student student);

    int update(Student student);

    List<Student> selectByNameAndAge(String name, Integer age);

    List<Student> selectByNames(List<String> names, Integer age);

    List<Student> selectByQuery(Query query, int age);

    void insertBatch(@Param("students") List<Student> students);

}
