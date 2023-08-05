package com.example.ecommerceapi.api;

import com.example.ecommerceapi.dto.UserDTO;
import com.example.ecommerceapi.service.UserService;
import com.example.ecommerceapi.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 21-Jul-23
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<StandardResponse> addUser(@RequestBody UserDTO userDTO) {
        boolean b = userService.addUser(userDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "Success...",b),HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", params = {"email", "password"})
    public ResponseEntity<StandardResponse> loginAuth(@RequestParam String email, @RequestParam String password) {
        boolean b = userService.loginAuth(email, password);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "Success...",b),HttpStatus.CREATED);
    }

    @GetMapping(value = "/find", params = {"email"})
    public ResponseEntity<StandardResponse> findUser(@RequestParam String email) {
        UserDTO userDTO = userService.findUser(email);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Success...", userDTO), HttpStatus.OK);
    }

    @PutMapping(params = {"curEmail"})
    public ResponseEntity<StandardResponse> updateUser(@RequestBody UserDTO userDTO, @RequestParam String curEmail){
        boolean b = userService.updateUser(userDTO, curEmail);
        return new ResponseEntity<StandardResponse>(new StandardResponse(204, "Success...",b),HttpStatus.OK);
    }

    @DeleteMapping(params = {"email"})
    public ResponseEntity<StandardResponse> deleteUser(@RequestParam String email){
        String s = userService.deleteUser(email);
        return new ResponseEntity<StandardResponse>(new StandardResponse(203, "Success...",s),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Success...",allUsers),HttpStatus.OK);
    }
}
