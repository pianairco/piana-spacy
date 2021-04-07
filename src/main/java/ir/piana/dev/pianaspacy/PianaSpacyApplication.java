package ir.piana.dev.pianaspacy;

import ir.piana.dev.pianaspacy.cfg.PianaSpacyModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * https://github.com/aswzen/Simple-JPOS-Q2-HTTP-ISO-Server
 * https://www.javaprogramto.com/2020/04/spring-boot-activemq-in-memory-example.html
 * */
@SpringBootApplication
@EnableConfigurationProperties({
		PianaSpacyModel.class
})
public class PianaSpacyApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(PianaSpacyApplication.class, args);

	}
}
