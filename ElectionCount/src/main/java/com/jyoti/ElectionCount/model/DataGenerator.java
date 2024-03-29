/**
 * 
 */
package com.jyoti.ElectionCount.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;



/**
 * @author JyotiKumar
 *
 */
@Component
public class DataGenerator implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(DataGenerator.class);
	static Map<String, String> electionMap = new HashMap<String, String>();

	@Autowired
	private BloomFilterManager bloomFilterManager;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.ApplicationRunner#run(org.springframework.boot.
	 * ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Start run.....");
		storeValidList();
	

	}

	public void storeValidList() throws IOException {

		BufferedReader bufferedReader = null;
		try {

			bufferedReader = fileReader("classpath:validVotersList.txt");

			String strCurrentLine;

			while (bufferedReader != null && (strCurrentLine = bufferedReader.readLine()) != null) {
				log.info("The voterid in validVotersList1 file ******** " + strCurrentLine.trim());
				bloomFilterManager.getBloomFilter().put(strCurrentLine.trim());

			}
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();

		}
	}

	
	public BufferedReader fileReader(String path) throws FileNotFoundException {

		BufferedReader objReader = null;
		InputStream inputStream = null;

		log.info("Adding valid voter id and candidate id is in progress ..... ");

		File file = ResourceUtils.getFile(path);
		// File is found
		log.info("File Found : " + file.exists());

		inputStream = new FileInputStream(file);
		objReader = new BufferedReader(new InputStreamReader(inputStream));

		return objReader;
	}

}
