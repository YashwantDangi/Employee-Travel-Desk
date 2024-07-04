package com.etd.validation;

import jakarta.validation.Constraint;

@Constraint(validatedBy = ReimbursementValidator.class)
public @interface ValidateReimbursement {
}
