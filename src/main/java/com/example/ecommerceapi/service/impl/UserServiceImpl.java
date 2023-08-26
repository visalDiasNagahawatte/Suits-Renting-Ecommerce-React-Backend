package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.UserDTO;
import com.example.ecommerceapi.entity.User;
import com.example.ecommerceapi.exception.*;
import com.example.ecommerceapi.repo.UserRepo;
import com.example.ecommerceapi.service.UserService;
import com.example.ecommerceapi.utill.PasswordEncoder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean addUser(UserDTO userDTO) {
        if (!Objects.isNull(userDTO)) {
            if (!userRepo.existsByEmail(userDTO.getEmail())) {
                userDTO.setPassword(encoder.hashPassword(userDTO.getPassword()));
                userRepo.save(modelMapper.map(userDTO, User.class));
                return true;
            } else {
                throw new EntryDuplicateException("email is already exists...");
            }
        } else {
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public UserDTO findUser(String email) {
        if (!email.isEmpty()) {
            Optional<User> user = userRepo.findByEmail(email);
            if (user.isPresent()) {
                UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
                userDTO.setPassword(null);
                return userDTO;
            } else {
                throw new UserNotFoundException("User not found...");
            }
        } else {
            throw new EntryNotFoundException("Invalid inputs...");
        }

    }

    @Override
    public boolean loginAuth(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            Optional<User> userOptional = userRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String hashedPassword = encoder.hashPassword(password);
                if (hashedPassword.equals(user.getPassword())) {
                    return true;
                } else {
                    throw new UnAuthorizedException("Invalid password. Please enter a valid password.");
                }
            } else {
                throw new EntryNotFoundException("Invalid email. Please enter a valid email.");
            }
        } else {
            throw new ValidationException("Please enter a valid email and password.");
        }
    }

    @Override
    public boolean updateUser(UserDTO userDTO, String curEmail) {
        if (!Objects.isNull(userDTO) && !curEmail.isEmpty()) {
            Optional<User> userOptional = userRepo.findByEmail(curEmail);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setEmail(userDTO.getEmail());
                user.setPassword(encoder.hashPassword(userDTO.getPassword()));
                user.setUserName(userDTO.getUserName());
                userRepo.save(user);
                return true;
            } else {
                throw new UserNotFoundException("User not found...");
            }
        } else {
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public String deleteUser(String email) {
        if (!email.isEmpty()) {
            if (userRepo.existsByEmail(email)) {
                return userRepo.deleteByEmail(email);
            } else {
                throw new UserNotFoundException("User not found...");
            }
        } else {
            throw new EntryNotFoundException("Invalid inputs...");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> all = userRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

}
