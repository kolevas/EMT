package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.UpdateBookCopyDto;
import mk.ukim.finki.emt.dto.UpdateBookDto;
import mk.ukim.finki.emt.service.application.BookCopyApplicationService;
import mk.ukim.finki.emt.service.domain.BookCopyService;
import mk.ukim.finki.emt.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookCopyApplicationServiceImpl implements BookCopyApplicationService {

    private final BookCopyService bookCopyService;

    public BookCopyApplicationServiceImpl(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @Override
    public Optional<UpdateBookCopyDto> createCopy(Long id) {
        return bookCopyService.createCopy(id).map(UpdateBookCopyDto::from);
    }

    @Override
    public Optional<UpdateBookCopyDto> findById(Long id) {
        return bookCopyService.findById(id).map(UpdateBookCopyDto::from);
    }

    @Override
    public List<UpdateBookCopyDto> findAll() {
        return bookCopyService.findAll().stream().map(UpdateBookCopyDto::from).toList();
    }

    @Override
    public List<UpdateBookCopyDto> findByBook(Long id) {
        return bookCopyService.findByBook(id).stream().map(UpdateBookCopyDto::from).toList();
    }

    @Override
    public Optional<UpdateBookDto> loan(Long id) {
        return bookCopyService.loan(id).map(UpdateBookDto::from);
    }

    @Override
    public Optional<UpdateBookCopyDto> returnBook(Long id) {
        return bookCopyService.returnBook(id).map(UpdateBookCopyDto::from);
    }
}
