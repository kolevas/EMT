package mk.ukim.finki.emt.service.application;


import mk.ukim.finki.emt.dto.*;
import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;
import mk.ukim.finki.emt.model.domain.JwtLog;
import mk.ukim.finki.emt.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<UpdateUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<UpdateUserDto> findByUsername(String username);
    List<UpdateBookDto> addBookToWhishlist(String username, Long bookId);
    List<UpdateBookDto> getUserWishlist(String username);
    List<UpdateBookCopyDto> loanWishlistedBooks(String username);
    List<UsersWithoutWishlistDto> getUsersWithoutWishlist();
    List<JwtLog> getJwtLogs();
}
