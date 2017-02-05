package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.example.*")
public class BankdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankdemoApplication.class, args);
	}
}
