package com.example.controller;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

/**
 * Created by rajat.arora on 2/2/2017.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    Page<Customer> getCustomers(Pageable pageable){
        return (Page<Customer>) customerDao.findAll(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Customer customer) {
        String customerId = "";
        try {
                    for(Account acc:customer.getAccounts()){

                        acc.setCustomer(customer);
                    }

            customerDao.save(customer);
            customerId = String.valueOf(customer.getCustomer_id());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "Customer succesfully created with id = " + customerId;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        System.out.println("Inside delete");
        try {
            Customer customer = new Customer(id);
            customerDao.delete(customer);
        }
        catch (Exception ex) {
            return "Error deleting the customer:" + ex.toString();
        }
        return "Customer succesfully deleted!";
    }


}
