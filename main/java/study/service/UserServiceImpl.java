package study.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import study.model.LoginDto;
import study.model.Role;
import study.model.User;
import study.model.UserDto;
import study.repository.RoleRepository;
import study.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
    @Override
	public UserDto addUser(UserDto userDto) {
		if(userRepository.findByEmail(userDto.getEmail())!=null) {
			return null;
		}else {
			User user = new User();
	        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
	        user.setEmail(userDto.getEmail());
	        // encrypt the password using spring security
	        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

	        Role role = roleRepository.findByName("ROLE_USER");
	        if(role == null){
	            role = addRoleToDB();
	        }
	        user.setRoles(Arrays.asList(role));
	        userRepository.save(user);
	        userDto.setPassword("");
	        return userDto; 
		}
	}
    private Role addRoleToDB(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
	@Override
	public List<User> findAll() {
		List<User> userList =  userRepository.findAll();
		Iterator<User> itr = userList.iterator();
		while (itr.hasNext()) {
			User user = (User) itr.next();
			user.setRoles(null);
		}
		return userList;
	}
	@Override
	public boolean login(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail());
		
		if(user!=null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
			return true;
		}	
		return false;
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);	
	}
}
