package com.etd.ControllerTesting;

import com.etd.controller.ReimbursementTypesController;
import com.etd.dto.ReimbursementTypesDTO;
import com.etd.service.ReimbursementTypesService;
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
import static org.mockito.Mockito.*;

public class ReimbursementTypesControllerTest {

    @Mock
    ReimbursementTypesService reimbursementTypesService;

    @InjectMocks
    ReimbursementTypesController reimbursementTypesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReimbursementTypes_Handler_Success() {
        // Arrange
        List<ReimbursementTypesDTO> lrtDTO = new ArrayList<>(); // populate this as needed
        ReimbursementTypesDTO rtDTO = new ReimbursementTypesDTO();
        lrtDTO.add(rtDTO);
        when(reimbursementTypesService.getAllReimbursementTypes()).thenReturn(lrtDTO);

        // Act
        ResponseEntity<?> response = reimbursementTypesController.getAllReimbursementTypes_Handler();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lrtDTO, response.getBody());
    }

    @Test
    public void testGetAllReimbursementTypes_Handler_Failure() {
        // Arrange
        List<ReimbursementTypesDTO> lrtDTO = new ArrayList<>();
        when(reimbursementTypesService.getAllReimbursementTypes()).thenReturn(lrtDTO);

        // Act
        ResponseEntity<?> response = reimbursementTypesController.getAllReimbursementTypes_Handler();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
