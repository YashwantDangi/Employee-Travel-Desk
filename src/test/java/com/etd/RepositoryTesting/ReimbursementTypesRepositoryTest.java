package com.etd.RepositoryTesting;

import com.etd.entity.ReimbursementTypes;
import com.etd.repositories.ReimbursementTypesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ReimbursementTypesRepositoryTest {

    @Autowired
    private ReimbursementTypesRepository reimbursementTypesRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindAll_Positive() {
        ReimbursementTypes rt = new ReimbursementTypes();
        rt.setId(1);
        rt.setType("Travel");
        entityManager.persist(rt);
        Iterable<ReimbursementTypes> it = reimbursementTypesRepo.findAll();
        assertTrue(it.iterator().hasNext());
    }

//    @Test
//    public void testFindAllNegative() {
//        Iterable<ReimbursementTypes> it = reimbursementTypesRepo.findAll();
//        assertFalse(it.iterator().hasNext());
//    }

    @Test
    public void testFindByIdPositive(){
        ReimbursementTypes rt = new ReimbursementTypes();
        rt.setId(1);
        rt.setType("Travel");
        entityManager.persist(rt);
        Optional<ReimbursementTypes> op = reimbursementTypesRepo.findById(1);
        assertTrue(op.isPresent());
    }

    @Test
    public void testFindByIdNegative(){
        Optional<ReimbursementTypes> op = reimbursementTypesRepo.findById(1);
        assertFalse(op.isPresent());
    }

    @Test
    public void testSavePositive(){
        ReimbursementTypes rt = new ReimbursementTypes();
        rt.setId(1);
        rt.setType("Travel");
        reimbursementTypesRepo.save(rt);
        Optional<ReimbursementTypes> op = reimbursementTypesRepo.findById(1);
        assertTrue(op.isPresent());
    }

    @Test
    public void testDeletePositive(){
        ReimbursementTypes rt = new ReimbursementTypes();
        rt.setId(1);
        rt.setType("Travel");
        entityManager.persist(rt);
        reimbursementTypesRepo.delete(rt);
        Optional<ReimbursementTypes> op = reimbursementTypesRepo.findById(1);
        assertFalse(op.isPresent());
    }
}
