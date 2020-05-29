package com.example.spring.mybatis;


import com.example.spring.dao.StudentDao;
import com.example.spring.po.Query;
import com.example.spring.po.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void testInsert() {
        for (int i = 4; i < 100; i++) {
            Student student = new Student();
            student.setAge(10);
            student.setName("name" + i);
            student.setBirthday(new Date());
            student.setBalance(10000L);
            student.setWeight(BigDecimal.TEN);
            studentDao.insert(student);
            System.out.println(student.getId());
        }
    }

    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setAge(100);
        student.setName("name1");
        student.setBirthday(new Date());
        student.setBalance(10L);
        student.setWeight(BigDecimal.ZERO);
        int update = studentDao.update(student);
        System.out.println(update);
    }

    @Test
    public void testSelect1() {
        List<Student> list = studentDao.selectByNameAndAge("name1", 100);
        System.out.println(list);
    }

    @Test
    public void testSelect2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("name1");
        list.add("name2");
        list.add("name3");
        List<Student> students = studentDao.selectByNames(list, 10);
        System.out.println(students);
    }

    @Test
    public void testSelect3() {
        Query query = new Query();
        query.setName("name1");
        List<Student> students = studentDao.selectByQuery(query, 10);
        System.out.println(students);
    }

    @Test
    public void testString() {
        String a = "abc";
        String b = "ab" + "c";
        System.out.println(a == b);
    }

}
