insert into Reimbursement_Types (reimbursement_types_id, type)
 values
 (101,'FOOD'),
 (102,'WATER'),
 (103,'LAUNDRY'),
 (104,'LOCALTRAVEL');

insert into Reimbursement_Requests
 (reimbursement_requests_id, travel_request_id, request_raised_by_employee_id, request_date, reimbursement_types_id, invoice_no, invoice_date, invoice_amount, document_url, request_processed_on, request_processed_by_employee_id, status, remarks)
 values
 (1001,2001,3001,'2024-02-22',101,'IN100','2024-02-20',10000,'xyz.pdf','2024-02-22',2317148,'ACCEPTED',''),
 (1002,2001,3001,'2024-02-22',104,'IN100','2024-02-20',10000,'xyz.pdf','2024-02-22',2317148,'REJECTED','Invoice Amount is different.'),
 (1003,2002,3001,'2024-02-22',104,'IN100','2024-02-20',10000,'xyz.pdf','2024-02-22',2317148,'NEW','');

 insert into Users values(111,'yash','yash@123','TravelDeskExec');
 insert into Users values(222,'amit','amit@123','Employee');
 insert into Users values(333,'ankit','ankit@123','HR');
