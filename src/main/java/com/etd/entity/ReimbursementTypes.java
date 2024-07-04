package com.etd.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Reimbursement_Types")
public class ReimbursementTypes {

//	Given Id
	@Id
	@Column(name = "Reimbursement_Types_Id")
	private int id;

//	Given Type
	@Column(name = "Type")
	private String type;

}
