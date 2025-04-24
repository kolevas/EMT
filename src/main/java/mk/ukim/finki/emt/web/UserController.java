package mk.ukim.finki.emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.dto.*;
import mk.ukim.finki.emt.model.domain.JwtLog;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.repository.UserRepository;
import mk.ukim.finki.emt.service.application.UserApplicationService;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;


    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<UpdateUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and generates a JWT")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    @Operation(summary = "User wishlist")
    @GetMapping("/my_wishlist/{username}")
    public List<UpdateBookDto> getUserWishlist(@PathVariable String username) {
        return userApplicationService.getUserWishlist(username);
    }

    @Operation(summary = "Add book to wishlist")
    @PostMapping("/add_to_wishlist/{username}")
    public List<UpdateBookDto> addBookToWhishlist(@PathVariable String username, @RequestBody Long bookId){
        return userApplicationService.addBookToWhishlist(username, bookId);
    }

    @Operation(summary = "Loan wishlisted books")
    @GetMapping("/loan_wishlist/{username}")
    public List<UpdateBookCopyDto> loanUserWishlist(@PathVariable String username) {
        return userApplicationService.loanWishlistedBooks(username);
    }

    @GetMapping("/list-user-info")
    public List<UsersWithoutWishlistDto> getAllUsers() {
        return userApplicationService.getUsersWithoutWishlist();
    }

    @GetMapping("/login-logs")
    public List<JwtLog> getUserLoginLogs() {
        return userApplicationService.getJwtLogs();
    }

}

