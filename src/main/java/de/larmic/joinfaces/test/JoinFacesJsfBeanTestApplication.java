package de.larmic.joinfaces.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class JoinFacesJsfBeanTestApplication {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(JoinFacesJsfBeanTestApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter("joinfaces-bean-test"));
        springApplication.run(args);
    }

}
