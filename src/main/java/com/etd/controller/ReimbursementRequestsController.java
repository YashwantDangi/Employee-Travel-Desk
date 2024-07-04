package com.etd.controller;

import com.etd.dto.ProcessReimbursementDTO;
import com.etd.dto.ReimbursementRequestsDTO_Request;
import com.etd.dto.ReimbursementRequestsDTO_Response;
import com.etd.service.ReimbursementRequestsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reimbursement")
@CrossOrigin("http://localhost:4200")
public class ReimbursementRequestsController {

    @Autowired
    private ReimbursementRequestsService service;

    /**
     * Handler for creating a new reimbursement request
     * @param reimbursementRequestsDTO_request
     * @return
     */
    @PostMapping("/add")
        public ResponseEntity<?> createNewReimbursement_Handler(@Valid @RequestBody ReimbursementRequestsDTO_Request reimbursementRequestsDTO_request){
           // System.out.println("Reimbursement Request: " + reimbursementRequestsDTO_request);
            String result = service.newReimbursement(reimbursementRequestsDTO_request);
            if(result.equals("success")){
                log.info("Reimbursement added successfully");
                return new ResponseEntity<>("Reimbursement added successfully",HttpStatus.OK);
            }else{
                log.error("Result failed");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    /**
     * Handler for retrieving all reimbursement requests having same travel request id
     * @param travelRequestId
     * @return
     */
    @GetMapping("/{travelRequestId}/requests")
    public ResponseEntity<?> getAllReimbursementRequestsByTravelRequestId_Handler(@PathVariable("travelRequestId") int travelRequestId){
        List<ReimbursementRequestsDTO_Response> lrrDTO = service.getAllReimbursementsByRequestId(travelRequestId);
        if(!lrrDTO.isEmpty()){
            return new ResponseEntity<List<ReimbursementRequestsDTO_Response>>(lrrDTO,HttpStatus.OK);
        }else{
            return new ResponseEntity<List<ReimbursementRequestsDTO_Response>>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handler for retrieving a particular reimbursement request
     * @param reimbursementId
     * @return
     */
    @GetMapping("/{reimbursementId}")
    public ResponseEntity<?> getReimbursementRequestsByRequestId_Handler(@PathVariable("reimbursementId") int reimbursementId){
        ReimbursementRequestsDTO_Response rrDTO = service.getReimbursementById(reimbursementId);
        if(rrDTO != null){
            return new ResponseEntity<ReimbursementRequestsDTO_Response>(rrDTO,HttpStatus.OK);
        }else{
            return new ResponseEntity<ReimbursementRequestsDTO_Response>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handler for processing a reimbursement request
     * @param reimbursementId
     * @param processReimbursementDTO
     * @return
     */
    @PutMapping("/{reimbursementId}/process")
    public ResponseEntity<?> processReimbursementRequests_Handler(@PathVariable("reimbursementId") int reimbursementId,@RequestBody ProcessReimbursementDTO processReimbursementDTO){
        String outcome = service.reimbursementProcess(reimbursementId, processReimbursementDTO);
        System.out.println(outcome);
        String response = "Reimbursement got " + outcome;
        if("ACCEPTED".equals(outcome) || "REJECTED".equals(outcome)){
            log.info("Reimbursement processed successfully");
            return new ResponseEntity<String>(response, HttpStatus.OK);
        }else{
            log.error("Reimbursement process failed" +
                    "");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
