package org.skcorg.webservices.client;

import org.skcorg.webservices.schemas.jaxb.Account;
import org.skcorg.webservices.schemas.jaxb.AccountDetailsRequest;
import org.skcorg.webservices.schemas.jaxb.AccountDetailsResponse;
import org.skcorg.webservices.schemas.jaxb.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class AccountService {
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	public Account getAccountsDetails(String accountNumber) {
		AccountDetailsRequest accountDetailsRequest = new ObjectFactory()
				.createAccountDetailsRequest();
		accountDetailsRequest.setAccountNumber(accountNumber);

		AccountDetailsResponse response = (AccountDetailsResponse) webServiceTemplate
				.marshalSendAndReceive(accountDetailsRequest);

		return response.getAccountDetails();
	}
}
