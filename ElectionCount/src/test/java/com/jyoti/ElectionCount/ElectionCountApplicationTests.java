package com.jyoti.ElectionCount;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElectionCountApplicationTests {

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Autowired
	private TestRestTemplate template;

	@Test
	public void testAcontextLoads() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<?> response = template.withBasicAuth("admin", "admin").exchange(getRootUrl() + "/api/test/load",
				HttpMethod.GET, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}
    
	
	@Test
	public void testBcandidateData() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String value = "835";
		
		ResponseEntity<String> response = template.withBasicAuth("admin", "admin").exchange(getRootUrl() + "/api/test/candidateData/200001",
				HttpMethod.GET, entity, String.class);
		System.out.println(response.getBody());
		assertEquals(value, response.getBody()) ;
	}
	
	@Test
	public void testCvotingCount() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		Integer value = 423;
		
		ResponseEntity<Integer> response = template.withBasicAuth("admin", "admin").exchange(getRootUrl() + "/api/test/votingCount/835",
				HttpMethod.GET, entity, Integer.class);
		System.out.println(response.getBody());
		assertEquals(value, response.getBody()) ;
	}
 
}
