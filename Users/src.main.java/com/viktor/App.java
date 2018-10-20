package com.viktor;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.viktor.beans.User;
import com.viktor.repository.UserRepository;

/**
 * @author Viktor Angelutsa
 *
 */
@SpringBootApplication
public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
   
	
	@Bean
	public CommandLineRunner setup(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("John", "Lewis", "jlewis", "pass", "j.l@gmail.com", "uk"));
			userRepository.save(new User("Nick", "Stuart", "nstuart", "pass", "n.s@gmail.com", "uk"));
			userRepository.save(new User("Helen", "Brown", "hbrown", "pass", "h.b@gmail.com", "uk"));
			userRepository.save(new User("Vanessa", "Scott", "vscott", "pass", "v.s@gmail.com", "uk"));
			logger.info("The sample data has been generated");
		};
	}
}
