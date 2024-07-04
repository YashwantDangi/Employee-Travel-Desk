package com.etd.service;

import java.util.List;

import com.etd.dto.ProcessReimbursementDTO;
import com.etd.dto.ReimbursementRequestsDTO_Request;
import com.etd.dto.ReimbursementRequestsDTO_Response;

public interface ReimbursementRequestsService {

	/**
	 * Get All Reimbursements By Request Id
	 * This endpoint will return details of all the reimbursement requests raised for a given travel request id
	 * @param travelRequestId
	 * @return
	 */
	List<ReimbursementRequestsDTO_Response> getAllReimbursementsByRequestId(int travelRequestId);

	/**
	 * Reimbursement Process
	 * Using this endpoint the travel desk executive will be able to accept or reject a given reimbursement
	 * @param reimbursementId
	 * @param processReimbursementDTO
	 * @return
	 */
	String reimbursementProcess(int reimbursementId, ProcessReimbursementDTO processReimbursementDTO);

	/**
	 * Get Reimbursement By Id
	 * Travel desk executives can use this endpoint to view detail of a single reimbursement request
	 * @param reimbursementId
	 * @return
	 */
	ReimbursementRequestsDTO_Response getReimbursementById(int reimbursementId);

	/**
	 * Add new Reimbursement Requests
	 * Using this end-point employees will be able to add request for a reimbursement
	 * @param reimbursementRequestsDTO_request
	 * @return
	 */
	String newReimbursement(ReimbursementRequestsDTO_Request reimbursementRequestsDTO_request);
}
