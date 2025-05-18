package mk.ukim.finki.emt.service.application;

import mk.ukim.finki.emt.dto.CreateAuthorDto;
import mk.ukim.finki.emt.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.projections.AuthorName;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<UpdateAuthorDto> findAll();
    Optional<UpdateAuthorDto> findById(Long id);
    Optional<UpdateAuthorDto> update(Long id, CreateAuthorDto authorDto);
    Optional<UpdateAuthorDto> save(CreateAuthorDto createAuthorDto);
    Optional<UpdateAuthorDto> delete(Long id);
    List<AuthorName> findAllProjectedBy();
}
