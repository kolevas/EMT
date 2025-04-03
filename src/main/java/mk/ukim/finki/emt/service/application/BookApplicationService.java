package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.CreateBookDto;
import mk.ukim.finki.emt.dto.DisplayHistoryDto;
import mk.ukim.finki.emt.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.dto.UpdateBookDto;
import mk.ukim.finki.emt.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    public Optional<UpdateBookDto> save(CreateBookDto bookDto);
    public Optional<UpdateBookDto> update(Long id, CreateBookDto bookDto);
    public Optional<UpdateBookDto> delete(Long id);
    public List<UpdateBookDto> findAll();
    public Optional<UpdateBookDto> findById(Long id);
    public List<DisplayHistoryDto> getHistory(Long id);
}
