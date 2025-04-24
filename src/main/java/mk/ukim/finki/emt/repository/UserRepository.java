package mk.ukim.finki.emt.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @Transactional
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {
                    "username", "password", "name", "surname"
            }
    )
    List<User> findAllBy();


    UserProjection findByRole(Role role);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

}
