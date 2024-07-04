package com.main;

import com.etd.ReimbursementModuleApplication;
import com.etd.controller.ReimbursementRequestsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ReimbursementModuleApplication.class})
class ReimbursementModuleApplicationTests {

	@Autowired
	private ReimbursementRequestsController reimbursementRequestsController;
	@Test
	void contextLoads() {
		assertNotNull(reimbursementRequestsController);
	}

}
