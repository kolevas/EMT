package mk.ukim.finki.emt.dto;

import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.Role;

public record UsersWithoutWishlistDto (String username, String name, String surname, String password, Role role) {
    public static UsersWithoutWishlistDto from(User user) {
        return new UsersWithoutWishlistDto(user.getUsername(), user.getName(), user.getSurname(), user.getPassword(), user.getRole());
    }
}
