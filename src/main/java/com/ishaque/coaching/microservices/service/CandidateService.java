package com.ishaque.coaching.microservices.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ishaque.coaching.microservices.exception.UserNotFoundException;
import com.ishaque.coaching.microservices.model.Candidate;
import com.ishaque.coaching.microservices.model.CourseDetails;

@Service
public class CandidateService {

	Logger log = LoggerFactory.getLogger(CandidateService.class);
	@Value("${candidate.url}")
	String candidateUrl;

	@Autowired
	RestTemplate restTemplate;

	public List<Candidate> getCandidates(String email,String mobile) {

		try {

			String url = candidateUrl + "/param";

			String urlTemplate = UriComponentsBuilder.fromHttpUrl(url).queryParam("email", email).queryParam("mobile", mobile).encode()
					.toUriString();

			HttpHeaders headers = new HttpHeaders();
			HttpEntity requestEntity = new HttpEntity<>(headers);
			ResponseEntity<Candidate[]> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity,
					Candidate[].class);

			List<Candidate> candidateList = Arrays.asList(response.getBody());
			return candidateList;
		} catch (Exception e) {
			log.error("Exception in getting the candidate Detail", e);
		}

		return null;

	}

	public Candidate addUser(Candidate candidate) {

		// check if user already exist
		List<Candidate> candidateList=	getCandidates(candidate.getEmail(), candidate.getMobile());
		if(candidateList.size()>0) {
			log.info("already user mobile or email is present");
			
			throw new UserNotFoundException((long) 1);
		}
		

		String url = candidateUrl;

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Candidate> requestEntity = new HttpEntity<Candidate>(candidate, headers);

		ResponseEntity<Candidate> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				Candidate.class);
		if (response == null || response.getBody() == null) {
			throw new UsernameNotFoundException("Candidate not Created");
		}

		return response.getBody();

	}

}
