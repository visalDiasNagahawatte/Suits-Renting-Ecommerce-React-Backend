package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.UserDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 21-Jul-23
 */
public interface UserService {
    boolean addUser(UserDTO userDTO);
    UserDTO findUser(String email);
    boolean loginAuth(String email, String password);
    boolean updateUser(UserDTO userDTO, String curEmail);
    String deleteUser(String email);
    List<UserDTO> getAllUsers();
}
