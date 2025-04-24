package mk.ukim.finki.emt.service.domain.impl;

import mk.ukim.finki.emt.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.service.domain.BooksPerAuthorViewService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BooksPerAuthorViewServiceImpl implements BooksPerAuthorViewService {

    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BooksPerAuthorViewServiceImpl(BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public Optional<Integer> numBooksByAuthorId(Long authorId) {
        return booksPerAuthorViewRepository.numBooksByAuthorId(authorId);
    }
}
