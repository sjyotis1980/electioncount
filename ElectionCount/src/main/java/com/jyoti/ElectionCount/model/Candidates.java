/**
 * 
 */
package com.jyoti.ElectionCount.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author JyotiKumar
 *
 */
@Entity
public class Candidates implements Serializable {
	private static final long serialVersionUID = 124525475L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String voterId;

	private String candidateId;

	public Candidates(){
		
	}
	/**
	 * @param voterId
	 * @param candidateId
	 */
	public Candidates(String voterId, String candidateId) {
		super();
		this.voterId = voterId;
		this.candidateId = candidateId;
	}

	/**
	 * @return the voterId
	 */
	public String getVoterId() {
		return voterId;
	}

	/**
	 * @param voterId
	 *            the voterId to set
	 */
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	/**
	 * @return the candidateId
	 */
	public String getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId
	 *            the candidateId to set
	 */
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
