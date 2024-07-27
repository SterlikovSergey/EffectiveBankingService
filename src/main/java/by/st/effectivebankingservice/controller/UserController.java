package by.st.effectivebankingservice.controller;

import by.st.effectivebankingservice.dtos.EmailRequest;
import by.st.effectivebankingservice.dtos.PhoneRequest;
import by.st.effectivebankingservice.dtos.RegistrationRequest;
import by.st.effectivebankingservice.mapper.UserMapper;
import by.st.effectivebankingservice.model.User;
import by.st.effectivebankingservice.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users") //TODO: add swagger
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @Operation(summary = "get user by id")
    @GetMapping("/{id}")
    @ApiResponse(code = 404, message = "User not found")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegistrationRequest request) {
        log.info("Registration request: {}", request);
        User user = userService.createUser(mapper.toUser(request));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<?> updatePhone(@PathVariable Long id, @RequestBody PhoneRequest phone,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        userService.updatePhone(id, phone.getPhone(), userDetails);
        return ResponseEntity.ok("Phone updated successfully");
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Long id, @RequestBody EmailRequest email,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        userService.updateEmail(id, email.getEmail(), userDetails);
        return ResponseEntity.ok("Email updated successfully");
    }

    @DeleteMapping("/{id}/phone")
    public ResponseEntity<?> deletePhone(@PathVariable Long id, @RequestBody PhoneRequest phone,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        userService.deletePhone(id, phone.getPhone(), userDetails);
        return ResponseEntity.ok("Phone deleted successfully");
    }

    @DeleteMapping("/{id}/email")
    public ResponseEntity<?> deleteEmail(@PathVariable Long id, @RequestBody EmailRequest email,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteEmail(id, email.getEmail(), userDetails);
        return ResponseEntity.ok("Email deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> searchUsers(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            Pageable pageable) {
        Page<User> users = userService.searchUsers(birthday, phone, fullName, email, pageable);
        return ResponseEntity.ok(users);
    }
}
