package com.etd.ServiceTesting;

import com.etd.dto.ReimbursementTypesDTO;
import com.etd.entity.ReimbursementTypes;
import com.etd.repositories.ReimbursementTypesRepository;
import com.etd.service.impl.ReimbursementTypesServiceImpl;
import com.etd.utilities.ReimbursementTypesMappingUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
        import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReimbursementTypesServiceTest {

    @InjectMocks
    private ReimbursementTypesServiceImpl service; // Your service class

    @Mock
    private ReimbursementTypesRepository reimbursementTypesRepo; // Your repository

    @Mock
    private ReimbursementTypesMappingUtility typeUtility; // Your utility class for conversion

    @Test
    void whenGetAllReimbursementTypes_thenReturnDTOList() {
        // Given
        ReimbursementTypes reimbursementType1 = new ReimbursementTypes(); // Mock some entities
        reimbursementType1.setId(1);
        reimbursementType1.setType("Type1");
        ReimbursementTypes reimbursementType2 = new ReimbursementTypes();
        reimbursementType2.setId(2);
        reimbursementType2.setType("Type2");

        List<ReimbursementTypes> reimbursementTypesList = Arrays.asList(reimbursementType1, reimbursementType2);
        when(reimbursementTypesRepo.findAll()).thenReturn(reimbursementTypesList);

        ReimbursementTypesDTO reimbursementTypesDTO1 = new ReimbursementTypesDTO(); // Mock corresponding DTOs
        reimbursementTypesDTO1.setId(1);
        reimbursementTypesDTO1.setType("Type1");

        ReimbursementTypesDTO reimbursementTypesDTO2 = new ReimbursementTypesDTO();
        reimbursementTypesDTO2.setId(2);
        reimbursementTypesDTO2.setType("Type2");

        when(typeUtility.toReimbursementTypesDTO(reimbursementType1)).thenReturn(reimbursementTypesDTO1);
        when(typeUtility.toReimbursementTypesDTO(reimbursementType2)).thenReturn(reimbursementTypesDTO2);

        // When
        List<ReimbursementTypesDTO> result = service.getAllReimbursementTypes();

        // Then
        assertEquals(2, result.size(), "The size of the returned dto list should match the number of entities");
        assertEquals(reimbursementTypesDTO1.getType(), result.get(0).getType(), "The first dto should match the expected dto");
        assertEquals(reimbursementTypesDTO2.getType(), result.get(1).getType(), "The second dto should match the expected dto");

        verify(reimbursementTypesRepo, times(1)).findAll();
        verify(typeUtility, times(1)).toReimbursementTypesDTO(reimbursementType1);
        verify(typeUtility, times(1)).toReimbursementTypesDTO(reimbursementType2);
    }
}