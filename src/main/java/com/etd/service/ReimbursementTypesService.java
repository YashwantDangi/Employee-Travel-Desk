package com.etd.service;

import java.util.List;

import com.etd.dto.ReimbursementTypesDTO;

public interface ReimbursementTypesService {

	/**
	 * Get All Reimbursement Types
	 * This end points will provide the available reimbursement types in the system
	 * @return
	 */
	List<ReimbursementTypesDTO> getAllReimbursementTypes();

}
