/**
 * 
 */
package com.jyoti.ElectionCount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jyoti.ElectionCount.model.Candidates;

/**
 * @author JyotiKumar
 *
 */
@Repository
public interface CandidatesRepository extends JpaRepository<Candidates, Long> {

	@Query("SELECT count(c.voterId) FROM Candidates c where c.candidateId = ?1")
	Integer findByCandidateId(String candidateId);

	@Query("SELECT c.candidateId FROM Candidates c where c.voterId = ?1")
	String findByVoterId(String voterId);

}
