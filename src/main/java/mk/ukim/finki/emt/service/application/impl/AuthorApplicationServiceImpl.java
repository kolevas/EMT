package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.CreateAuthorDto;
import mk.ukim.finki.emt.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.model.projections.AuthorName;
import mk.ukim.finki.emt.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;

    public AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<UpdateAuthorDto> findAll() {
        return UpdateAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<UpdateAuthorDto> findById(Long id) {
        return authorService.findById(id).map(UpdateAuthorDto::from);
    }

    @Override
    public Optional<UpdateAuthorDto> update(Long id,CreateAuthorDto authorDto) {
        return authorService.update(id,authorDto.toAuthor()).map(UpdateAuthorDto::from);
    }

    @Override
    public Optional<UpdateAuthorDto> save(CreateAuthorDto createAuthorDto) {
        return authorService.save(createAuthorDto.toAuthor()).map(UpdateAuthorDto::from);
    }

    @Override
    public void delete(Long id) {
        authorService.delete(id);
    }

    @Override
    public List<AuthorName> findAllProjectedBy() {
        return authorService.findAllProjectedBy();
    }
}
