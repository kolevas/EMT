package mk.ukim.finki.emt.service.application;

import org.springframework.stereotype.Service;

import java.util.Optional;

public interface BooksPerAuthorViewApplicationService {
    Optional<Integer> numBooksByAuthorId (Long authorId);
}
