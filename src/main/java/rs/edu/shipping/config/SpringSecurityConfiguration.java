package rs.edu.shipping.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import rs.edu.shipping.user.constants.UserRole;

@Configuration
public class SpringSecurityConfiguration {

  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource){
    System.out.println("SpringSecurityConfiguration ");
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, active from user where username = ?");
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from authorities where username = ?");
    return jdbcUserDetailsManager;
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(configurer ->
        configurer
            .requestMatchers(HttpMethod.POST, "/user/create/employee").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.POST, "/user/login").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.POST, "/user/signup").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.GET, "/user/get").hasAuthority(UserRole.CUSTOMER.getValue())
            .requestMatchers(HttpMethod.GET, "/order/request").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.POST, "/order/create").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.POST, "/order/choose").hasAuthority(UserRole.EMPLOYEE.getValue())
            .requestMatchers(HttpMethod.POST, "/courier/create").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.POST, "/provider/create").hasAuthority(UserRole.ADMIN.getValue())
            .requestMatchers(HttpMethod.POST, "/provider/update").hasAuthority(UserRole.ADMIN.getValue())
    );
    http.httpBasic(Customizer.withDefaults());
    http.csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }
}
