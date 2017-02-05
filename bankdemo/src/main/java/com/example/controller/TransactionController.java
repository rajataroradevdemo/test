package com.example.controller;

import com.example.model.Account;
import com.example.model.Transaction;
import com.example.model.TransactionDTO;
import com.example.model.TransactionDao;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by rajat.arora on 2/3/2017.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestBody TransactionDTO transaction){


//        transactionDao.save(tranc);
        transactionService.createTransaction(transaction);



        return "success";
    }

}
