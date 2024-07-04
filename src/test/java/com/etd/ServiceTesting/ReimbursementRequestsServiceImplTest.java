package com.etd.ServiceTesting;

import com.etd.dto.ReimbursementRequestsDTO_Request;
import com.etd.dto.ReimbursementRequestsDTO_Response;
import com.etd.entity.ReimbursementRequests;
import com.etd.entity.ReimbursementTypes;
import com.etd.enumeration.ReimbursementRequestsStatusEnum;
import com.etd.repositories.ReimbursementRequestsRepository;
import com.etd.service.impl.ReimbursementRequestsServiceImpl;
import com.etd.utilities.ReimbursementRequestsMappingUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReimbursementRequestsServiceImplTest {

    @Mock
    private ReimbursementRequestsRepository reimbursementRequestsRepo;

    @Mock
    private ReimbursementRequestsMappingUtility requestUtility;

    @InjectMocks
    private ReimbursementRequestsServiceImpl reimbursementRequestsService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

//    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//    Testing - get all reimbursement requests by travel request id
    @Test
    public void testGetAllReimbursementRequestsByRequestId(){
        int travelRequestId = 123;
        List<ReimbursementRequests> mockRequests = new ArrayList<>();
        mockRequests.add(new ReimbursementRequests(1, travelRequestId, 101, LocalDate.of(2024,3,18),new ReimbursementTypes(104,"LOCALTRAVEL"),"INV001",LocalDate.of(2024,3,10), 50,"http://example.com",LocalDate.of(2024,3,20),201, ReimbursementRequestsStatusEnum.ACCEPTED,""));
        mockRequests.add(new ReimbursementRequests(2, travelRequestId, 102, LocalDate.of(2024,3,19),new ReimbursementTypes(101,"FOOD"),"INV002",LocalDate.of(2024,3,10), 100,"http://example.com",LocalDate.of(2024,3,20),202,ReimbursementRequestsStatusEnum.REJECTED,"Invoice Amount is different."));

        when(reimbursementRequestsRepo.findByTravelRequestId(travelRequestId)).thenReturn(mockRequests);

        when(requestUtility.toReimbursementRequestsDTO_Repsonse(any(ReimbursementRequests.class)))
                .thenAnswer(invocation -> {
                    ReimbursementRequests request = invocation.getArgument(0);
                    return new ReimbursementRequestsDTO_Response(request.getId(), request.getTravelRequestId(), request.getRequestRaisedByEmployeeId(), request.getRequestDate(), request.getReimbursementTypes(),request.getInvoiceNo(), request.getInvoiceDate(),request.getInvoiceAmount(), request.getDocumentURL(), request.getRequestProcessedOn(), request.getRequestProcessedByEmployeeId(), request.getStatus(), request.getRemarks());
                });

        List<ReimbursementRequestsDTO_Response> result = reimbursementRequestsService.getAllReimbursementsByRequestId(travelRequestId);

        assertEquals(2, result.size());
        assertEquals("LOCALTRAVEL", result.get(0).getReimbursementTypes().getType());
        assertEquals("FOOD", result.get(1).getReimbursementTypes().getType());

        verify(reimbursementRequestsRepo).findByTravelRequestId(travelRequestId);

        verify(requestUtility, times(2)).toReimbursementRequestsDTO_Repsonse(any(ReimbursementRequests.class));
    }

//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //  New Reimbursement Requests Method Testing
    @Test
    void whenNewReimbursement_thenSaveIsCalled() {
        // Given
        ReimbursementRequestsDTO_Request newReimbursementRequestDTO = new ReimbursementRequestsDTO_Request();
        // Populate your dto with test data as necessary
        ReimbursementRequests rr = new ReimbursementRequests();
        // Assuming the toReimbursementRequests method will convert the dto to this entity

        when(requestUtility.toReimbursementRequests_from_Request(newReimbursementRequestDTO)).thenReturn(rr);

        // When
        reimbursementRequestsService.newReimbursement(newReimbursementRequestDTO);

        // Then
        verify(requestUtility, times(1)).toReimbursementRequests_from_Request(newReimbursementRequestDTO);
        verify(reimbursementRequestsRepo, times(1)).save(rr);
    }

//    -----------------------------------------------------------------------------------------------------------------------------------------------

//    Testing - Get Reimbursement By Id
    @Test
    void whenReimbursementExists_thenConvertToDTO() {
        int reimbursementId = 1;
        ReimbursementRequests foundReimbursement = new ReimbursementRequests();
        foundReimbursement.setId(reimbursementId);
        // Populate other necessary fields of foundReimbursement as needed

        when(reimbursementRequestsRepo.findById(reimbursementId)).thenReturn(Optional.of(foundReimbursement));
        ReimbursementRequestsDTO_Response expectedDTO = new ReimbursementRequestsDTO_Response();
        when(requestUtility.toReimbursementRequestsDTO_Repsonse(any(ReimbursementRequests.class))).thenReturn(expectedDTO);

        ReimbursementRequestsDTO_Response actualDTO = reimbursementRequestsService.getReimbursementById(reimbursementId);

        assertSame(expectedDTO, actualDTO, "The dto returned was not the one expected");
        verify(reimbursementRequestsRepo, times(1)).findById(reimbursementId);
        verify(requestUtility, times(1)).toReimbursementRequestsDTO_Repsonse(foundReimbursement);
    }


    @Test
    void whenReimbursementDoesNotExist_thenReturnEmptyDTO() {
        int reimbursementId = 1;
        when(reimbursementRequestsRepo.findById(reimbursementId)).thenReturn(Optional.empty());
        ReimbursementRequestsDTO_Response emptyDTO = new ReimbursementRequestsDTO_Response();
        when(requestUtility.toReimbursementRequestsDTO_Repsonse(any(ReimbursementRequests.class))).thenReturn(emptyDTO);

        ReimbursementRequestsDTO_Response resultDTO = reimbursementRequestsService.getReimbursementById(reimbursementId);

        assertNotNull(resultDTO, "The result should not be null even if the reimbursement is not found");
        verify(reimbursementRequestsRepo, times(1)).findById(reimbursementId);
        verify(requestUtility, times(1)).toReimbursementRequestsDTO_Repsonse(any(ReimbursementRequests.class));
    }

//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------

//    Testing - Reimbursement process method
//    @Test
//    public void whenFoodReimbursementIsWithinBudget_thenAccepted() {
//        // Setup
//        ReimbursementRequestsDTO_Response rrDTO = new ReimbursementRequestsDTO_Response();
//        rrDTO.setStatus(ReimbursementRequestsStatusEnum.valueOf("NEW"));
//        rrDTO.setInvoiceAmount(1200); // Within acceptable range
//        ReimbursementTypes type = new ReimbursementTypes();
//        type.setType("FOOD");
//        rrDTO.setReimbursementTypes(type);
//
//        when(requestUtility.toReimbursementRequests_from_Response(any(ReimbursementRequestsDTO_Response.class)))
//                .thenReturn(new ReimbursementRequests());
//
//        // Execution
//        String result = reimbursementRequestsService.reimbursementProcess(rrDTO.getId(),new ProcessReimbursementDTO("ACCEPTED",""));
//
//        // Assertions
//        assertEquals("ACCEPTED", result, "Status should be 'Accepted'");
//        verify(reimbursementRequestsRepo, times(1)).save(any(ReimbursementRequests.class));
//    }

//    @ParameterizedTest
//    @CsvSource({
//            "FOOD, 1100, ACCEPTED",
//            "WATER, 1500, ACCEPTED",
//            "LAUNDRY, 300, ACCEPTED",
//            "FOOD, 1600, REJECTED",
//            "LAUNDRY, 600, REJECTED",
//            "Other, 900, ACCEPTED",
//            "Other, 1100, REJECTED"
//    })
//    void testReimbursementRequests(String type, int amount, String expectedStatus) {
//        // Setup
//        ReimbursementRequestsDTO_Response rrDTO = new ReimbursementRequestsDTO_Response();
//        rrDTO.setStatus(ReimbursementRequestsStatusEnum.valueOf("NEW"));
//        rrDTO.setInvoiceAmount(amount);
//        ReimbursementTypes reimbursementType = new ReimbursementTypes();
//        reimbursementType.setType(type);
//        rrDTO.setReimbursementTypes(reimbursementType);
//
//        when(requestUtility.toReimbursementRequests_from_Response(any(ReimbursementRequestsDTO_Response.class)))
//                .thenReturn(new ReimbursementRequests());
//
//        // Execution
//        String result = reimbursementRequestsService.reimbursementProcess(rrDTO.getId(), new ProcessReimbursementDTO(expectedStatus,rrDTO.getRemarks()));
//
//        // Assertions based on expected status
//        assertEquals(expectedStatus, result);
//        if (ReimbursementRequestsStatusEnum.NEW.equals(rrDTO.getStatus())) {
//            verify(reimbursementRequestsRepo, times(1)).save(any(ReimbursementRequests.class));
//        }
//    }

//    -----------------------------------------------------------------------------------------------------------------------------------------
}
