package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.projections.AuthorName;
import mk.ukim.finki.emt.repository.AuthorRepository;
import mk.ukim.finki.emt.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.emt.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.service.domain.AuthorService;
import mk.ukim.finki.emt.service.domain.AuthorsPerCountryViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorApplicationService authorService;
    private final AuthorsPerCountryViewService authorsPerCountryViewService;
    public AuthorController(AuthorApplicationService authorService, AuthorsPerCountryViewService authorsPerCountryViewService) {
        this.authorService = authorService;
        this.authorsPerCountryViewService = authorsPerCountryViewService;
    }

    @GetMapping("/all")
    public List<UpdateAuthorDto> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/by-country")
    public ResponseEntity<Integer> authors_per_country(Long countryId) {
        return authorsPerCountryViewService.authorsPerCountry(countryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/names")
    public List<AuthorName> getAuthorNames() {
        return authorService.findAllProjectedBy();
    }

}
