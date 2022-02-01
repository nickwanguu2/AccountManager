package com.revature.AccountManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountManagerApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(AccountManagerApplication.class, args);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
