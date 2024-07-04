package com.etd.validation;

import com.etd.dto.ReimbursementRequestsDTO_Request;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReimbursementValidator implements ConstraintValidator<ValidateReimbursement, ReimbursementRequestsDTO_Request> {

    @Override
    public boolean isValid(ReimbursementRequestsDTO_Request rrDTO, ConstraintValidatorContext context) {
        int spendAmt = rrDTO.getInvoiceAmount();
        String type = rrDTO.getReimbursementType();

        if ("Food".equals(type) || "Water".equals(type)) {
            return spendAmt >= 1000 && spendAmt <= 1500;
        } else if ("Laundry".equals(type)) {
            return spendAmt >= 250 && spendAmt <= 500;
        } else if("Localtravel".equals(type)){
            return spendAmt <= 1000;
        }
        return false;
    }
}
