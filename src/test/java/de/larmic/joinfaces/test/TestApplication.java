package de.larmic.joinfaces.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = TestApplication.class)
public class TestApplication {
   public static void main(String[] args) {
      new SpringApplication(TestApplication.class).run(args);
   }
}
