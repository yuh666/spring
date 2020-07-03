package com.example.spring.mybatis;


import com.example.spring.common.A;
import com.example.spring.dao.mybatis.StudentDao;
import com.example.spring.po.Query;
import com.example.spring.po.Student;
import com.example.spring.service.TransService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private A a;

    @Autowired
    private TransService transService;


    @Test
    public void testProxy() {
        System.out.println(Arrays.toString(studentDao.getClass().getAnnotations()));
    }

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
    public void testInsertBatch() {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 100; i < 200; i++) {
            Student student = new Student();
            student.setAge(10);
            student.setName("name" + i);
            student.setBirthday(new Date());
            student.setBalance(10000L);
            student.setWeight(BigDecimal.TEN);
            students.add(student);
        }
        studentDao.insertBatch(students);
    }

    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setName("name1");
        student.setAge(100000);
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
        List<Student> students = studentDao.selectByNames(list, 100);
        System.out.println(students);
    }

    @Test
    public void testSelect3() {
        Query query = new Query();
        query.setName("name2");
        List<Student> students = studentDao.selectByQuery(query, 10);
        System.out.println(students);
    }

    @Test
    public void testSelect4() {
        Query query = new Query();
        query.setName("name2");
        List<Student> students = studentDao.selectByQueryAnno(query, 10, 1);
        System.out.println(students);
    }

    @Test
    public void testSelectAll() {
        Page<Student> page = PageHelper.startPage(1, 10, true);
        List<Student> students = studentDao.selectAll();
        System.out.println(page);
    }

    @Test
    public void testString() {
        String a = "abc";
        String b = "ab" + "c";
        System.out.println(a == b);
    }


    @Test
    public void testAspect() {
        a.abc();
    }

    @Test
    public void testTrans(){
        transService.trans();
    }

}
