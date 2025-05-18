package mk.ukim.finki.emt.service.application.impl;

import mk.ukim.finki.emt.dto.CreateAuthorDto;
import mk.ukim.finki.emt.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.projections.AuthorName;
import mk.ukim.finki.emt.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.service.domain.AuthorService;
import mk.ukim.finki.emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
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
        Country country = countryService.findById(authorDto.country()).get();
        return authorService.update(id,authorDto.toAuthor(country)).map(UpdateAuthorDto::from);
    }

    @Override
    public Optional<UpdateAuthorDto> save(CreateAuthorDto createAuthorDto) {
        Country country = countryService.findById(createAuthorDto.country()).get();
        return authorService.save(createAuthorDto.toAuthor(country)).map(UpdateAuthorDto::from);
    }

    @Override
    public Optional<UpdateAuthorDto> delete(Long id) {
        return authorService.delete(id).map(UpdateAuthorDto::from);
    }

    @Override
    public List<AuthorName> findAllProjectedBy() {
        return authorService.findAllProjectedBy();
    }
}
