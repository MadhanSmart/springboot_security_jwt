package com.spring.securitypractice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.securitypractice.Entity.Student;
import com.spring.securitypractice.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public Student save(Student student) {
		 student = new Student(student.getFirstName(), 
				student.getLastName(), student.getPhoneNumber(),
				student.getBirthDate());
		 return studentRepository.save(student);
	}

	public Optional<Student> getById(int id) {
		
		return studentRepository.findById(id);
	}


}
