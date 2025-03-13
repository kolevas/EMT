package mk.ukim.finki.emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmtApplication.class, args);
	}

}
//
//		{
//		"name": "Les Misérables",
//		"category": "NOVEL",
//		"author": 1,
//		"availableCopies": 5
//		}
//nov entitet primerok za sekoja kniga i da ne se cuva int available cpy tuku da ima vrska i da ima iznajmuvanje za toj primerok