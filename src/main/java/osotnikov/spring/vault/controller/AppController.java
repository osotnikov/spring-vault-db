package osotnikov.spring.vault.controller;

import osotnikov.spring.vault.config.StaticVaultProps;
import osotnikov.spring.vault.dto.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osotnikov.spring.vault.entity.User;
import osotnikov.spring.vault.repository.UserRepository;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final UserRepository userRepository;
    private final StaticVaultProps staticVaultProps;

    @GetMapping("/user")
    public ResponseEntity<GenericResponse> getUsers() {
        return ResponseEntity.ok(new GenericResponse(true, "Users returned successfully", userRepository.findAll(), HttpStatus.OK.value(), LocalDateTime.now()));
    }

    @PostMapping("/user")
    public ResponseEntity<GenericResponse> addUser(@RequestBody User user) {
        return ResponseEntity.ok(new GenericResponse(true, "User saved successfully", userRepository.save(user), HttpStatus.CREATED.value(), LocalDateTime.now()));
    }


    @GetMapping("/staticVaultProps")
    public ResponseEntity<GenericResponse> getStaticVaultProps() {
        return ResponseEntity.ok(new GenericResponse(true, "Static props returned successfully", staticVaultProps, HttpStatus.OK.value(), LocalDateTime.now()));
    }
}
