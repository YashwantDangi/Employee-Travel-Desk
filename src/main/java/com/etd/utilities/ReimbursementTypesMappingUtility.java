package com.etd.utilities;

import com.etd.dto.ReimbursementTypesDTO;
import com.etd.entity.ReimbursementTypes;
import org.springframework.stereotype.Component;

@Component
public class ReimbursementTypesMappingUtility {
    public ReimbursementTypesDTO toReimbursementTypesDTO(ReimbursementTypes reimbursementTypes){
        return new ReimbursementTypesDTO(
                reimbursementTypes.getId(),
                reimbursementTypes.getType()
        );
    }
}
