package mk.ukim.finki.emt.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.model.Author;
import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.repository.AuthorRepository;
import mk.ukim.finki.emt.repository.BookRepository;
import mk.ukim.finki.emt.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
     private final AuthorRepository authorRepository;
     private final CountryRepository countryRepository;
     public DataInitializer(AuthorRepository authorRepository, CountryRepository countryRepository) {
         this.authorRepository = authorRepository;
         this.countryRepository = countryRepository;
     }
     @PostConstruct
     public void init(){
         Country france = countryRepository.save(new Country("France", "Europe"));
         Country spain = countryRepository.save(new Country("Spain", "Europe"));
         Country italy = countryRepository.save(new Country("Italy", "Europe"));
         Country germany = countryRepository.save(new Country("Germany", "Europe"));
         Country uk = countryRepository.save(new Country("United Kingdom", "Europe"));

         authorRepository.save(new Author("Victor", "Hugo", france));
         authorRepository.save(new Author("Miguel", "de Cervantes", spain));
         authorRepository.save(new Author("Dante", "Alighieri", italy));
         authorRepository.save(new Author("Johann", "Goethe", germany));
         authorRepository.save(new Author("William", "Shakespeare", uk));

     }

}
