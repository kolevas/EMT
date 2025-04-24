package mk.ukim.finki.emt.service.domain.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;
import mk.ukim.finki.emt.repository.BookRepository;
import mk.ukim.finki.emt.service.domain.BookCopyService;
import mk.ukim.finki.emt.service.domain.BookService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.repository.UserRepository;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookService bookService;
    private final BookCopyService bookCopyService;
    private final BookRepository bookRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, BookService bookService, BookCopyService bookCopyService, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookService = bookService;
        this.bookCopyService = bookCopyService;
        this.bookRepository = bookRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public List<Book> addBookToWhishlist(String username, Long bookId) {
        Book book = bookService.findById(bookId).get();
        List<BookCopy> bookCopies = bookCopyService.findByBook(bookId);
        User user = findByUsername(username);
        if (!bookCopies.isEmpty()) {
           user.getWishlistedBooks().add(book);
           book.getReaders().add(user);
           userRepository.save(user);
           bookService.save(book);
            return user.getWishlistedBooks();
        }
        throw new RuntimeException("Book could not be added to user. No available copies found.");
    }

    @Override
    public List<Book> getUserWishlist(String username) {
        return userRepository.findByUsername(username).get().getWishlistedBooks();
    }

    @Override
    public List<BookCopy> loanWishlistedBooks(String username) {
        User user = findByUsername(username);
        List<Book> books = user.getWishlistedBooks();
        List<Book> toRemove = new ArrayList<>();
        List<BookCopy> userBookCopies = new ArrayList<>();

        books.forEach(book -> {
            List<BookCopy> bookCopies = bookCopyService.findByBook(book.getId());
            if (!bookCopies.isEmpty()) {
                BookCopy copy = bookCopies.get(0);
                userBookCopies.add(copy);
                bookCopyService.loan(copy.getId());
                toRemove.add(book);
                book.getReaders().remove(user);
            }
        });

        books.removeAll(toRemove);
        userRepository.save(user);
        bookRepository.saveAll(books);
        return userBookCopies;
    }


    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException("Username or password cannot be empty");
        if (!password.equals(repeatPassword)) throw new RuntimeException("Passwords do not match");
        if (userRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Username is already in use");
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException("Username or password cannot be empty");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found"));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Passwords do not match");
        return user;

    }

    @Transactional
    public List<User> getUsersWithoutWishlist() {
        EntityGraph<User> graph = entityManager.createEntityGraph(User.class);
        graph.addAttributeNodes("username","name", "surname", "password", "role");

        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .setHint("javax.persistence.loadgraph", graph)
                .getResultList();
    }

}
