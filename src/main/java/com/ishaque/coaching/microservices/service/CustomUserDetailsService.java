package com.ishaque.coaching.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ishaque.coaching.microservices.model.Candidate;
import com.ishaque.coaching.microservices.model.CustomUserDetails;
import com.ishaque.coaching.microservices.model.User;



public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CandidateService cadidateService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Candidate> candidateList = cadidateService.getCandidates(email,null);
		if (candidateList == null || candidateList.size()==0) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user= new User();
		Candidate candidate=candidateList.stream().findFirst().get();
		user.setEmail(candidate.getEmail());
		user.setFirstName(candidate.getName());
		user.setPassword(candidate.getPassword());
		
		return new CustomUserDetails(user);
	}

}
