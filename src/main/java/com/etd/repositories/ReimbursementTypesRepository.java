package com.etd.repositories;

import com.etd.entity.ReimbursementTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReimbursementTypesRepository extends CrudRepository<ReimbursementTypes, Integer> {
     ReimbursementTypes findByType(String type);
}
