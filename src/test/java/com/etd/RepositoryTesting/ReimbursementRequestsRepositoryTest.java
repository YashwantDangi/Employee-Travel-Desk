package com.etd.RepositoryTesting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import com.etd.enumeration.ReimbursementRequestsStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.etd.entity.ReimbursementRequests;
import com.etd.repositories.ReimbursementRequestsRepository;


@DataJpaTest
class ReimbursementRequestsRepositoryTest {

	@Autowired
	private ReimbursementRequestsRepository reimbursementRequestsRepo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindByIdNegative(){
		Optional<ReimbursementRequests> op = reimbursementRequestsRepo.findById(1);
        assertFalse(op.isPresent());
	}

	@Test
	public void testSavePositive(){
		ReimbursementRequests rr = new ReimbursementRequests();
		rr.setId(1);
		rr.setTravelRequestId(1);
		rr.setRequestRaisedByEmployeeId(1);
		rr.setRequestDate(LocalDate.now());
		rr.setInvoiceNo("INNO01");
		rr.setInvoiceDate(LocalDate.of(2024, 2, 22));
		rr.setInvoiceAmount(10000);
		rr.setDocumentURL("xyz");
		rr.setRequestProcessedOn(LocalDate.now());
		rr.setRequestProcessedByEmployeeId(1);
		rr.setStatus(ReimbursementRequestsStatusEnum.valueOf("NEW"));
		rr.setRemarks("");
		reimbursementRequestsRepo.save(rr);
		Optional<ReimbursementRequests> op = reimbursementRequestsRepo.findById(1);
		assertTrue(op.isPresent());
	}

}
