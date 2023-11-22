package rs.edu.shipping.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.shipping.user.service.UserService;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/signup")
  public ResponseEntity<Void> signup(String username, String password, String firstName, String lastName) {
    userService.signupCustomer(username, password, firstName, lastName);
    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/login")
  public ResponseEntity<Void> login(String username, String password) {
    userService.login(username, password);
    return ResponseEntity.ok().build();
  }
  @PostMapping(value = "/create/employee")
  public ResponseEntity<Void> createEmployee() {
    return ResponseEntity.ok().build();
  }
  @GetMapping("/get")
  public ResponseEntity<Void> get() {
    return ResponseEntity.ok().build();
  }
}
