package com.etd.validation;


import jakarta.validation.Constraint;

@Constraint(validatedBy = DocumentURLValidation.class)
public @interface ValidateDocument {

}
