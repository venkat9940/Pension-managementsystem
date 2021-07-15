package com.pensionerDisbursmentMicroservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.controller.PensionDisbursementController;

import nl.jqno.equalsverifier.EqualsVerifier;


class PensionerDisbursmentMicroserviceApplicationTest {
	

	@Test
	void Banktest() {
		
			EqualsVerifier.simple().forClass(Bank.class).verify();
	}
	@Test
	void PensionerDetailtest() {
		
			EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	@Test
	void ProcessPensionInputTest() {
		
			EqualsVerifier.simple().forClass(ProcessPensionInput.class).verify();
	}
	@Test
	void ProcessPensionResponseTest() {
		
			EqualsVerifier.simple().forClass(ProcessPensionResponse.class).verify();
	}

}