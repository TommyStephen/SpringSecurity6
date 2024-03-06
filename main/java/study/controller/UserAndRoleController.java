package study.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import study.model.LoginDto;
import study.model.User;
import study.model.UserDto;
import study.service.UserService;

@RestController
@CrossOrigin
public class UserAndRoleController {

	private UserService userService;
		
	public UserAndRoleController(UserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping("angular/signup")
	public UserDto addUser(@RequestBody UserDto userDto) {
		
		return userService.addUser(userDto );
	}
	@GetMapping("angular/findAllUsers")
	public List<User> findAll(){
		return userService.findAll();
		
	}
	@PostMapping("angular/login")
	public boolean login(@RequestBody LoginDto loginDto) {
		if (userService.login(loginDto)) {
			
			return true;
		}
		return false;	
	}
	@DeleteMapping("angular/deleteUser")
	public void deleteUserById(@RequestParam Long id) {
		userService.deleteUserById(id);
	}
}
