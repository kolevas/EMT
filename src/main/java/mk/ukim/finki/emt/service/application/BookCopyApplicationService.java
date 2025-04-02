package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.UpdateBookCopyDto;
import mk.ukim.finki.emt.dto.UpdateBookDto;
import mk.ukim.finki.emt.model.domain.Book;
import mk.ukim.finki.emt.model.domain.BookCopy;

import java.util.List;
import java.util.Optional;

public interface BookCopyApplicationService {
    Optional<UpdateBookCopyDto> createCopy(Long id);
    Optional<UpdateBookCopyDto> findById(Long id);
    List<UpdateBookCopyDto> findAll();
    List<UpdateBookCopyDto> findByBook(Long id);
    Optional<UpdateBookDto> loan(Long id);
    public Optional<UpdateBookCopyDto> returnBook(Long id);
}
