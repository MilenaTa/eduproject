package rs.edu.shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("rs.edu.shipping")
@EnableJpaRepositories("rs.edu.shipping")
@ConfigurationPropertiesScan("rs.edu.shipping")
@EnableScheduling
public class Client {

  public static void main(String[] args) {
  SpringApplication.run(Client.class, args);
}
}
