package com.example.service;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by rajat.arora on 2/3/2017.
 */
@Service
@Transactional
public class TransactionService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    @Transactional(
            propagation = Propagation.REQUIRED
    )
    public boolean createTransaction(TransactionDTO transactionDto){

        //save transaction

        Transaction transaction = createModelFromDto(transactionDto);
        transactionDao.save(transaction);

        if(transaction.getOperation().equalsIgnoreCase("WDL") || transaction.getOperation().equalsIgnoreCase("DEP")){
            try{
                //update source account
                if(!transaction.getSourceAccount().equals(transaction.getDestinationAccount())){
                    throw new RuntimeException("Source and Destination Account Must be same for WDL and DEP Transation.");
                }
                updateAccount(transaction.getSourceAccount().getAccount_id(), transaction.getAmount());
            }catch (Exception ex){
                //update transaction for failure
                transaction.setStatus("ERROR");
                transactionDao.save(transaction);
                throw new RuntimeException("Unable to persist transaction");
            }
        }else if(transaction.getOperation().equalsIgnoreCase("TRF")){
            try{
                updateAccount(transaction.getSourceAccount().getAccount_id(), -transaction.getAmount());

                Transaction doubleEntry = createModelFromDto(transactionDto);
                Account tempAccount = doubleEntry.getSourceAccount();
                doubleEntry.setSourceAccount(doubleEntry.getDestinationAccount());
                doubleEntry.setDestinationAccount(tempAccount);
                doubleEntry.setAmount(-doubleEntry.getAmount());
                transactionDao.save(doubleEntry);
                updateAccount(transaction.getDestinationAccount().getAccount_id(), transaction.getAmount());

                //THIS BLOCK WILL BE PART OF SINGLE TRANSACTION
                //update source with DB
                //Create transaction for Dest
                //Update Destination with cr
            }catch (Exception ex){
                //ROLLBACK ALL
                //UPDATE SOURCE TRANSACTION FOR FAILURE
            }
        }
        return true;
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW
    )
    private void updateAccount(Long Id, Long amount) {
       //find account by id
        Account account = accountDao.getOne(Id);
            account.setAccountBalance(account.getAccountBalance() + amount );
        //save account details
        accountDao.save(account);
        System.out.println(amount);
        if(amount.equals(1111L)){
            System.out.println("exceptoon thrown");
            throw new RuntimeException("Unable to persist user record");
        }

    }

    private Transaction createModelFromDto(TransactionDTO transactionDTO){
        Transaction tranc = new Transaction();
        tranc.setDescription(transactionDTO.getDescription());
        tranc.setOperation(transactionDTO.getOperation());
        if(transactionDTO.getOperation().equalsIgnoreCase("WDL") || transactionDTO.getOperation().equalsIgnoreCase("TRF")){
            tranc.setAmount(-transactionDTO.getAmount());
        }else{
            tranc.setAmount(transactionDTO.getAmount());
        }
        tranc.setSourceAccount(new Account(transactionDTO.getSourceAccountId()));
        tranc.setDestinationAccount(new Account(transactionDTO.getDestinationAccountId()));
        tranc.setTransactionTime(new Date(Calendar.getInstance().getTimeInMillis()));
        tranc.setStatus("SUCCESS");
        return tranc;

    }


}
