package com.iihtibm.registration;

import com.iihtibm.registration.models.User;
import com.iihtibm.registration.repository.UserJdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author savagelee
 */
@SpringBootApplication
public class RegistrationApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserJdbcRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("User id 10001 -> {}", repository.findById(10001L));

		User user = new User();
		user.setId(10010L);
		user.setName("John");
		user.setEmail("John@iihtibm.com");
		user.setUsername("John");
		user.setPassword("123456");
		user.setRole("USER");
		logger.info("Inserting -> {}", repository.insert(user));

		user.setId(10001L);
		user.setName("Peter");
		user.setEmail("Peter@iihtibm.com");
		user.setUsername("Peter");
		user.setPassword("123456");
		user.setRole("USER");
		logger.info("Update 10001 -> {}", repository.update(user));

		//repository.deleteById(10002L);

		logger.info("All users -> {}", repository.findAll());
	}

}
