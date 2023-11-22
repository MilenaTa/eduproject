package rs.edu.shipping.user.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.edu.shipping.user.User;
import rs.edu.shipping.user.constants.UserRole;
import rs.edu.shipping.user.entity.AuthoritiesEntity;
import rs.edu.shipping.user.entity.AuthoritiesId;
import rs.edu.shipping.user.entity.UserEntity;
import rs.edu.shipping.user.repository.AuthoritiesEntityRepository;
import rs.edu.shipping.user.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserEntityRepository userEntityRepository;
  private final AuthoritiesEntityRepository authoritiesEntityRepository;

  public void login(String username, String password){
    BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    var userEntity = userEntityRepository.findByUsername(username).get();
    var bcryptPassword = userEntity.getPassword();
    bcryptPassword = bcryptPassword.substring(bcryptPassword.indexOf("}")+1);
    if(userEntity.getUsername() == null){
      throw new RuntimeException("There is no registered user with such username");
    } else if (!b.matches(password, bcryptPassword)) {
      throw new RuntimeException("Wrong password");
    }
    User.getInstance(username, password);
  }

  public void signupCustomer(String username, String password, String firstName, String lastName){
    save(username, password, firstName, lastName, UserRole.CUSTOMER.getValue());
  }

  public UserEntity save(String username, String password, String firstName, String lastName, String role){
    UserEntity user = UserEntity.builder()
        .username(username)
        .password(passwordGenerator(password))
        .firstName(firstName)
        .lastName(lastName)
        .active(true)
        .build();
    userEntityRepository.save(user);
    AuthoritiesEntity authority = AuthoritiesEntity.builder()
        .id(AuthoritiesId.builder()
            .role(role)
            .username(username)
            .build())
        .build();
    saveAuthority(authority);
    return user;
  }

  private String passwordGenerator(String password){
    var hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
    hashPassword = "{bcrypt}" + hashPassword;
    return hashPassword;
  }

  public void saveAuthority(AuthoritiesEntity authorities){
    authoritiesEntityRepository.save(authorities);
  }

  public UserEntity getByUsername(String username){
    return userEntityRepository.findByUsername(username).get();
  }

  public List<UserEntity> getAll(){
    return userEntityRepository.findAll();
  }

}
