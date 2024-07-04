package com.etd.dto;

import com.etd.enumeration.ReimbursementRequestsStatusEnum;
import com.etd.validation.ValidateDocument;
import com.etd.validation.ValidateReimbursement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidateReimbursement // Custom Constraint
public class ReimbursementRequestsDTO_Request {

    @NotNull(message = "Travel Request Id should not be null")
    private Integer travelRequestId;

    @NotNull(message = "Request Raised By Employee Id should not be null")
    private Integer requestRaisedByEmployeeId;

    @NotBlank(message = "Reimbursement Type should not be null")
    private String reimbursementType;

    @NotNull(message = "Invoice No should not be null")
    private String invoiceNo;

    @NotNull(message = "Invoice Date should not be null")
    @Past
    private LocalDate invoiceDate;

    @NotNull(message = "Invoice Amount should not be null")
    @PositiveOrZero(message = "Invoice Amount must be positive")
    private Integer invoiceAmount;

    @NotBlank(message = "DocumentURL should not be blank")
    @ValidateDocument //	Custom Constraint
    private String documentURL;

    private ReimbursementRequestsStatusEnum status = ReimbursementRequestsStatusEnum.NEW;

}
