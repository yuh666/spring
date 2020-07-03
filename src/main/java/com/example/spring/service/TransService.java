package com.example.spring.service;

import com.example.spring.dao.StudentDao;
import com.example.spring.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransService {

    @Autowired
    private StudentDao dao;

    @Transactional
    public void trans(){
        dao.insert(new Student());
    }
}
