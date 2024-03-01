package net.javaguides.todo.controler;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.LoginDto;
import net.javaguides.todo.dto.RegisterDto;
import net.javaguides.todo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    //Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getUsernameOrEmail() + " : " +loginDto.getPassword());
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}