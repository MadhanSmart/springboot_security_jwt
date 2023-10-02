package com.spring.securitypractice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.securitypractice.Entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
