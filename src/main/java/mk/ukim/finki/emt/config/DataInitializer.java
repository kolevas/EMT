//package mk.ukim.finki.emt.config;
//
//import jakarta.annotation.PostConstruct;
//import mk.ukim.finki.emt.model.domain.Author;
//import mk.ukim.finki.emt.model.domain.Book;
//import mk.ukim.finki.emt.model.domain.Country;
//import mk.ukim.finki.emt.model.domain.User;
//import mk.ukim.finki.emt.model.enumerations.BookCategory;
//import mk.ukim.finki.emt.model.enumerations.Role;
//import mk.ukim.finki.emt.repository.AuthorRepository;
//import mk.ukim.finki.emt.repository.BookRepository;
//import mk.ukim.finki.emt.repository.CountryRepository;
//import mk.ukim.finki.emt.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer {
//     private final AuthorRepository authorRepository;
//     private final CountryRepository countryRepository;
//     private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final BookRepository bookRepository;
//
//    public DataInitializer(AuthorRepository authorRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BookRepository bookRepository) {
//         this.authorRepository = authorRepository;
//         this.countryRepository = countryRepository;
//         this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.bookRepository = bookRepository;
//    }
//     @PostConstruct
//     public void init(){
//         System.out.println(passwordEncoder.encode("li"));
//         Country france = countryRepository.save(new Country("France", "Europe"));
//         Country spain = countryRepository.save(new Country("Spain", "Europe"));
//         Country italy = countryRepository.save(new Country("Italy", "Europe"));
//         Country germany = countryRepository.save(new Country("Germany", "Europe"));
//         Country uk = countryRepository.save(new Country("United Kingdom", "Europe"));
//
//         Author author = authorRepository.save(new Author("Victor", "Hugo", france));
//         authorRepository.save(new Author("Miguel", "de Cervantes", spain));
//         authorRepository.save(new Author("Dante", "Alighieri", italy));
//         authorRepository.save(new Author("Johann", "Goethe", germany));
//         authorRepository.save(new Author("William", "Shakespeare", uk));
//
//         bookRepository.save(new Book("Book1", BookCategory.NOVEL, author));
//
//         userRepository.save(new User(
//                 "li",
//                 passwordEncoder.encode("li"),
//                 "Snezana",
//                 "Koleva",
//                 Role.ROLE_LIBRARIAN
//         ));
//
//         userRepository.save(new User(
//                 "user",
//                 passwordEncoder.encode("user"),
//                 "user",
//                 "user",
//                 Role.ROLE_USER
//         ));
//
//
//     }
//
//}
