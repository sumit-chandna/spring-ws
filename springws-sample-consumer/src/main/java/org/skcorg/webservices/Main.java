package org.skcorg.webservices;

import org.skcorg.webservices.client.AccountService;
import org.skcorg.webservices.schemas.jaxb.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		AccountService accountService = context.getBean(AccountService.class);

		Account account = accountService.getAccountsDetails("123");
		if (account != null) {
			System.out.println(account.getAccountName());
		}

	}
}