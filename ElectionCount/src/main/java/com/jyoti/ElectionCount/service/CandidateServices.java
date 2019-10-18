/**
 * 
 */
package com.jyoti.ElectionCount.service;

import java.util.Map;

/**
 * @author JyotiKumar
 *
 */
public interface CandidateServices {

	void save(Map<String, String> map);

	String getCandidate(String voterId);

	Integer getVotingCount(String candidateId);

}
