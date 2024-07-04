package com.etd.ControllerTesting;

import com.etd.controller.ReimbursementRequestsController;
import com.etd.dto.ProcessReimbursementDTO;
import com.etd.dto.ReimbursementRequestsDTO_Request;
import com.etd.dto.ReimbursementRequestsDTO_Response;
import com.etd.service.ReimbursementRequestsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ReimbursementRequestsControllerTest {

    @Mock
    ReimbursementRequestsService reimbursementRequestsService;

    @InjectMocks
    ReimbursementRequestsController reimbursementRequestsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNewReimbursement_Handler_Success() {
        // Arrange
       // ReimbursementRequestsServiceImpl mockService = mock(ReimbursementRequestsServiceImpl.class);
        ReimbursementRequestsDTO_Request request = new ReimbursementRequestsDTO_Request(); // populate this as needed
        when(reimbursementRequestsService.newReimbursement(request)).thenReturn("success");



        // Act
        ResponseEntity<?> response = reimbursementRequestsController.createNewReimbursement_Handler(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reimbursement added successfully", response.getBody());
    }

    @Test
    public void testCreateNewReimbursement_Handler_Failure() {
        // Arrange
        ReimbursementRequestsDTO_Request request = new ReimbursementRequestsDTO_Request(); // populate this as needed
        when(reimbursementRequestsService.newReimbursement(request)).thenReturn("failure");

        // Act
        ResponseEntity<?> response = reimbursementRequestsController.createNewReimbursement_Handler(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


        @Test
        public void testGetAllReimbursementRequestsByTravelRequestId_Handler_Success() {
            // Arrange
            int travelRequestId = 1; // replace with actual id
            List<ReimbursementRequestsDTO_Response> lrrDTO = new ArrayList<>(); // populate this as needed
            ReimbursementRequestsDTO_Response rrDTO_Request = new ReimbursementRequestsDTO_Response();
            lrrDTO.add(rrDTO_Request);

            when(reimbursementRequestsService.getAllReimbursementsByRequestId(travelRequestId)).thenReturn(lrrDTO);

            // Act
            ResponseEntity<?> response = reimbursementRequestsController.getAllReimbursementRequestsByTravelRequestId_Handler(travelRequestId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(lrrDTO, response.getBody());
        }

        @Test
        public void testGetAllReimbursementRequestsByTravelRequestId_Handler_Failure() {
            // Arrange
            int travelRequestId = 1; // replace with actual id
            List<ReimbursementRequestsDTO_Response> lrrDTO = new ArrayList<>();
            when(reimbursementRequestsService.getAllReimbursementsByRequestId(travelRequestId)).thenReturn(lrrDTO);

            // Act
            ResponseEntity<?> response = reimbursementRequestsController.getAllReimbursementRequestsByTravelRequestId_Handler(travelRequestId);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        public void testGetReimbursementRequestsByRequestId_Handler_Success() {
            // Arrange
            int reimbursementId = 1; // replace with actual id
            ReimbursementRequestsDTO_Response rrDTO = new ReimbursementRequestsDTO_Response(); // populate this as needed
            when(reimbursementRequestsService.getReimbursementById(reimbursementId)).thenReturn(rrDTO);

            // Act
            ResponseEntity<?> response = reimbursementRequestsController.getReimbursementRequestsByRequestId_Handler(reimbursementId);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(rrDTO, response.getBody());
        }

        @Test
        public void testGetReimbursementRequestsByRequestId_Handler_Failure() {
            // Arrange
            int reimbursementId = 1; // replace with actual id
            when(reimbursementRequestsService.getReimbursementById(reimbursementId)).thenReturn(null);

            // Act
            ResponseEntity<?> response = reimbursementRequestsController.getReimbursementRequestsByRequestId_Handler(reimbursementId);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        public void testProcessReimbursementRequests_Handler_Rejected() {
            // Arrange
            int reimbursementId = 1; // replace with actual id
            ProcessReimbursementDTO processReimbursementDTO = new ProcessReimbursementDTO(); // populate this as needed
            when(reimbursementRequestsService.reimbursementProcess(reimbursementId, processReimbursementDTO)).thenReturn("Rejected");

            // Act
            ResponseEntity<?> response = reimbursementRequestsController.processReimbursementRequests_Handler(reimbursementId, processReimbursementDTO);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNull(response.getBody());
        }

        @Test
        public void testProcessReimbursementRequests_Handler_Failure() {
            // Arrange
            int reimbursementId = 1; // replace with actual id
            ProcessReimbursementDTO processReimbursementDTO = new ProcessReimbursementDTO(); // populate this as needed
            when(reimbursementRequestsService.reimbursementProcess(reimbursementId, processReimbursementDTO)).thenReturn("failure");

            // Act
            ResponseEntity<?> response = reimbursementRequestsController.processReimbursementRequests_Handler(reimbursementId, processReimbursementDTO);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

}
