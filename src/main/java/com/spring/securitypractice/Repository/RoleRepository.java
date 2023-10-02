package com.spring.securitypractice.Repository;

import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.securitypractice.Entity.ERole;
import com.spring.securitypractice.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(ERole name);

}
