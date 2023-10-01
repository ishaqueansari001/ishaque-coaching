package com.ishaque.coaching.microservices.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ishaque.coaching.microservices.model.Candidate;
import com.ishaque.coaching.microservices.model.CourseDetails;

import com.ishaque.coaching.microservices.model.User;
import com.ishaque.coaching.microservices.service.CandidateService;
import com.ishaque.coaching.microservices.service.CourseService;

import jakarta.validation.Valid;

@Controller
public class CoachingController {

	@Autowired
	private CandidateService candidateService;

	@GetMapping("")
	public String viewHomePage() {
		return "home";
	}

	@GetMapping("/about-us")
	public String viewAboutUsPage() {
		return "about";
	}

	// sign_up related changes:: start

	@GetMapping("/signup_form")
	public String showRegistrationForm(Model model) {
		model.addAttribute("student", new Candidate());
		return "signup_form";
	}

	@PostMapping("/signup_form")
	public String postRegistrationForm(@Valid Candidate details, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("fields", result);
			model.addAttribute("student", new Candidate());
			return "signup_form";
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(details.getPassword());
		details.setPassword(encodedPassword);

		candidateService.addUser(details);

		return "register_success";
	}

	@GetMapping("/register_success")
	public String register() {
		return "register_success";
	}

	// sign_up related changes:: end

	@GetMapping("/login")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@Autowired
	CourseService courseService;

	@GetMapping("/courses/java")
	public String java(Model model) {
		CourseDetails details = courseService.getCourseDetails("JAVA");
		model.addAttribute("courseDetails", details);
		return "courses/java";
	}

	@GetMapping("/courses/DevOps")
	public String DevOps(Model model) {
		CourseDetails details = courseService.getCourseDetails("DevOps");
		model.addAttribute("courseDetails", details);
		return "courses/java";
	}

}