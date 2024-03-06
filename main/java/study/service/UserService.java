package study.service;

import java.util.List;

import study.model.LoginDto;
import study.model.User;
import study.model.UserDto;

public interface UserService {

	UserDto addUser(UserDto userDto);

	List<User> findAll();

	boolean login(LoginDto loginDto);

	void deleteUserById(Long id);

}
