package rs.edu.shipping.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "User")
public class UserEntity {

  @Id
  @Column(name = "username", unique = true)
  private String username;
  private String password;
  @Column(name = "firstname")
  private String firstName;
  @Column(name = "lastname")
  private String lastName;
  private boolean active;

}