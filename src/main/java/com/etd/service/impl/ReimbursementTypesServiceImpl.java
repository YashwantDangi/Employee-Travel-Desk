package com.etd.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.etd.dto.ReimbursementTypesDTO;
import com.etd.entity.ReimbursementTypes;
import com.etd.service.ReimbursementTypesService;
import com.etd.utilities.ReimbursementTypesMappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etd.repositories.ReimbursementTypesRepository;

@Service
public class ReimbursementTypesServiceImpl implements ReimbursementTypesService {

	@Autowired
	private ReimbursementTypesRepository reimbursementTypesRepo;

	@Autowired
	private ReimbursementTypesMappingUtility typeUtility;

	@Override
	public List<ReimbursementTypesDTO> getAllReimbursementTypes() {
		List<ReimbursementTypes> lrt =  (List<ReimbursementTypes>) reimbursementTypesRepo.findAll();
		List<ReimbursementTypesDTO> lrtDTO = new ArrayList<>();
		for(ReimbursementTypes rt : lrt){
			ReimbursementTypesDTO rtDTO = typeUtility.toReimbursementTypesDTO(rt);
			lrtDTO.add(rtDTO);
		}
		return lrtDTO;
	}

}
