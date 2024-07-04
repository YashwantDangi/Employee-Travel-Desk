package com.etd.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.etd.dto.ProcessReimbursementDTO;
import com.etd.dto.ReimbursementRequestsDTO_Request;
import com.etd.entity.ReimbursementRequests;
import com.etd.enumeration.ReimbursementRequestsStatusEnum;
import com.etd.service.ReimbursementRequestsService;
import com.etd.utilities.ReimbursementRequestsMappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etd.dto.ReimbursementRequestsDTO_Response;
import com.etd.repositories.ReimbursementRequestsRepository;

@Service
public class ReimbursementRequestsServiceImpl implements ReimbursementRequestsService {

	@Autowired
	private ReimbursementRequestsRepository reimbursementRequestsRepo;

	@Autowired
	private ReimbursementRequestsMappingUtility requestUtility;

	@Override
	public List<ReimbursementRequestsDTO_Response> getAllReimbursementsByRequestId(int travelRequestId) {
		List<ReimbursementRequests> lrr = reimbursementRequestsRepo.findByTravelRequestId(travelRequestId);
		List<ReimbursementRequestsDTO_Response> lrrDTO = new ArrayList<>();
		for(ReimbursementRequests rr : lrr){
			ReimbursementRequestsDTO_Response rrDTO = requestUtility.toReimbursementRequestsDTO_Repsonse(rr);
			lrrDTO.add(rrDTO);
		}
		return lrrDTO;
	}

	@Override
	public String reimbursementProcess(int reimbursementId, ProcessReimbursementDTO prDTO) {
		ReimbursementRequestsDTO_Response rrDTO = getReimbursementById(reimbursementId);
			if (rrDTO.getStatus().equals(ReimbursementRequestsStatusEnum.NEW)) {
				rrDTO.setStatus(ReimbursementRequestsStatusEnum.valueOf(prDTO.getStatus()));
				rrDTO.setRemarks(prDTO.getRemarks());
				rrDTO.setRequestProcessedOn(LocalDate.now());
				rrDTO.setRequestProcessedByEmployeeId(prDTO.getRequestProcessedByEmployeeId());
				ReimbursementRequests rr = requestUtility.toReimbursementRequests_from_Response(rrDTO);
				reimbursementRequestsRepo.save(rr);
		}
		return String.valueOf(rrDTO.getStatus());
	}

	@Override
	public ReimbursementRequestsDTO_Response getReimbursementById(int reimbursementId) {
		Optional<ReimbursementRequests> rr = reimbursementRequestsRepo.findById(reimbursementId);
		ReimbursementRequests rrObj = new ReimbursementRequests();
		if(rr.isPresent()){
			ReimbursementRequests obj = rr.get();
			rrObj.setId(obj.getId());
			rrObj.setTravelRequestId(obj.getTravelRequestId());
			rrObj.setRequestRaisedByEmployeeId(obj.getRequestRaisedByEmployeeId());
			rrObj.setRequestDate(obj.getRequestDate());
			rrObj.setReimbursementTypes(obj.getReimbursementTypes());
			rrObj.setInvoiceNo(obj.getInvoiceNo());
			rrObj.setInvoiceDate(obj.getInvoiceDate());
			rrObj.setInvoiceAmount(obj.getInvoiceAmount());
			rrObj.setDocumentURL(obj.getDocumentURL());
			rrObj.setRequestProcessedOn(obj.getRequestProcessedOn());
			rrObj.setRequestProcessedByEmployeeId(obj.getRequestProcessedByEmployeeId());
			rrObj.setStatus(obj.getStatus());
			rrObj.setRemarks(obj.getRemarks());
		}
        return requestUtility.toReimbursementRequestsDTO_Repsonse(rrObj);
	}

	@Override
	public String newReimbursement(ReimbursementRequestsDTO_Request reimbursementRequestsDTO_request) {
		if(reimbursementRequestsDTO_request != null){
			ReimbursementRequests rr = requestUtility.toReimbursementRequests_from_Request(reimbursementRequestsDTO_request);
			System.out.println("rr:"+rr);
			reimbursementRequestsRepo.save(rr);
			return "success";
		}
		return "fail";
	}

}
