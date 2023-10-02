package com.spring.securitypractice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.securitypractice.Entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{

}
