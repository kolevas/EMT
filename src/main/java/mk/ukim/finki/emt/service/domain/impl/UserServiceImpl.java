package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookService bookService;
    private final BookCopyService bookCopyService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, BookService bookService, BookCopyService bookCopyService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookService = bookService;
        this.bookCopyService = bookCopyService;
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
           userRepository.save(user);
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
        List<Book> books = userRepository.findByUsername(username).get().getWishlistedBooks();
        List<BookCopy> userBookCopies = new ArrayList<>();
        books.forEach(book -> {
            List<BookCopy> bookCopies = bookCopyService.findByBook(book.getId());
            if (!bookCopies.isEmpty()) {
                userBookCopies.add(bookCopies.get(0));
                bookCopyService.loan(bookCopies.get(0).getId());
            }
        });
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
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Username or password cannot be empty");
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                RuntimeException::new);
    }

}
