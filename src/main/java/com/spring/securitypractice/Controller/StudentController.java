package com.spring.securitypractice.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.spring.securitypractice.Entity.Student;
import com.spring.securitypractice.Exception.StudentNotFoundException;
import com.spring.securitypractice.Service.StudentService;
import com.spring.securitypractice.jwt.JwtUtils;
import com.spring.securitypractice.payload.JwtRequest;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/list")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> student = studentService.getAllStudent();
		return ResponseEntity.ok().body(student);
	}

	@GetMapping("/byId")
	public Optional<Student> getById(@PathVariable int id) throws StudentNotFoundException {

		Optional<Student> student = studentService.getById(id);

		if (student == null) {
			throw new StudentNotFoundException("id-" + id);
		}
		return student;
	}

	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {

		student = studentService.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();

		return ResponseEntity.created(location).body(student);

	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody JwtRequest jwtRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtUtils.generateToken(jwtRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}
}
