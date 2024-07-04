package com.etd.utilities;

import com.etd.dto.ReimbursementRequestsDTO_Request;
import com.etd.dto.ReimbursementRequestsDTO_Response;
import com.etd.entity.ReimbursementRequests;
import com.etd.entity.ReimbursementTypes;
import com.etd.repositories.ReimbursementTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReimbursementRequestsMappingUtility {
    @Autowired
    private ReimbursementTypesRepository reimbursementTypesRepository;

    public ReimbursementRequestsDTO_Response toReimbursementRequestsDTO_Repsonse(ReimbursementRequests reimbursementRequests){
        return new ReimbursementRequestsDTO_Response(
                reimbursementRequests.getId(),
                reimbursementRequests.getTravelRequestId(),
                reimbursementRequests.getRequestRaisedByEmployeeId(),
                reimbursementRequests.getRequestDate(),
                reimbursementRequests.getReimbursementTypes(),
                reimbursementRequests.getInvoiceNo(),
                reimbursementRequests.getInvoiceDate(),
                reimbursementRequests.getInvoiceAmount(),
                reimbursementRequests.getDocumentURL(),
                reimbursementRequests.getRequestProcessedOn(),
                reimbursementRequests.getRequestProcessedByEmployeeId(),
                reimbursementRequests.getStatus(),
                reimbursementRequests.getRemarks()
        );
    }
    public ReimbursementRequests toReimbursementRequests_from_Request(ReimbursementRequestsDTO_Request reimbursementRequestsDTO_request){
        ReimbursementRequests reimbursementRequests = new ReimbursementRequests();

//        Set Properties
        reimbursementRequests.setTravelRequestId(reimbursementRequestsDTO_request.getTravelRequestId());
        reimbursementRequests.setRemarks("");
        reimbursementRequests.setRequestRaisedByEmployeeId(reimbursementRequestsDTO_request.getRequestRaisedByEmployeeId());
        reimbursementRequests.setReimbursementTypes(
                new ReimbursementTypes(
                        reimbursementTypesRepository.findByType(
                                reimbursementRequestsDTO_request.getReimbursementType()
                        ).getId(),reimbursementRequestsDTO_request.getReimbursementType()
                )
        );
        reimbursementRequests.setInvoiceNo(reimbursementRequestsDTO_request.getInvoiceNo());
        reimbursementRequests.setInvoiceDate(reimbursementRequestsDTO_request.getInvoiceDate());
        reimbursementRequests.setInvoiceAmount(reimbursementRequestsDTO_request.getInvoiceAmount());
        reimbursementRequests.setDocumentURL(reimbursementRequestsDTO_request.getDocumentURL());
        reimbursementRequests.setStatus(reimbursementRequestsDTO_request.getStatus());

        return reimbursementRequests;
    }



    public ReimbursementRequests toReimbursementRequests_from_Response(ReimbursementRequestsDTO_Response reimbursementRequestsDTO_response){
        return new ReimbursementRequests(
                reimbursementRequestsDTO_response.getId(),
                reimbursementRequestsDTO_response.getTravelRequestId(),
                reimbursementRequestsDTO_response.getRequestRaisedByEmployeeId(),
                reimbursementRequestsDTO_response.getRequestDate(),
                reimbursementRequestsDTO_response.getReimbursementTypes(),
                reimbursementRequestsDTO_response.getInvoiceNo(),
                reimbursementRequestsDTO_response.getInvoiceDate(),
                reimbursementRequestsDTO_response.getInvoiceAmount(),
                reimbursementRequestsDTO_response.getDocumentURL(),
                reimbursementRequestsDTO_response.getRequestProcessedOn(),
                reimbursementRequestsDTO_response.getRequestProcessedByEmployeeId(),
                reimbursementRequestsDTO_response.getStatus(),
                reimbursementRequestsDTO_response.getRemarks()
        );
    }
}
