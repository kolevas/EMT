package mk.ukim.finki.emt.service.domain;

import java.util.Optional;

public interface BooksPerAuthorViewService {

    Optional<Integer> numBooksByAuthorId (Long authorId);
}
