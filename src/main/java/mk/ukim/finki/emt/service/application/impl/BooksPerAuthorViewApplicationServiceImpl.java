package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.service.application.BooksPerAuthorViewApplicationService;
import mk.ukim.finki.emt.service.domain.BooksPerAuthorViewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BooksPerAuthorViewApplicationServiceImpl implements BooksPerAuthorViewApplicationService {

    private final BooksPerAuthorViewService booksPerAuthor;

    public BooksPerAuthorViewApplicationServiceImpl(BooksPerAuthorViewService booksPerAuthor) {
        this.booksPerAuthor = booksPerAuthor;
    }


    @Override
    public Optional<Integer> numBooksByAuthorId(Long authorId) {
        return booksPerAuthor.numBooksByAuthorId(authorId);
    }
}
