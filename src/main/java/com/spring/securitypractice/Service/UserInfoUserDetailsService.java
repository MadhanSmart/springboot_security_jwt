package com.spring.securitypractice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.spring.securitypractice.Entity.UserInfo;
import com.spring.securitypractice.Repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInfo userInfo = userInfoRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found :"+username));
				
		
		return UserInfoService.build(userInfo);
	}

	
}
