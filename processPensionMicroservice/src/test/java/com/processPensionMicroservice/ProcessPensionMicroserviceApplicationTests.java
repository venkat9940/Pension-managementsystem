package com.processPensionMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.processPensionMicroservice.exception.NotFoundException;
import com.processPensionMicroservice.model.Bank;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;
import com.processPensionMicroservice.service.ProcessPensionService;
import com.processPensionMicroservice.util.DateUtil;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class ProcessPensionMicroserviceApplicationTests {
	@Autowired
	ProcessPensionService processPensionService;

	@Test
	void contextLoads() {
	}

	@Test
	void testBankDetails() {
		EqualsVerifier.simple().forClass(Bank.class).verify();
	}

	@Test
	void testPensionerDeatils() {
		EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	
	@Test
	void testPensionDetails() {
		EqualsVerifier.simple().forClass(PensionDetail.class).verify();
	}

	@Test
	void testPensionerInputDeatils() {
		EqualsVerifier.simple().forClass(PensionerInput.class).verify();
	}
	
	@Test
	void testProcessPensionerInputDeatils() {
		EqualsVerifier.simple().forClass(ProcessPensionInput.class).verify();
	}
	
	@Test
	void testProcessPensionResponseDeatils() {
		EqualsVerifier.simple().forClass(ProcessPensionResponse.class).verify();
	}
	//service testing
	@Test
	public void testCheckDetailsForCorrectPensionerInputForSelfPensionType() throws ParseException {
		PensionerInput input = new PensionerInput("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q",
				123456789001L, "self");
		Bank bank = new Bank("ICICI", 12345678, "private");
		PensionerDetail details = new PensionerDetail("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q", 27000,
				10000, "self", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(10, response.getPensionStatusCode());
	}
	@Test
	public void testCheckDetailsForCorrectPensionerInputForFamilyPensionType() throws ParseException {
		PensionerInput input = new PensionerInput("priyanka", DateUtil.parseDate("27-11-1998"), "PCQAZ1285Q",
				123456789002L, "family");
		Bank bank = new Bank("SBI",12345679,"public");
		PensionerDetail details = new PensionerDetail("priyanka", DateUtil.parseDate("27-11-1998"), "PCQAZ1285Q", 30000,
				12000, "family", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(10, response.getPensionStatusCode());
	}
	
	@Test
	public void testCheckDetailsForIncorrectPensionerInputForSelf() throws ParseException {
		PensionerInput input = new PensionerInput("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q",
				123456789001L, "self");
		Bank bank = new Bank("ICICI", 12345678, "private");
		PensionerDetail details = new PensionerDetail("deekshith", DateUtil.parseDate("29-01-1999"), "PCQAZ1285Q", 27000,
				10000, "self", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(21, response.getPensionStatusCode());
	}
	@Test
	public void testCheckDetailsForIncorrectPensionerInputForFamily() throws ParseException {
		PensionerInput input = new PensionerInput("priyanka", DateUtil.parseDate("27-11-1998"), "PCQAZ1285Q",
				123456789002L, "family");
		Bank bank = new Bank("SBI",12345679,"public");
		PensionerDetail details = new PensionerDetail("priyanka", DateUtil.parseDate("27-11-1998"), "PCASD1234Q", 30000,
				12000, "family", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(21, response.getPensionStatusCode());
	}
	@Test
	public void testGettingPensionDetailByPassingPensionerDetalisForSelfPensionType() throws ParseException {
		Bank bank = new Bank("ICICI", 456678, "public");
		PensionerDetail details = new PensionerDetail("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q", 100000,
				10000, "self", bank);

		PensionDetail actualDetail = processPensionService.getresult(details);

		assertEquals(90000, actualDetail.getPensionAmount());
	}
	@Test
	public void testGettingPensionDetailByPassingPensionerDetalisForFamilyPensionType() throws ParseException {
		Bank bank = new Bank("ICICI", 456678, "public");
		PensionerDetail details = new PensionerDetail("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q", 100000,
				10000, "family", bank);

		PensionDetail actualDetail = processPensionService.getresult(details);

		assertEquals(60000, actualDetail.getPensionAmount());
	}


	
	@Test
	void testMainMethod() throws NumberFormatException, IOException, NotFoundException, ParseException {
		ProcessPensionMicroserviceApplication.main(new String[] {});
	}
	

}