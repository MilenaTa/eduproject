package rs.edu.shipping.user;

public class User {

  private static User uniqueInstance;
  private String username;
  private String password;


  private User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  private User(){}

  public static User getInstance(String username, String password){
    if(uniqueInstance == null){
      uniqueInstance = new User(username, password);
    }
    return uniqueInstance;
  }

  public static User getInstance(){
    if(uniqueInstance == null){
      uniqueInstance = new User();
    }
    return uniqueInstance;
  }

  public String getUsername() { return username; }

  public String getPassword() { return password; }
}
