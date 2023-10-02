package com.spring.securitypractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.securitypractice.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

	boolean existsByName(String username);

	boolean existsByEmail(String email);

	 Optional<UserInfo> findByName(String username);

}
