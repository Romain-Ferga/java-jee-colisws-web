package eu.fr.indyli.formation.transactionnel.ecolis.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import eu.fr.indyli.formation.business.config.EcolisBusinessConfig;
import eu.fr.indyli.formation.transactionnel.ecolis.security.WebSecurityConfig;

@SpringBootApplication
@Import({EcolisBusinessConfig.class, MvcConfig.class, WebSecurityConfig.class})

public class SpringConfigWebApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringConfigWebApplication.class);
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringConfigWebApplication.class, args);
  }

}
