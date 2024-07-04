package com.etd.dto;

import java.time.LocalDate;

import com.etd.entity.ReimbursementTypes;
import com.etd.enumeration.ReimbursementRequestsStatusEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReimbursementRequestsDTO_Response {

	private int id;
	private int travelRequestId;
	private int requestRaisedByEmployeeId;
	private LocalDate requestDate;
	private ReimbursementTypes reimbursementTypes;
	private String invoiceNo;
	private LocalDate invoiceDate;
	private int invoiceAmount;
	private String documentURL;
	private LocalDate requestProcessedOn;
	private int requestProcessedByEmployeeId;
	private ReimbursementRequestsStatusEnum status;
	private String remarks;
}
