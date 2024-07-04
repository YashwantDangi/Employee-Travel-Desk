package com.etd.controller;

import com.etd.dto.ReimbursementTypesDTO;
import com.etd.service.ReimbursementTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reimbursement")
@CrossOrigin("http://localhost:4200")
public class ReimbursementTypesController {

    @Autowired
    private ReimbursementTypesService service;

    /**
     * Handler for retrieving all reimbursement types
     * @return
     */
    @GetMapping("/types")
    public ResponseEntity<?> getAllReimbursementTypes_Handler(){
        List<ReimbursementTypesDTO> lrtDTO = service.getAllReimbursementTypes();
        if(!lrtDTO.isEmpty()){
            return new ResponseEntity<List<ReimbursementTypesDTO>>(lrtDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<ReimbursementTypesDTO>>(HttpStatus.BAD_REQUEST);
        }
    }
}
