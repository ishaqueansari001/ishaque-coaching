package com.ishaque.coaching.microservices.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishaque.coaching.microservices.model.Candidate;
import com.ishaque.coaching.microservices.model.CarriCulam;
import com.ishaque.coaching.microservices.model.CourseDetails;
import com.ishaque.coaching.microservices.model.Trainer;

@Component
public class CourseService {

	@Value("${course.url}")
	private String courseUrl;

	@Autowired
	RestTemplate restTemplate;

	public CourseDetails getCourseDetails(String name) {


		String url = courseUrl + "/param";

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url).queryParam("name", name).encode().toUriString();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity requestEntity = new HttpEntity<>(headers);
		ResponseEntity<CourseDetails[]> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity,
				CourseDetails[].class);

		if (response == null || response.getBody() == null || Arrays.asList(response.getBody()).size() == 0) {
			throw new UsernameNotFoundException("course not Found");
		}

		List<CourseDetails> courseList = Arrays.asList(response.getBody());

		return courseList.get(0);

	}

}
