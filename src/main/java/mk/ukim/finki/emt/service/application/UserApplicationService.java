package mk.ukim.finki.emt.service.application;


import mk.ukim.finki.emt.dto.CreateUserDto;
import mk.ukim.finki.emt.dto.LoginUserDto;
import mk.ukim.finki.emt.dto.UpdateUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<UpdateUserDto> register(CreateUserDto createUserDto);

    Optional<UpdateUserDto> login(LoginUserDto loginUserDto);

    Optional<UpdateUserDto> findByUsername(String username);

}
