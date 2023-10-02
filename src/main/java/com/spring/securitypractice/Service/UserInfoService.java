package com.spring.securitypractice.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.securitypractice.Entity.UserInfo;

public class UserInfoService implements UserDetails {

	private int id;
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserInfoService(int id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserInfoService build(UserInfo userInfo) {
		List<GrantedAuthority> authorities = userInfo.getRoles().stream()
		        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
		        .collect(Collectors.toList());
				
				return new UserInfoService(userInfo.getId(), 
						userInfo.getName(), userInfo.getEmail(), 
						userInfo.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
