package com.pensionerDetailsMicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pensionerDetailsMicroservice.Controller.PensionerDetailsController;
import com.pensionerDetailsMicroservice.Exception.NotFoundException;
import com.pensionerDetailsMicroservice.Model.Bank;
import com.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pensionerDetailsMicroservice.Service.PensionerdetailService;
import com.pensionerDetailsMicroservice.Util.DateUtil;

import nl.jqno.equalsverifier.EqualsVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
class PensionerDetailsMicroserviceApplicationTests {
	
	@Autowired
	private PensionerdetailService pds;
	@Autowired
	private PensionerDetailsController controller;
	////////
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNotNullPensionDetailServiceObject() {
		assertNotNull(pds);
	}

	@Test
	public void testCorrectDetailsReturnedFromServiceWithCorrectAadharNumber() throws IOException, NotFoundException,
			NumberFormatException, com.pensionerDetailsMicroservice.Exception.NotFoundException, ParseException {

		PensionerDetail pensionerDetail = new PensionerDetail("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q",
				27000, 10000, "self", new Bank("ICICI", 12345678, "private"));
		assertEquals(pds.getPensionerDetailByAadhaarNumber(123456789001L), pensionerDetail);
	}

	
	 

	/////////
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
	void testMainMethod() throws NumberFormatException, IOException, NotFoundException, ParseException {
		PensionerDetailsMicroserviceApplication.main(new String [] {});
	}
	//////////////////////////


	
	@Test
	public void testToGetCorrectPenionerDetailsFromController() throws Exception {
		PensionerDetail pensionerDetail = new PensionerDetail("deekshith", DateUtil.parseDate("29-01-1999"), "PCASD1234Q",
				27000, 10000, "self", new Bank("ICICI", 12345678, "private"));
		//when(pds.getPensionerDetailByAadhaarNumber(123456789001L)).thenReturn(pensionerDetail);
		PensionerDetail actual = controller.getPensionerDetailByAadhaar(123456789001L);
		assertNotNull(actual);
		assertEquals(actual, pensionerDetail);

	}

	@Test
	public void testForAadharNumberNotInCsvFile() {

		PensionerDetail actual = controller.getPensionerDetailByAadhaar(12345678888L);
		assertNull(actual);
	}


}
