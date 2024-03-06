package study.service;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import study.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		study.model.User employee = userRepository.findByEmail(email);
//
//		if (employee != null) {
//			return new User(email, employee.getPassword(),
//					mapRolesToAuthorities(employee.getRoles()));
//		} else {
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//	}
//
//	private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//		Collection<GrantedAuthority> mapRoles = roles.stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//		return mapRoles;
//	}

	 @Override
	    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
	        study.model.User user = userRepository.findByEmail(username);
	          if(user==null){
	                throw new UsernameNotFoundException("User does not exist");
	               }
	        Set<GrantedAuthority> authorities = user.getRoles().stream()
	                .map((role) -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toSet());

	        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
	    }
	
	
	
	
}
