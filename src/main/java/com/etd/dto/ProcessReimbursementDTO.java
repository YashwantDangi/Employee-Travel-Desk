package com.etd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessReimbursementDTO {

    private String status;

    private String remarks;

    private int requestProcessedByEmployeeId;
}
