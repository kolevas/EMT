package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.*;
import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.JwtLog;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.repository.LogsRepository;
import mk.ukim.finki.emt.security.JwtHelper;
import mk.ukim.finki.emt.service.application.UserApplicationService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final LogsRepository logsRepository;
    private final LogsRepository loginLogsRepository;


    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper, LogsRepository logsRepository, LogsRepository loginLogsRepository) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.logsRepository = logsRepository;
        this.loginLogsRepository = loginLogsRepository;
    }

    @Override
    public Optional<UpdateUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(UpdateUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(loginUserDto.username(), loginUserDto.password());

        String token = jwtHelper.generateToken(user);
        JwtLog jwtLog = new JwtLog(token, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        logsRepository.save(jwtLog);

        return Optional.of(new LoginResponseDto(token));

    }

    @Override
    public Optional<UpdateUserDto> findByUsername(String username) {
        return Optional.of(UpdateUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<UpdateBookDto> addBookToWhishlist(String username, Long bookId) {
        User user = userService.findByUsername(username);
        userService.addBookToWhishlist(username,bookId);
        List<Book> books = user.getWishlistedBooks();
        return books.stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }

    @Override
    public List<UpdateBookDto> getUserWishlist(String username) {
        return userService.getUserWishlist(username).stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }

    @Override
    public List<UpdateBookCopyDto> loanWishlistedBooks(String username) {
        return userService.loanWishlistedBooks(username).stream().map(UpdateBookCopyDto::from).collect(Collectors.toList());

    }

    @Override
    public List<UsersWithoutWishlistDto> getUsersWithoutWishlist() {
        return userService.getUsersWithoutWishlist().stream().map(UsersWithoutWishlistDto::from).toList();
    }

    @Override
    public List<JwtLog> getJwtLogs() {
        return loginLogsRepository.findAll();
    }


}
