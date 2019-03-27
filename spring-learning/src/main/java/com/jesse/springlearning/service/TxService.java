package com.jesse.springlearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("AlibabaTransactionMustHaveRollback")
@Service
public class TxService {

    //@Autowired
    //private JdbcTemplate jdbcTemplate;

    @Transactional
    @Autowired
    public void insertUser(){
        //jdbcTemplate.execute("insert into users(name ,age) values ('f',18)");
    }
}
