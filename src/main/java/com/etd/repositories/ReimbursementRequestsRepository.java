package com.etd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etd.entity.ReimbursementRequests;

@Repository
public interface ReimbursementRequestsRepository extends JpaRepository<ReimbursementRequests, Integer> {

	List<ReimbursementRequests> findByTravelRequestId(int travelRequestId);

}
