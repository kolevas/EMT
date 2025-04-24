package mk.ukim.finki.emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmtApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}




}

//		{
//		"name": "Les Misérables",
//		"category": "NOVEL",
//		"author": 1,
//		"availableCopies": 5
//		}
//nov entitet primerok za sekoja kniga i da ne se cuva int available cpy tuku da ima vrska i da ima iznajmuvanje za toj primerok

//istorija na promeni na kniga (na sekoj edit da se cuva kniga i da napraveme ednpoiunt kaj sho ke se listat site prethodni verzii na knigata
//nov endpoint kaj sho ke se vrakja username token issued at i expires at