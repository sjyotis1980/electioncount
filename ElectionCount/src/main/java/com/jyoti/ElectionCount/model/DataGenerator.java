/**
 * 
 */
package com.jyoti.ElectionCount.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.config.ServerAuthContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.jyoti.ElectionCount.service.CandidateServices;



/**
 * @author JyotiKumar
 *
 */
@Component
public class DataGenerator implements ApplicationRunner {
	
	private static final Logger log =LoggerFactory.getLogger(DataGenerator.class);
	static Map<String, String> electionMap = new HashMap<String, String>();
	
	@Autowired
	private BloomFilterManager bloomFilterManager;
		
	
			
	@Autowired
	private CandidateServices candidateServices;

	/* (non-Javadoc)
	 * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//log.info("Start run.....");
		storeValidList();
		addElection();

	}
	
	public void storeValidList() throws IOException {
		
		BufferedReader bufferedReader = null;
		try {
			
			bufferedReader = fileReader("classpath:validVotersList.txt");
			
           
            

			String strCurrentLine;
			//objReader = new BufferedReader(new FileReader("D:/Problem2/validVotersList.txt"));
			//int bloomSetSize = (int) objReader.lines().count();
			//System.out.println(" The no of voters " + bufferedReader);
			//bf = new BloomFilter(100000, 36);
			
			
			while (bufferedReader != null && (strCurrentLine = bufferedReader.readLine()) != null) {
				//System.out.println("The voterid in validVotersList1 file ******** " + strCurrentLine.trim());
				//log.info("The voterid in validVotersList1 file ******** "+ strCurrentLine.trim());
				bloomFilterManager.getBloomFilter().put(strCurrentLine.trim());
				//bf.add(strCurrentLine.trim());
			}
		}finally {
				if (bufferedReader != null)
					bufferedReader.close();
			
		}
	}

	public void addElection() throws IOException{
		BufferedReader bufferedReader = null;
		String strCurrentLine;
		String voterId = null;
		String candidateId = null;

		try {
			
			bufferedReader = fileReader("classpath:votersCandList.txt");
			
			while (bufferedReader != null && (strCurrentLine = bufferedReader.readLine()) != null) {
				//System.out.println("The voter id in validCandList1  " + strCurrentLine);
				if (strCurrentLine != null && (strCurrentLine.length() > 1 && strCurrentLine.length() <= 6)
						|| strCurrentLine.length() > 6) {
					voterId = strCurrentLine.substring(0, 6);
					//System.out.println("VoterId " + voterId);
					//log.info("VoterId " + voterId);
				}
				if (strCurrentLine != null && strCurrentLine.length() > 6) {
					candidateId = strCurrentLine.substring(7, strCurrentLine.length()).trim();
					//System.out.println("candidateId " + candidateId);
					//log.info("candidateId " + candidateId);
				}
				//System.out.println(" The bf object " + voterId);
				log.info(" The bf object " + bloomFilterManager.getBloomFilter().mightContain(voterId) + "   " + voterId);
			//	if (bf.contains(voterId)) {
				if (bloomFilterManager.getBloomFilter().mightContain(voterId)){
					//System.out.println(" putting values in electionMap ##### " + voterId);
					log.info(" putting values in electionMap ##### " + voterId);
					electionMap.put(voterId, candidateId);
				//	candidates.setVoterId(voterId);
					//System.out.println(" candidateId ##### " + candidateId);
					
				//	candidates.setCandidateId(candidateId);
					//System.out.println(" candidateId Not null ##### " + candidateId);
					
					
					
					
				}else{
					//System.out.println("Invalid Voter "+voterId);
					log.info("Invalid Voter "+voterId);
				}
				
				candidateServices.save(electionMap);
				//System.out.println("candidateServices.save completed ");
				log.info("candidateServices.save completed ");
			}
		} finally {
			
				if (bufferedReader != null)
					bufferedReader.close();
			
		}

	}
	
	public BufferedReader fileReader(String path) throws FileNotFoundException{
		
		BufferedReader objReader = null;
		InputStream inputStream = null;
		
		System.out.println("Adding valid voter id and candidate id is in progress ..... ");
		
		//inputStream = servletContext.getResourceAsStream("classpath:votersCandList.txt");
		File file = ResourceUtils.getFile(path);
		//File is found
		System.out.println("File Found : " + file.exists());
		log.info("File Found : " + file.exists());
		
		inputStream = new FileInputStream(file);
		objReader = new BufferedReader(new InputStreamReader(inputStream));
		
		return objReader;
	}

}
